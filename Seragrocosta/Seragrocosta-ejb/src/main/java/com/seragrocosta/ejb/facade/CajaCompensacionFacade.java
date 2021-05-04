/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.CajaCompensacionConversor;
import com.seragrocosta.ejb.dto.CajaCompensacionDto;
import com.seragrocosta.ejb.entity.CajaCompensacion;
import com.seragrocosta.ejb.validator.ValidatorEjb;
import java.time.LocalDate;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author USER
 */
@Stateless
public class CajaCompensacionFacade extends AbstractFacade<CajaCompensacion> {

    @Inject
    private CajaCompensacionConversor cajaCompensacionConversor;

    @Inject
    private ValidatorEjb validatorEjb;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajaCompensacionFacade() {
        super(CajaCompensacion.class);
    }

    public void save(final CajaCompensacionDto dto) {
        final CajaCompensacion entity = cajaCompensacionConversor.getCajaCompensacionFromDto(dto);
        if (entity.getIdCajaCompensacion() == null) {
            entity.setFechaCreacion(LocalDate.now());
            this.create(entity);
        } else {
            this.edit(entity);
        }
    }

    public boolean isExistNit(final CajaCompensacionDto dto) {
        final TypedQuery<CajaCompensacion> query
                = this.em.createQuery("SELECT c FROM CajaCompensacion c WHERE UPPER(c.nit)=:nit AND c.idCajaCompensacion<>:idCajaCompensacion", CajaCompensacion.class);
        query.setParameter("nit", dto.getNit().toUpperCase());
        query.setParameter("idCajaCompensacion", dto.getIdCajaCompensacion() == null ? NumberUtils.INTEGER_ZERO : dto.getIdCajaCompensacion());
        return validatorEjb.getSingleResult(query) != null;
    }

    public void delete(final CajaCompensacionDto dto) {
        final CajaCompensacion entity = this.find(dto.getIdCajaCompensacion());
        this.remove(entity);
    }

}
