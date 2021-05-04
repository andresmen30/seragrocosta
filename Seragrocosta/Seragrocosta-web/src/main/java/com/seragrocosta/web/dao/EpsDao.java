/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.EpsConversor;
import com.seragrocosta.ejb.dto.ArlDto;
import com.seragrocosta.ejb.dto.EpsDto;
import com.seragrocosta.ejb.entity.Eps;
import com.seragrocosta.ejb.facade.EpsFacade;
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
public class EpsDao implements Serializable {

    @EJB
    private EpsFacade epsFacade;

    @EJB
    private EpsConversor epsConversor;

    public List<EpsDto> getEpsList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Eps> entity = epsFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return epsConversor.getListEpsFromEntity(entity);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return epsFacade.registersNumber(filters);
    }

    public void save(final EpsDto epsDto) {
        epsFacade.save(epsDto);
    }

    public boolean isExistNit(final EpsDto epsDto) {
        return epsFacade.isExistNit(epsDto);
    }

    public void delete(final EpsDto epsDto) {
        epsFacade.delete(epsDto);
    }

    public List<EpsDto> getListEps() {
        return epsConversor.getListEpsFromEntity(epsFacade.findAll());
    }

}
