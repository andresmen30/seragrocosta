/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.CargoConversor;
import com.seragrocosta.ejb.dto.CargoDto;
import com.seragrocosta.ejb.entity.Cargo;
import com.seragrocosta.ejb.facade.CargoFacade;
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
public class CargoDao implements Serializable {

    @EJB
    private CargoFacade cargoFacade;

    @EJB
    private CargoConversor cargoConversor;

    public List<CargoDto> getCargonList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Cargo> entity = cargoFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return cargoConversor.getListCargoDtoFromEntity(entity);
    }

    public List<CargoDto> getListCargos() {
      return cargoConversor.getListCargoDtoFromEntity(cargoFacade.findAll());
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return cargoFacade.registersNumber(filters);
    }

    public void save(final CargoDto dto) {
        cargoFacade.save(dto);
    }

    public boolean isExistNombre(final CargoDto dto) {
        return cargoFacade.isExistNombre(dto);
    }

    public void delete(final CargoDto dto) {
        cargoFacade.delete(dto);
    }

}
