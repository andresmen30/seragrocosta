/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.entity.ParametrizacionNomina;
import com.seragrocosta.ejb.validator.ValidatorEjb;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ParametrizacionNominaFacade extends AbstractFacade<ParametrizacionNomina> {

    @Inject
    private ValidatorEjb validatorEjb;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrizacionNominaFacade() {
        super(ParametrizacionNomina.class);
    }

    public List<ParametrizacionNomina> getParametrizacionDto() {
        final TypedQuery<ParametrizacionNomina> query
                = this.em.createNamedQuery("ParametrizacionNomina.findAll", ParametrizacionNomina.class);
        return query.getResultList();
    }

}
