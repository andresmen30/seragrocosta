/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.ArlConversor;
import com.seragrocosta.ejb.dto.ArlDto;
import com.seragrocosta.ejb.entity.Arl;
import com.seragrocosta.ejb.facade.ArlFacade;
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
public class ArlDao {

    @EJB
    private ArlFacade arlFacade;

    @EJB
    private ArlConversor arlConversor;

    public List<ArlDto> getArlList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Arl> entity = arlFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return arlConversor.getListArlFromEntity(entity);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return arlFacade.registersNumber(filters);
    }

    public void save(final ArlDto dto) {
        arlFacade.save(dto);
    }

    public boolean isExistNit(final ArlDto dto) {
        return arlFacade.isExistNit(dto);
    }

    public void delete(final ArlDto dto) {
        arlFacade.delete(dto);
    }

    public List<ArlDto> getListArls() {
        return arlConversor.getListArlFromEntity(arlFacade.findAll());
    }

}
