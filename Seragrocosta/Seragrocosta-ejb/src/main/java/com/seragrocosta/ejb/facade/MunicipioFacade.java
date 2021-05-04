/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.entity.Municipio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public List<Municipio> getListMunicipioByDepartamento(final int idDepartamento) {
        final TypedQuery<Municipio> query
                = this.em.createQuery("SELECT m FROM Municipio m WHERE m.idDepartamento.idDepartamento=:idDepartamento", Municipio.class)
                        .setParameter("idDepartamento", idDepartamento);
        return query.getResultList();
    }

}
