/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class ParametrizacionNominaDto implements Serializable {
    
    private Integer idParametrizacion;
    private BigDecimal salarioMinimo;
    private BigDecimal auxilioTransporte;
    private BigDecimal saludEmpleado;
    private BigDecimal saludEmpresa;
    private BigDecimal interesCesantias;
    private BigDecimal primaServicios;
    private BigDecimal vacaciones;
    private BigDecimal pension;
    private BigDecimal cajaCompensacion;
    private BigDecimal cesantias;
    
}
