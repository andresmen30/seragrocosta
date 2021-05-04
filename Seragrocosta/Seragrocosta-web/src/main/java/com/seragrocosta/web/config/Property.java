/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    /**
     * Bundle key
     *
     * @return a valid bundle key or ""
     */
    @Nonbinding
    String key() default "";

    /**
     * Bundle key
     *
     * @return a valid bundle key or ""
     */
    @Nonbinding
    String bundle() default "";

    /**
     * Is it a mandatory property
     *
     * @return true if mandator
     */
    @Nonbinding
    boolean mandatory() default false;

    /**
     * Default value if not provided
     *
     * @return default value or ""
     */
    @Nonbinding
    String defaultValue() default "";
}
