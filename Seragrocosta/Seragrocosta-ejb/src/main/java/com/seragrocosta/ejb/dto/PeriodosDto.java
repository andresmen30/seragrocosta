/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import com.seragrocosta.ejb.enums.EstadoPeriodo;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class PeriodosDto implements Serializable {

    private Integer idPeriodo;
    private String nombre;
    private String nombreFechas;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private EstadoPeriodo estadoPeriodo;
    private List<PeriodoConceptoSalarialDto> periodoConceptoSalarialList;

}
