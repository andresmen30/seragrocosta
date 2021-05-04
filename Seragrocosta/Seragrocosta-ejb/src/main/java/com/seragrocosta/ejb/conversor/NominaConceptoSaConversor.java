/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.NominaConceptoSalarialDto;
import com.seragrocosta.ejb.entity.NominaConceptoSalarial;
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
public class NominaConceptoSaConversor implements Serializable {

    @Inject
    private ConceptoSalarialConversor parametrizationConversor;

    public NominaConceptoSalarialDto getNominaConceptSalFromEntity(final NominaConceptoSalarial nominaConceptoSalarialEntity) {
        final NominaConceptoSalarialDto dto = new NominaConceptoSalarialDto();
        dto.setIdNominaConceptoSalarial(nominaConceptoSalarialEntity.getIdNominaConceptoSalarial());
        dto.setIdConceptoSalarial(parametrizationConversor
                .getConceptoSalarialFromEntity(nominaConceptoSalarialEntity.getIdConceptoSalarial()));
        return dto;
    }

    public List<NominaConceptoSalarialDto> getNominaConceptSalListFromEntity(final List<NominaConceptoSalarial> listEntity) {
        final List<NominaConceptoSalarialDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(conceptEntity -> {
                listDto.add(getNominaConceptSalFromEntity(conceptEntity));
            });
        }
        return listDto;
    }

    public NominaConceptoSalarial getNominaConceptSalFromDto(final NominaConceptoSalarialDto dto) {
        final NominaConceptoSalarial entity = new NominaConceptoSalarial();
        entity.setIdNominaConceptoSalarial(dto.getIdNominaConceptoSalarial());
        entity.setIdConceptoSalarial(parametrizationConversor
                .getConceptoSalarialFromDto(dto.getIdConceptoSalarial()));
        return entity;
    }

    public List<NominaConceptoSalarial> getNominaConceptSalListFromDto(final List<NominaConceptoSalarialDto> listDto) {
        final List<NominaConceptoSalarial> listEntity = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listDto)) {
            listDto.stream().forEach(conceptDto -> {
                listEntity.add(getNominaConceptSalFromDto(conceptDto));
            });
        }
        return listEntity;
    }

}
