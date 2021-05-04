/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.entity.ConceptoSalarial;
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
public class ConceptoSalarialFacade extends AbstractFacade<ConceptoSalarial> {

    @Inject
    private ValidatorEjb validatorEjb;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptoSalarialFacade() {
        super(ConceptoSalarial.class);
    }

    public List<ConceptoSalarial> getConceptoSalarialAll() {
        final TypedQuery<ConceptoSalarial> query
                = this.em.createNamedQuery("ConceptoSalarial.findAll", ConceptoSalarial.class);
        return query.getResultList();
    }

    public boolean existParamValue(final String value) {
        final TypedQuery<ConceptoSalarial> query
                = this.em.createNamedQuery("ConceptoSalarial.findByNombre", ConceptoSalarial.class);
        query.setParameter("nombre", value);
        return validatorEjb.getSingleResult(query) != null;
    }

    public ConceptoSalarial getParametrizationByNombre(final String nombre) {
        final TypedQuery<ConceptoSalarial> query
                = this.em.createNamedQuery("ConceptoSalarial.findByNombre", ConceptoSalarial.class);
        query.setParameter("nombre", nombre);
        return validatorEjb.getSingleResult(query);
    }

}
