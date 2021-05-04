/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.validator;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author USER
 */
@Stateless
public class ValidatorEjb implements Serializable {

    public <T> T getSingleResult(final TypedQuery<T> query) {
        return query.setMaxResults(NumberUtils.INTEGER_ONE).getResultList()
                .parallelStream().findFirst().orElse(null);
    }

}
