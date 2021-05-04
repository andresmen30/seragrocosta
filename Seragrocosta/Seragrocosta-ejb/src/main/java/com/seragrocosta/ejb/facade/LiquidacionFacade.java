/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.LiquidacionConversor;
import com.seragrocosta.ejb.dto.LiquidacionDto;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.ejb.entity.Liquidacion;
import java.time.LocalDate;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class LiquidacionFacade extends AbstractFacade<Liquidacion> {
    
    @Inject
    private LiquidacionConversor liquidacionConversor;
    
    @Inject
    private PeriodoFacade periodoFacade;
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public LiquidacionFacade() {
        super(Liquidacion.class);
    }
    
    public Liquidacion save(final LiquidacionDto liquidacionDto) {
        final Liquidacion entity = liquidacionConversor
                .getLiquidacionFromDto(liquidacionDto);
        entity.setFechaLiquidacion(LocalDate.now());
        if (entity.getIdLiquidacion() == null) {
            this.create(entity);
        } else {
            this.edit(entity);
        }
        return entity;
    }
    
}
