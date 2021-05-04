/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.ParametrizacionNominaDto;
import com.seragrocosta.web.dao.ParametrizacionNominaDao;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Named(value = "parametrizacionNominaMb")
@ViewScoped
public class ParametrizacionNominaMb implements Serializable {
    
    @Inject
    private ParametrizacionNominaDao parametrizacionDao;
    
    @Inject
    private MessageUtil messageUtil;
    
    @Getter
    @Setter
    private ParametrizacionNominaDto parametrizacionDto;

    /**
     * Creates a new instance of ParametrizacionMb
     */
    public ParametrizacionNominaMb() {
        super();
    }
    
    @PostConstruct
    public void init() {
        parametrizacionDto = parametrizacionDao.getParametrizacionDto();
        
    }
    
    public void save() {
        parametrizacionDao.save(this.parametrizacionDto);
        messageUtil.addMessageInfo("Se ha guardado correctamente");
    }
    
    public void clear() {
        parametrizacionDto.setAuxilioTransporte(BigDecimal.ZERO);
        parametrizacionDto.setCajaCompensacion(BigDecimal.ZERO);
        parametrizacionDto.setCesantias(BigDecimal.ZERO);
        parametrizacionDto.setInteresCesantias(BigDecimal.ZERO);
        parametrizacionDto.setPension(BigDecimal.ZERO);
        parametrizacionDto.setPrimaServicios(BigDecimal.ZERO);
        parametrizacionDto.setSalarioMinimo(BigDecimal.ZERO);
        parametrizacionDto.setSaludEmpleado(BigDecimal.ZERO);
        parametrizacionDto.setSaludEmpresa(BigDecimal.ZERO);
        parametrizacionDto.setVacaciones(BigDecimal.ZERO);
        
    }
    
}
