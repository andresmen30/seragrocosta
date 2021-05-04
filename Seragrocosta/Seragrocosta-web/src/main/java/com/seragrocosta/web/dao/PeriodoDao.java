/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.PeriodoConversor;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.ejb.entity.Periodo;
import com.seragrocosta.ejb.facade.PeriodoFacade;
import java.io.Serializable;
import java.time.LocalDate;
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
public class PeriodoDao implements Serializable {

    @EJB
    private PeriodoFacade periodoFacade;

    @EJB
    private PeriodoConversor periodoConversor;

    public List<PeriodosDto> getPeriodosList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Periodo> periodos = periodoFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return periodoConversor.getPeriodoListFromEntity(periodos);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return periodoFacade.registersNumber(filters);
    }

    public void save(final PeriodosDto periodosDto, final String... conceptosCheck) {
        periodoFacade.save(periodosDto, conceptosCheck);
    }

    public void delete(final PeriodosDto periodosDto) {
        periodoFacade.delete(periodosDto);
    }

    public boolean isValidateRangeDates(final Integer idPeriodo, final LocalDate startDate, final LocalDate endDate) {
        return periodoFacade.isValidateRangeDates(idPeriodo, startDate, endDate);
    }

    public List<PeriodosDto> getPeriodoList() {
        final List<Periodo> listEntity = this.periodoFacade.getListPeriods();
        return periodoConversor.getPeriodoListFromEntity(listEntity);
    }

    public List<PeriodosDto> getListPeriodsByStartAndEnd(final LocalDate startDate, final LocalDate endDate) {
        return periodoConversor.getPeriodoListFromEntity(periodoFacade.getListPeriodsByStartAndEnd(startDate, endDate));
    }
    
    public PeriodosDto getPeriodoById(final Integer id){
       return periodoConversor.getPeriodoFromEntity(periodoFacade.find(id));
    }

}
