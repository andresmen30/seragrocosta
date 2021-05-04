/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.dto.NovedadFijaDto;
import com.seragrocosta.ejb.entity.Empleado;
import com.seragrocosta.ejb.entity.NovedadFija;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class NovedadFijaConversor implements Serializable {
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    @Inject
    private ConceptoNominaConversor conceptoNominaConversor;
    
    public NovedadFija getNovedadEmpleadoFromDto(@NonNull final NovedadFijaDto dto, @NonNull final EmpleadoDto empleadoDto) {
        final NovedadFija entity = new NovedadFija();
        entity.setIdNovedadFija(dto.getIdNovedadFija());
        entity.setIdConceptoNomina(conceptoNominaConversor.getConceptoNominaFromDto(dto.getIdConceptoNomina()));
        entity.setIdEmpleado(getEmpleadoFromBd(empleadoDto.getIdEmpleado()));
        entity.setCuotas(dto.getCuotas());
        entity.setValor(dto.getValor());
        return entity;
    }
    
    public NovedadFijaDto getNovedadEmpleadoFromEntity(@NonNull final NovedadFija entity) {
        final NovedadFijaDto dto = new NovedadFijaDto();
        dto.setIdNovedadFija(entity.getIdNovedadFija());
        dto.setIdConceptoNomina(conceptoNominaConversor.getConceptoNominaFromEntity(entity.getIdConceptoNomina()));
        dto.setCuotas(entity.getCuotas());
        dto.setValor(entity.getValor());
        return dto;
    }
    
    public List<NovedadFijaDto> getListNovedadEmpleadoFromEntity(@NonNull final List<NovedadFija> listEntity) {
        final List<NovedadFijaDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getNovedadEmpleadoFromEntity(entity));
            });
        }
        return listDto;
    }
    
    public Empleado getEmpleadoFromBd(final Integer idEmpleado) {
        return this.em.find(Empleado.class, idEmpleado);
    }
    
}
