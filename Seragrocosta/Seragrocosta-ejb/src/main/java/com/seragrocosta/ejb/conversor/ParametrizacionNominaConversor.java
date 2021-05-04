/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.ParametrizacionNominaDto;
import com.seragrocosta.ejb.entity.ParametrizacionNomina;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@Stateless
public class ParametrizacionNominaConversor implements Serializable {

    public ParametrizacionNominaDto getParametrizacionFromEntity(final ParametrizacionNomina entity) {
        final ParametrizacionNominaDto dto = new ParametrizacionNominaDto();
        dto.setIdParametrizacion(entity.getIdParametrizacion());
        dto.setAuxilioTransporte(entity.getAuxilioTransporte());
        dto.setCajaCompensacion(entity.getCajaCompensacion());
        dto.setPension(entity.getPension());
        dto.setSalarioMinimo(entity.getSalarioMinimo());
        dto.setSaludEmpleado(entity.getSaludEmpleado());
        dto.setInteresCesantias(entity.getInteresCesantias());
        dto.setPrimaServicios(entity.getPrimaServicios());
        dto.setSaludEmpresa(entity.getSaludEmpresa());
        dto.setVacaciones(entity.getVacaciones());
        dto.setCesantias(entity.getCesantias());
        return dto;
    }

    public ParametrizacionNomina getParametrizacionFromDto(final ParametrizacionNominaDto dto) {
        final ParametrizacionNomina entity = new ParametrizacionNomina();
        entity.setIdParametrizacion(dto.getIdParametrizacion());
        entity.setAuxilioTransporte(dto.getAuxilioTransporte());
        entity.setPension(dto.getPension());
        entity.setSalarioMinimo(dto.getSalarioMinimo());
        entity.setCajaCompensacion(dto.getCajaCompensacion());
        entity.setSaludEmpleado(dto.getSaludEmpleado());
        entity.setSaludEmpresa(dto.getSaludEmpresa());
        entity.setInteresCesantias(dto.getInteresCesantias());
        entity.setPrimaServicios(dto.getPrimaServicios());
        entity.setVacaciones(dto.getVacaciones());
        entity.setCesantias(dto.getCesantias());
        return entity;
    }

}
