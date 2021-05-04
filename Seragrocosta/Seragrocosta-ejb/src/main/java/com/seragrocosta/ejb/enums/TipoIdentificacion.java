/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.enums;

import lombok.Getter;

/**
 *
 * @author USER
 */
public enum TipoIdentificacion {

    CC("Cédula de ciudadania"),
    CE("Cédula de extranjería"),
    RC("Registro Civil"),
    PA("Pasaporte");

    @Getter
    private final String description;

    private TipoIdentificacion(final String description) {
        this.description = description;
    }

}
