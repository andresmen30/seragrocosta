/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import com.seragrocosta.ejb.enums.BancoTipoCuenta;
import com.seragrocosta.ejb.enums.EstadoCivil;
import com.seragrocosta.ejb.enums.NivelArl;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class NominaDto {

    private Integer idNomina;
    private BigDecimal asignacionBasica;
    private EstadoCivil estadoCivil;
    private boolean libretaMilitar;
    private LocalDate fechaInicialCajaCompensacion;
    private LocalDate fechaInicialArl;
    private LocalDate fechaInicialEps;
    private LocalDate fechaInicialFondoPension;
    private LocalDate fechaInicialCesantias;
    private LocalDate fechaFinalCajaCompensacion;
    private LocalDate fechaFinalArl;
    private LocalDate fechaFinalEps;
    private LocalDate fechaFinalFondoPension;
    private LocalDate fechaFinalCesantias;
    private AreaDto idArea;
    private ArlDto idArl;
    private FondoPensionDto fondoPensionDto;
    private CajaCompensacionDto idCajaCompensacion;
    private CargoDto idCargo;
    private EpsDto idEps;
    private String bancoCuenta;
    private BancoTipoCuenta bancoTipoCuenta;
    private BancoDto idBanco;
    private CesantiasDto cesantiasDto;
    private NivelArl nivelArl;
}
