
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.LiquidacionConversor;
import com.seragrocosta.ejb.dto.LiquidacionDto;
import com.seragrocosta.ejb.entity.Liquidacion;
import com.seragrocosta.ejb.facade.LiquidacionFacade;
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
public class LiquidacionDao implements Serializable {
    
    @EJB
    private LiquidacionFacade liquidacionFacade;
    
    @EJB
    private LiquidacionConversor liquidacionConversor;
    
    public List<LiquidacionDto> getPeriodosList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Liquidacion> liquidaciones = liquidacionFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return liquidacionConversor.getListLiquidacionFromEntity(liquidaciones);
    }
    
    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return liquidacionFacade.registersNumber(filters);
    }
    
    public LiquidacionDto save(final LiquidacionDto liquidacionDto) {
        return liquidacionConversor.getLiquidacionFromEntity(liquidacionFacade.save(liquidacionDto));
    }
    
}
