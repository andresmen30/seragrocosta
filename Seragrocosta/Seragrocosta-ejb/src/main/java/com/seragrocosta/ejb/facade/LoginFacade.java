/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.entity.Login;
import com.seragrocosta.ejb.validator.ValidatorEjb;
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
public class LoginFacade extends AbstractFacade<Login>{
    
    @Inject
    private ValidatorEjb validatorEjb;
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public LoginFacade() {
        super(Login.class);
    }
    
    
    public Login getLoginByEmail(final String email) {
        final TypedQuery<Login> query
                = this.em.createNamedQuery("Login.findByEmail", Login.class);
        query.setParameter("email", email);
        return validatorEjb.getSingleResult(query);
        
    }
    
}
