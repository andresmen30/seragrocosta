/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import com.seragrocosta.ejb.enums.TipoContable;
import com.seragrocosta.ejb.enums.TipoNomina;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class ConceptoNominaDto {

    private Integer idConceptoNomina;
    private String nombre;
    private TipoNomina tipoNomina;
    private TipoContable tipoContable;
    private String cantidad;
    private boolean imprimirDesprendible;
    private String formula;
    private String resultCalculator;
    private BigDecimal valor;
    private List<NominaConceptoSalarialDto> listConceptoSalarial;

}
