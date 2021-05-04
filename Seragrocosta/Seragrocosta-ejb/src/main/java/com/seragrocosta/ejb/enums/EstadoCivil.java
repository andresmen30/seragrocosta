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
public enum EstadoCivil {

    CASADO("Casado"),
    SEPARADO("Separado"),
    SOLTERO("Soltero"),
    UNION_LIBRE("Unión libre"),
    VIUDO("Viudo");


    @Getter
    private final String description;

    private EstadoCivil(final String description) {
        this.description = description;
    }

}
