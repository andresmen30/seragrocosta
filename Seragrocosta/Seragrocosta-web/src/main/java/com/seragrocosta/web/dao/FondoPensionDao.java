/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.FondoPensionConversor;
import com.seragrocosta.ejb.dto.FondoPensionDto;
import com.seragrocosta.ejb.entity.FondoPension;
import com.seragrocosta.ejb.facade.FondoPensionFacade;
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
public class FondoPensionDao implements Serializable {

    @EJB
    private FondoPensionFacade fondoPensionFacade;

    @EJB
    private FondoPensionConversor fondoPensionConversor;

    public List<FondoPensionDto> getFondoPensionList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<FondoPension> entity = fondoPensionFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return fondoPensionConversor.getListFondoPensionFromEntity(entity);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return fondoPensionFacade.registersNumber(filters);
    }

    public void save(final FondoPensionDto epsDto) {
        fondoPensionFacade.save(epsDto);
    }

    public boolean isExistNit(final FondoPensionDto dto) {
        return fondoPensionFacade.isExistNit(dto);
    }

    public void delete(final FondoPensionDto dto) {
        fondoPensionFacade.delete(dto);
    }

    public List<FondoPensionDto> getListFondoPensiones() {
        return fondoPensionConversor.getListFondoPensionFromEntity(fondoPensionFacade.findAll());
    }
}
