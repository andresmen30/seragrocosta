/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.PeriodoConceptoSalarialDto;
import com.seragrocosta.ejb.entity.PeriodoConceptoSalarial;
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
public class PeriodoConceptoSaConversor implements Serializable {
    
    @Inject
    private ConceptoSalarialConversor parametrizationConversor;
    
    
    public PeriodoConceptoSalarialDto getPeriodoConceptSalFromEntity(final PeriodoConceptoSalarial periodoConceptoSalarialEntity) {
        final PeriodoConceptoSalarialDto periodoConceptoSalarialDto = new PeriodoConceptoSalarialDto();
        periodoConceptoSalarialDto.setIdPeriodoConceptoSalarial(periodoConceptoSalarialEntity.getIdPeriodoConceptoSalarial());
        periodoConceptoSalarialDto.setIdConceptoSalarial(parametrizationConversor
                .getConceptoSalarialFromEntity(periodoConceptoSalarialEntity.getIdConceptoSalarial()));
        return periodoConceptoSalarialDto;
    }
    
    public List<PeriodoConceptoSalarialDto> getPeriodoConceptSalListFromEntity(final List<PeriodoConceptoSalarial> periodoConceptoSalarialListEntity) {
        final List<PeriodoConceptoSalarialDto> periodoConceptoSalarialListDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(periodoConceptoSalarialListEntity)) {
            periodoConceptoSalarialListEntity.stream().forEach(conceptEntity -> {
                periodoConceptoSalarialListDto.add(getPeriodoConceptSalFromEntity(conceptEntity));
            });
        }
        return periodoConceptoSalarialListDto;
    }
    
    public PeriodoConceptoSalarial getPeriodoConceptSalFromDto(final PeriodoConceptoSalarialDto periodoConceptoSalarialDto) {
        final PeriodoConceptoSalarial periodoConceptoSalarialEntity = new PeriodoConceptoSalarial();
        periodoConceptoSalarialEntity.setIdPeriodoConceptoSalarial(periodoConceptoSalarialDto.getIdPeriodoConceptoSalarial());
        periodoConceptoSalarialEntity.setIdConceptoSalarial(parametrizationConversor
                .getConceptoSalarialFromDto(periodoConceptoSalarialDto.getIdConceptoSalarial()));
        return periodoConceptoSalarialEntity;
    }
    
    public List<PeriodoConceptoSalarial> getPeriodoConceptSalListFromDto(final List<PeriodoConceptoSalarialDto> periodoConceptoSalarialListDto) {
        final List<PeriodoConceptoSalarial> periodoConceptoSalarialListEntity = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(periodoConceptoSalarialListDto)) {
            periodoConceptoSalarialListDto.stream().forEach(conceptDto -> {
                periodoConceptoSalarialListEntity.add(getPeriodoConceptSalFromDto(conceptDto));
            });
        }
        return periodoConceptoSalarialListEntity;
    }
    
}
