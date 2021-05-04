/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.NovedadVariableConversor;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.dto.NovedadVariableDto;
import com.seragrocosta.ejb.entity.NovedadVariable;
import com.seragrocosta.ejb.facade.NovedadVariableFacade;
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
public class NovedadVariableDao implements Serializable {
    
    @EJB
    private NovedadVariableFacade novedadVariableFacade;
    
    @EJB
    private NovedadVariableConversor novedadVariableConversor;
    
    public List<NovedadVariableDto> getNovedadVariableList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<NovedadVariable> entity = novedadVariableFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return novedadVariableConversor.getListNovedadVariableFromEntity(entity);
    }
    
    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return novedadVariableFacade.registersNumber(filters);
    }
    
    public void saveNovedadVariable(final EmpleadoDto empleadoDto) {
        novedadVariableFacade.saveNovedadVariable(empleadoDto);
    }
    
}
