/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.AreaConversor;
import com.seragrocosta.ejb.dto.AreaDto;
import com.seragrocosta.ejb.entity.Area;
import com.seragrocosta.ejb.facade.AreaFacade;
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
public class AreaDao implements Serializable {

    @EJB
    private AreaFacade areaFacade;

    @EJB
    private AreaConversor areaConversor;

    public List<AreaDto> getAreaList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Area> entity = areaFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return areaConversor.getListAreaFromEntity(entity);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return areaFacade.registersNumber(filters);
    }

    public void save(final AreaDto dto) {
        areaFacade.save(dto);
    }

    public boolean isExistNombre(final AreaDto dto) {
        return areaFacade.isExistNombre(dto);
    }

    public void delete(final AreaDto dto) {
        areaFacade.delete(dto);
    }
    
    public List<AreaDto> getListAreas(){
      return areaConversor.getListAreaFromEntity(areaFacade.findAll());
    }

}
