/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.ArlConversor;
import com.seragrocosta.ejb.dto.ArlDto;
import com.seragrocosta.ejb.entity.Arl;
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
public class ArlFacade extends AbstractFacade<Arl> {

    @Inject
    private ValidatorEjb validatorEjb;

    @Inject
    private ArlConversor arlConversor;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArlFacade() {
        super(Arl.class);
    }

    public void save(final ArlDto dto) {
        final Arl entity = arlConversor.getArlFromDto(dto);
        if (entity.getIdArl() == null) {
            entity.setFechaCreacion(LocalDate.now());
            this.create(entity);
        } else {
            this.edit(entity);
        }
    }

    public boolean isExistNit(final ArlDto dto) {
        final TypedQuery<Arl> query
                = this.em.createQuery("SELECT a FROM Arl a WHERE UPPER(a.nit)=:nit AND a.idArl <>:idArl", Arl.class);
        query.setParameter("nit", dto.getNit().toUpperCase());
        query.setParameter("idArl", dto.getIdArl() == null ? NumberUtils.INTEGER_ZERO : dto.getIdArl());
        return validatorEjb.getSingleResult(query) != null;
    }

    public void delete(final ArlDto dto) {
        final Arl entity = this.find(dto.getIdArl());
        this.remove(entity);
    }

}
