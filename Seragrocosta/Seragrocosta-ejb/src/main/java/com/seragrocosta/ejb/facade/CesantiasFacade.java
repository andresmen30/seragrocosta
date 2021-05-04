/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.CesantiasConversor;
import com.seragrocosta.ejb.dto.CesantiasDto;
import com.seragrocosta.ejb.entity.Cesantias;
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
public class CesantiasFacade extends AbstractFacade<Cesantias> {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Inject
    private CesantiasConversor cesantiasConversor;

    @Inject
    private ValidatorEjb validatorEjb;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CesantiasFacade() {
        super(Cesantias.class);
    }

    public void save(final CesantiasDto dto) {
        final Cesantias entity = cesantiasConversor.getCesantiasFromDto(dto);
        if (entity.getIdCesantias() == null) {
            entity.setFechaCreacion(LocalDate.now());
            this.create(entity);
        } else {
            this.edit(entity);
        }
    }

    public void delete(final CesantiasDto dto) {
        final Cesantias entity = this.find(dto.getIdCesantias());
        this.remove(entity);
    }

    public boolean isExistNit(final CesantiasDto dto) {
        final TypedQuery<Cesantias> query
                = this.em.createQuery("SELECT c FROM Cesantias c WHERE UPPER(c.nit)=:nit AND c.idCesantias <>:idCesantias", Cesantias.class);
        query.setParameter("nit", dto.getNit().toUpperCase());
        query.setParameter("idCesantias", dto.getIdCesantias() == null ? NumberUtils.INTEGER_ZERO : dto.getIdCesantias());
        return validatorEjb.getSingleResult(query) != null;
    }

}
