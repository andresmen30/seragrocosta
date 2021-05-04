/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.enums;

import java.math.BigDecimal;
import lombok.Getter;

/**
 *
 * @author USER
 */
public enum NivelArl {

    NIVEL_1("Nivel 1", BigDecimal.valueOf(0.522)),
    NIVEL_2("Nivel 2", BigDecimal.valueOf(1.044)),
    NIVEL_3("Nivel 3", BigDecimal.valueOf(2.436)),
    NIVEL_4("Nivel 4", BigDecimal.valueOf(4.350)),
    NIVEL_5("Nivel 5", BigDecimal.valueOf(6.960));

    @Getter
    private final String descripcion;
    @Getter
    private final BigDecimal porcentaje;

    private NivelArl(final String descripcion, final BigDecimal porcentaje) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

}
