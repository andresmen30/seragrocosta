/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.NovedadFijaConversor;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.dto.NovedadFijaDto;
import com.seragrocosta.ejb.entity.NovedadFija;
import com.seragrocosta.ejb.facade.NovedadFijaFacade;
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
public class NovedadFijaDao implements Serializable {
    
    @EJB
    private NovedadFijaFacade novedadEmpleadoFacade;
    
    @EJB
    private NovedadFijaConversor novedadEmpleadoConversor;
    
    public List<NovedadFijaDto> getNovedadEmpleadoList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<NovedadFija> entity = novedadEmpleadoFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return novedadEmpleadoConversor.getListNovedadEmpleadoFromEntity(entity);
    }
    
    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return novedadEmpleadoFacade.registersNumber(filters);
    }
    
    public void saveNovedadEmpleado(final EmpleadoDto empleadoDto) {
        novedadEmpleadoFacade.saveNovedadEmpleado(empleadoDto);
    }
    
}
