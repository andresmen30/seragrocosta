/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.CesantiasConversor;
import com.seragrocosta.ejb.dto.CesantiasDto;
import com.seragrocosta.ejb.entity.Cesantias;
import com.seragrocosta.ejb.facade.CesantiasFacade;
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
public class CesantiasDao implements Serializable {
    
    @EJB
    private CesantiasFacade cesantiasFacade;
    
    @EJB
    private CesantiasConversor cesantiasConversor;
    
    public List<CesantiasDto> getCesantiasList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Cesantias> entity = cesantiasFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return cesantiasConversor.getListCesantiasFtomEntity(entity);
    }
    
    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return cesantiasFacade.registersNumber(filters);
    }
    
    public void save(final CesantiasDto dto) {
        cesantiasFacade.save(dto);
    }
    
    public void delete(final CesantiasDto dto) {
        cesantiasFacade.delete(dto);
    }
    
    public List<CesantiasDto> getListCesantias() {
        return cesantiasConversor.getListCesantiasFtomEntity(cesantiasFacade.findAll());
    }
    
    public boolean isExistNit(final CesantiasDto cesantiasDto){
         return cesantiasFacade.isExistNit(cesantiasDto);
    }
}
