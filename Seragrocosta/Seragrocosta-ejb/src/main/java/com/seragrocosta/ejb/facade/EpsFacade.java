/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.EpsConversor;
import com.seragrocosta.ejb.dto.EpsDto;
import com.seragrocosta.ejb.entity.Eps;
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
public class EpsFacade extends AbstractFacade<Eps> {
    
    @Inject
    private EpsConversor epsConversor;
    
    @Inject
    private ValidatorEjb validatorEjb;
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public EpsFacade() {
        super(Eps.class);
    }
    
    public void save(final EpsDto epsDto) {
        final Eps eps = epsConversor.getEpsFromDto(epsDto);
        if (eps.getIdEps() == null) {
            eps.setFechaCreacion(LocalDate.now());
            this.create(eps);
        } else {
            this.edit(eps);
        }
    }
    
    public boolean isExistNit(final EpsDto epsDto) {
        final TypedQuery<Eps> query
                = this.em.createQuery("SELECT e FROM Eps e WHERE UPPER(e.nit)=:nit AND e.idEps<>:idEps", Eps.class);
        query.setParameter("nit", epsDto.getNit().toUpperCase());
        query.setParameter("idEps", epsDto.getIdEps() == null ? NumberUtils.INTEGER_ZERO : epsDto.getIdEps());
        return validatorEjb.getSingleResult(query) != null;
    }
    
    public void delete(final EpsDto epsDto) {
        final Eps eps = this.find(epsDto.getIdEps());
        this.remove(eps);
    }
    
}
