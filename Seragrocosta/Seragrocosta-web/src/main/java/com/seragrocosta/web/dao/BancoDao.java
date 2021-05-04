/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.BancoConversor;
import com.seragrocosta.ejb.dto.BancoDto;
import com.seragrocosta.ejb.entity.Banco;
import com.seragrocosta.ejb.facade.BancoFacade;
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
public class BancoDao implements Serializable {

    @EJB
    private BancoFacade bancoFacade;

    @EJB
    private BancoConversor bancoConversor;

    public List<BancoDto> getBancoList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Banco> entity = bancoFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return bancoConversor.getListBancoFromEntity(entity);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return bancoFacade.registersNumber(filters);
    }

    public void save(final BancoDto dto) {
        bancoFacade.save(dto);
    }

    public boolean isExistCode(final BancoDto dto) {
        return bancoFacade.isExistCode(dto);
    }

    public void delete(final BancoDto dto) {
        bancoFacade.delete(dto);
    }

    public List<BancoDto> getListBancos() {
        return bancoConversor.getListBancoFromEntity(bancoFacade.findAll());
    }
}
