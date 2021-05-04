/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class NovedadVariableDto {

    private Integer idNovedadVariable;
    private BigDecimal valor;
    private String observacion;
    private ConceptoNominaDto idConceptoNomina;
    private PeriodosDto idPeriodo;

}
