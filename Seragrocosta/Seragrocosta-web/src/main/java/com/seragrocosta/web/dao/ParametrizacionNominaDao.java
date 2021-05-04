/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.ParametrizacionNominaConversor;
import com.seragrocosta.ejb.dto.ParametrizacionNominaDto;
import com.seragrocosta.ejb.entity.ParametrizacionNomina;
import com.seragrocosta.ejb.facade.ParametrizacionNominaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class ParametrizacionNominaDao {
    
    @EJB
    private ParametrizacionNominaConversor parametrizacionConversor;
    
    @EJB
    private ParametrizacionNominaFacade parametrizacionFacade;
    
    public ParametrizacionNominaDto getParametrizacionDto() {
        final List<ParametrizacionNomina> listEntity = parametrizacionFacade.findAll();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            return parametrizacionConversor.getParametrizacionFromEntity(listEntity.stream().findAny().get());
        }        
        return new ParametrizacionNominaDto();
        
    }
    
    public void save(final ParametrizacionNominaDto parametrizacionDto) {
        final ParametrizacionNomina entity = parametrizacionConversor
                .getParametrizacionFromDto(parametrizacionDto);
        if (entity.getIdParametrizacion() == null) {
            parametrizacionFacade.create(entity);
        } else {
            parametrizacionFacade.edit(entity);
        }
    }
    
}
