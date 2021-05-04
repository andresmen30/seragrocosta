/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.dto.NovedadVariableDto;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.ejb.entity.Empleado;
import com.seragrocosta.ejb.entity.NovedadVariable;
import com.seragrocosta.ejb.entity.Periodo;
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
public class NovedadVariableConversor implements Serializable {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Inject
    private ConceptoNominaConversor conceptoNominaConversor;

    @Inject
    private PeriodoConversor periodoConversor;

    public NovedadVariable getNovedadVariableFromDto(@NonNull final EmpleadoDto empleadoDto) {
        final NovedadVariable entity = new NovedadVariable();
        entity.setIdNovedadVariable(empleadoDto.getNovedadVariableDto().getIdNovedadVariable());
        entity.setObservacion(empleadoDto.getNovedadVariableDto().getObservacion());
        entity.setValor(empleadoDto.getNovedadVariableDto().getValor());
        entity.setIdConceptoNomina(conceptoNominaConversor.getConceptoNominaFromDto(empleadoDto.getNovedadVariableDto().getIdConceptoNomina()));
        entity.setIdEmpleado(getEmpleadoFromBd(empleadoDto.getIdEmpleado()));
        entity.setIdPeriodo(getPeriodoFromBd(empleadoDto.getNovedadVariableDto().getIdPeriodo().getIdPeriodo()));
        return entity;
    }

    public NovedadVariableDto getNovedadVariableFromEntity(@NonNull final NovedadVariable entity) {
        final NovedadVariableDto dto = new NovedadVariableDto();
        dto.setIdNovedadVariable(entity.getIdNovedadVariable());
        dto.setObservacion(entity.getObservacion());
        dto.setValor(entity.getValor());
        dto.setIdConceptoNomina(conceptoNominaConversor.getConceptoNominaFromEntity(entity.getIdConceptoNomina()));
        dto.setIdPeriodo(periodoConversor.getPeriodoFromEntity(entity.getIdPeriodo()));
        return dto;
    }

    public List<NovedadVariableDto> getListNovedadVariableFromEntity(@NonNull final List<NovedadVariable> listEntity) {
        final List<NovedadVariableDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getNovedadVariableFromEntity(entity));
            });
        }
        return listDto;
    }

    public Empleado getEmpleadoFromBd(final Integer idEmpleado) {
        return this.em.find(Empleado.class, idEmpleado);
    }

    public Periodo getPeriodoFromBd(final Integer idPeriodo) {
        return this.em.find(Periodo.class, idPeriodo);
    }

}
