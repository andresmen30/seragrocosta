/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.DetalleLiquidacionConversor;
import com.seragrocosta.ejb.dto.DetalleLiquidacionDto;
import com.seragrocosta.ejb.entity.DetalleLiquidacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class DetalleLiquidacionFacade extends AbstractFacade<DetalleLiquidacion> {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Inject
    private DetalleLiquidacionConversor detalleLiquidacionConversor;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleLiquidacionFacade() {
        super(DetalleLiquidacion.class);
    }

    public void save(final List<DetalleLiquidacionDto> listaDetalleDto) {
        if (CollectionUtils.isNotEmpty(listaDetalleDto)) {
            listaDetalleDto.stream().forEach(dto -> {
                final DetalleLiquidacion entity = detalleLiquidacionConversor
                        .getDetalleLiquidacionFromDto(dto);
                if (entity.getIdDetalleLiquidacion() == null) {
                    this.create(entity);
                } else {
                  this.edit(entity);
                }
            });
        }
    }

}
