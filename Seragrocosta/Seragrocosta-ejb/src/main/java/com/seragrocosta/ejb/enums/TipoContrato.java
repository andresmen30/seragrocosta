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
public enum TipoContrato {

    TERMINO_FIJO("Término fijo"),
    TERMINO_INDEFINIDO("Término indefinido"),
    CONTRATO_OBRA_LABOR("Obra o labor");


    @Getter
    private final String description;

    private TipoContrato(final String description) {
        this.description = description;
    }

}
