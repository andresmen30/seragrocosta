/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.FondoPensionConversor;
import com.seragrocosta.ejb.dto.FondoPensionDto;
import com.seragrocosta.ejb.entity.FondoPension;
import com.seragrocosta.ejb.validator.ValidatorEjb;
import java.time.LocalDate;
import java.util.Date;
import javax.ejb.EJB;
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
public class FondoPensionFacade extends AbstractFacade<FondoPension> {

    @EJB
    private FondoPensionConversor fondoPensionConversor;

    @Inject
    private ValidatorEjb validatorEjb;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FondoPensionFacade() {
        super(FondoPension.class);
    }

    public void save(final FondoPensionDto dto) {
        final FondoPension entity = fondoPensionConversor.getFondoPensionFromEntity(dto);
        if (entity.getIdFondoPension() == null) {
            entity.setFechaCreacion(LocalDate.now());
            this.create(entity);
        } else {
            this.edit(entity);
        }
    }

    public boolean isExistNit(final FondoPensionDto dto) {
        final TypedQuery<FondoPension> query
                = this.em.createQuery("SELECT f FROM FondoPension f WHERE UPPER(f.nit)=:nit AND f.idFondoPension <>:idFondoPension", FondoPension.class);
        query.setParameter("nit", dto.getNit().toUpperCase());
        query.setParameter("idFondoPension", dto.getIdFondoPension() == null ? NumberUtils.INTEGER_ZERO : dto.getIdFondoPension());
        return validatorEjb.getSingleResult(query) != null;
    }

    public void delete(final FondoPensionDto dto) {
        final FondoPension entity = this.find(dto.getIdFondoPension());
        this.remove(entity);
    }

}
