/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.ConceptoSalarialDto;
import com.seragrocosta.ejb.entity.ConceptoSalarial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class ConceptoSalarialConversor implements Serializable {

    public ConceptoSalarialDto getConceptoSalarialFromEntity(final ConceptoSalarial conceptoSalarial) {
        final ConceptoSalarialDto conceptoSalarialDto = new ConceptoSalarialDto();
        conceptoSalarialDto.setIdConceptoSalarial(conceptoSalarial.getIdConceptoSalarial());
        conceptoSalarialDto.setNombre(conceptoSalarial.getNombre());
        return conceptoSalarialDto;
    }

    public List<ConceptoSalarialDto> getConceptoSalarialListFromEntity(final List<ConceptoSalarial> listaConceptoSalarial) {
        List<ConceptoSalarialDto> listaConceptoSalarialDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listaConceptoSalarial)) {
            listaConceptoSalarial.stream().forEach(entity -> {
                listaConceptoSalarialDto.add(getConceptoSalarialFromEntity(entity));
            });
        }
        return listaConceptoSalarialDto;
    }

    public ConceptoSalarial getConceptoSalarialFromDto(final ConceptoSalarialDto conceptoDto) {
        final ConceptoSalarial entity = new ConceptoSalarial();
        entity.setIdConceptoSalarial(conceptoDto.getIdConceptoSalarial());
        entity.setNombre(conceptoDto.getNombre());
        return entity;
    }

    public List<ConceptoSalarial> getConceptoSalarialListFromDto(final List<ConceptoSalarialDto> listaConceptoSalarialDto) {
        List<ConceptoSalarial> listaConceptoSalarialEntity = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listaConceptoSalarialDto)) {
            listaConceptoSalarialDto.stream().forEach(parametrizationDto -> {
                listaConceptoSalarialEntity.add(getConceptoSalarialFromDto(parametrizationDto));
            });
        }
        return listaConceptoSalarialEntity;
    }
}
