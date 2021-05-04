/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.ConceptoNominaDto;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.dto.NovedadFijaDto;
import com.seragrocosta.ejb.dto.NovedadVariableDto;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.ejb.entity.Empleado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class EmpleadoConversor implements Serializable {
    
    @Inject
    private DepartamentoConversor departamentoConversor;
    
    @Inject
    private MunicipioConversor municipioConversor;
    
    @Inject
    private NominaConversor nominaConversor;
    
    public EmpleadoDto getEmpleadoFromEntity(final Empleado entity) {
        final EmpleadoDto dto = new EmpleadoDto();
        dto.setCelular(entity.getCelular());
        dto.setDepartamentoDto(departamentoConversor
                .getDepartamentoFromEntity(entity.getIdDepartamento()));
        dto.setDireccion(entity.getDireccion());
        dto.setEmail(entity.getEmail());
        dto.setFechaIngreso(entity.getFechaIngreso());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setIdEmpleado(entity.getIdEmpleado());
        dto.setIdentificacion(entity.getIdentificacion());
        dto.setMunicipioDto(municipioConversor.getMunicipioFromEntity(entity.getIdMunicipio()));
        dto.setPrimerApellido(entity.getPrimerApellido());
        dto.setPrimerNombre(entity.getPrimerNombre());
        dto.setSegundoApellido(entity.getSegundoApellido());
        dto.setSegundoNombre(entity.getSegundoNombre());
        dto.setSexo(entity.getSexo());
        dto.setTelefono(entity.getTelefono());
        dto.setTipoIdentificacion(entity.getTipoIdentificacion());
        dto.setTipoContrato(entity.getTipoContrato());
        dto.setNominaDto(nominaConversor.getNominaFromEntity(entity.getIdNomina()));
        dto.setNovedadEmpleadoDto(new NovedadFijaDto());
        dto.getNovedadEmpleadoDto().setIdConceptoNomina(new ConceptoNominaDto());
        dto.setNovedadVariableDto(new NovedadVariableDto());
        dto.getNovedadVariableDto().setIdPeriodo(new PeriodosDto());
        dto.getNovedadVariableDto().setIdConceptoNomina(new ConceptoNominaDto());
        dto.setEstado(entity.getEstado());
        return dto;
    }
    
    public Empleado getEmpleadoFromDto(final EmpleadoDto dto) {
        final Empleado entity = new Empleado();
        entity.setCelular(dto.getCelular());
        entity.setDireccion(dto.getDireccion());
        entity.setEmail(dto.getEmail());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setIdentificacion(dto.getIdentificacion());
        entity.setPrimerApellido(dto.getPrimerApellido());
        entity.setPrimerNombre(dto.getPrimerNombre());
        entity.setSegundoApellido(dto.getSegundoApellido());
        entity.setSegundoNombre(dto.getSegundoNombre());
        entity.setSexo(dto.getSexo());
        entity.setTelefono(dto.getTelefono());
        entity.setTipoIdentificacion(dto.getTipoIdentificacion());
        entity.setTipoContrato(dto.getTipoContrato());
        entity.setIdMunicipio(this.municipioConversor.getMunicipioFromBd(dto.getMunicipioDto().getIdMunicipio()));
        entity.setIdDepartamento(this.departamentoConversor.getDepartamentoFromBd(dto.getDepartamentoDto().getIdDepartamento()));
        entity.setIdNomina(this.nominaConversor.getNominaFromDto(dto.getNominaDto()));
        entity.setEstado(dto.getEstado());
        return entity;
    }
    
    public List<EmpleadoDto> getListEmpleadoFromEntity(final List<Empleado> listEntity) {
        final List<EmpleadoDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getEmpleadoFromEntity(entity));
            });
        }
        return listDto;
    }
}
