/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.NovedadVariableConversor;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.entity.NovedadFija;
import com.seragrocosta.ejb.entity.NovedadVariable;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class NovedadVariableFacade extends AbstractFacade<NovedadVariable> {

    @Inject
    private NovedadVariableConversor variableConversor;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NovedadVariableFacade() {
        super(NovedadVariable.class);
    }

    public void saveNovedadVariable(final EmpleadoDto empleadoDto) {
        final NovedadVariable novedadVariable = variableConversor
                .getNovedadVariableFromDto(empleadoDto);
        if (empleadoDto.getNovedadVariableDto().getIdNovedadVariable() == null) {
            this.create(novedadVariable);
        } else {
            this.edit(novedadVariable);
        }
    }

}
