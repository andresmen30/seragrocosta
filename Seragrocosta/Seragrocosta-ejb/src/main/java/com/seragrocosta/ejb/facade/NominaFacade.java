/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.entity.Nomina;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class NominaFacade extends AbstractFacade<Nomina> {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NominaFacade() {
        super(Nomina.class);
    }

    public void delete(final EmpleadoDto empleadoDto) {
        this.em.createQuery("DELETE FROM Nomina n WHERE n.idNomina=:idNomina")
                .setParameter("idNomina",
                        empleadoDto.getNominaDto().getIdNomina()).executeUpdate();
    }

}
