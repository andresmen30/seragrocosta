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
public enum TipoNomina {

    VALOR("Valor"),
    SEPARADO("Formula");

    @Getter
    private final String description;

    private TipoNomina(final String description) {
        this.description = description;
    }

}
