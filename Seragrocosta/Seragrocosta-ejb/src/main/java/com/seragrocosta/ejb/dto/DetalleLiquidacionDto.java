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
public class DetalleLiquidacionDto implements Serializable {
    
    private Integer idDetalleLiquidacion;
    private int diasTrabajados;
    private BigDecimal salud;
    private BigDecimal pension;
    private BigDecimal auxilioTransporte;
    private BigDecimal totalDevengado;
    private BigDecimal totalDeducido;
    private BigDecimal totalNeto;
    private BigDecimal salarioDevengado;
    private EmpleadoDto idEmpleado;
    private LiquidacionDto idLiquidacion;
}
