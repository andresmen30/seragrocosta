/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.ConceptoNominaConversor;
import com.seragrocosta.ejb.dto.ConceptoNominaDto;
import com.seragrocosta.ejb.entity.ConceptoNomina;
import com.seragrocosta.ejb.facade.ConceptoNominaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author USER
 */
@Stateless
public class ConceptoNominaDao implements Serializable {
    
    @EJB
    private ConceptoNominaConversor conceptoNominaConversor;
    
    @EJB
    private ConceptoNominaFacade conceptoNominaFacade;
    
    public List<ConceptoNominaDto> getList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<ConceptoNomina> entity = conceptoNominaFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return conceptoNominaConversor.getListConceptoNominaFromEntity(entity);
    }
    
    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return conceptoNominaFacade.registersNumber(filters);
    }
    
    public List<ConceptoNominaDto> completeConceptoNomina() {
        return conceptoNominaConversor.getListConceptoNominaFromEntity(conceptoNominaFacade.completeConceptoNomina());
    }
    
    public void save(final ConceptoNominaDto dto, final String... conceptosCheck) {
        conceptoNominaFacade.save(dto, conceptosCheck);
    }
    
}
