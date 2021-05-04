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
public enum ConditionBoolean {

    SI("Si", true),
    NO("No", false);

    @Getter
    private final String description;
    @Getter
    private final boolean condition;

    private ConditionBoolean(final String description, final boolean condition) {
        this.condition = condition;
        this.description = description;
    }

}
