/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.CajaCompensacionConversor;
import com.seragrocosta.ejb.dto.CajaCompensacionDto;
import com.seragrocosta.ejb.dto.CajaCompensacionDto;
import com.seragrocosta.ejb.entity.CajaCompensacion;
import com.seragrocosta.ejb.entity.FondoPension;
import com.seragrocosta.ejb.facade.CajaCompensacionFacade;
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
public class CajaCompensacionDao {

    @EJB
    private CajaCompensacionFacade compensacionFacade;

    @EJB
    private CajaCompensacionConversor cajaCompensacionConversor;

    public List<CajaCompensacionDto> getCajaCompensacionList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<CajaCompensacion> entity = compensacionFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return cajaCompensacionConversor.getListCajaCompensacionFromEntity(entity);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return compensacionFacade.registersNumber(filters);
    }

    public void save(final CajaCompensacionDto dto) {
        compensacionFacade.save(dto);
    }

    public boolean isExistNit(final CajaCompensacionDto dto) {
        return compensacionFacade.isExistNit(dto);
    }

    public void delete(final CajaCompensacionDto dto) {
        compensacionFacade.delete(dto);
    }

    public List<CajaCompensacionDto> getListCajaCompensaciones() {
        return cajaCompensacionConversor.getListCajaCompensacionFromEntity(compensacionFacade.findAll());
    }

}
