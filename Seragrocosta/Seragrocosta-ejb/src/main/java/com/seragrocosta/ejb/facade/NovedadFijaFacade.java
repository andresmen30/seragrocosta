/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.NovedadFijaConversor;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.entity.NovedadFija;
import com.seragrocosta.ejb.validator.ValidatorEjb;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class NovedadFijaFacade extends AbstractFacade<NovedadFija> {

    @Inject
    private ValidatorEjb validatorEjb;

    @Inject
    private NovedadFijaConversor empleadoConversor;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NovedadFijaFacade() {
        super(NovedadFija.class);
    }

    public void saveNovedadEmpleado(final EmpleadoDto empleadoDto) {
        final NovedadFija novedadEmpleado = empleadoConversor
                .getNovedadEmpleadoFromDto(empleadoDto.getNovedadEmpleadoDto(), empleadoDto);
        if (novedadEmpleado.getIdNovedadFija() == null) {
            this.create(novedadEmpleado);
        } else {
            this.edit(novedadEmpleado);
        }
    }

}
