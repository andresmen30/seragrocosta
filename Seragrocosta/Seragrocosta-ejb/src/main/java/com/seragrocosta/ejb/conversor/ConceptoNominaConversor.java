/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.ConceptoNominaDto;
import com.seragrocosta.ejb.entity.ConceptoNomina;
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
public class ConceptoNominaConversor implements Serializable {
    
    @Inject
    private NominaConceptoSaConversor nominaConceptoSaConversor;
    
    public ConceptoNominaDto getConceptoNominaFromEntity(final ConceptoNomina entity) {
        final ConceptoNominaDto dto = new ConceptoNominaDto();
        dto.setIdConceptoNomina(entity.getIdConceptoNomina());
        dto.setNombre(entity.getNombre());
        dto.setImprimirDesprendible(entity.getImprimirDesprendible());
        dto.setTipoContable(entity.getTipoContable());
        dto.setCantidad(entity.getCantidad());
        dto.setTipoNomina(entity.getTipoNomina());
        dto.setFormula(entity.getFormula());
        dto.setValor(entity.getValor());
        dto.setListConceptoSalarial(nominaConceptoSaConversor
                .getNominaConceptSalListFromEntity(entity.getNominaConceptoSalarialList()));
        return dto;
    }
    
    public ConceptoNomina getConceptoNominaFromDto(final ConceptoNominaDto dto) {
        final ConceptoNomina entity = new ConceptoNomina();
        entity.setIdConceptoNomina(dto.getIdConceptoNomina());
        entity.setNombre(dto.getNombre());
        entity.setImprimirDesprendible(dto.isImprimirDesprendible());
        entity.setTipoContable(dto.getTipoContable());
        entity.setCantidad(dto.getCantidad());
        entity.setFormula(dto.getFormula());
        entity.setTipoNomina(dto.getTipoNomina());
        entity.setValor(dto.getValor());
        return entity;
    }
    
    public List<ConceptoNominaDto> getListConceptoNominaFromEntity(final List<ConceptoNomina> listEntity) {
        final List<ConceptoNominaDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getConceptoNominaFromEntity(entity));
            });
        }
        return listDto;
    }
    
}
