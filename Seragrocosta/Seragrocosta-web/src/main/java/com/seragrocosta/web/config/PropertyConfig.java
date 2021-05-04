/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.config;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

@Dependent
public class PropertyConfig {

    private static final String MANDATORY_PARAM_MISSING = "No se encontró una definición para un parámetro de configuración obligatorio : '{0}'";

    @Produces
    @Property
    public String injectConfigurationString(final InjectionPoint ip) throws IllegalStateException {
        final Property param = ip.getAnnotated().getAnnotation(Property.class);
        if (StringUtils.isBlank(param.key())) {
            return param.defaultValue();
        }
        String value;
        try {
            value = loadBublePropertie(param.bundle(), param.key());
            if (StringUtils.isBlank(value)) {
                if (param.mandatory()) {
                    throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                            new Object[]{param.key()}));
                } else {
                    return param.defaultValue();
                }
            }
            return value;
        } catch (final MissingResourceException e) {
            if (param.mandatory()) {
                throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                        new Object[]{param.key()}));
            }
            return StringUtils.EMPTY;
        }
    }

    @Produces
    @Property
    public int injectConfigurationInt(final InjectionPoint ip) throws IllegalStateException {
        final Property param = ip.getAnnotated().getAnnotation(Property.class);
        if (StringUtils.isBlank(param.key())) {
            return Integer.parseInt(param.defaultValue());
        }
        String value;
        try {
            value = loadBublePropertie(param.bundle(), param.key());
            if (StringUtils.isBlank(value)) {
                if (param.mandatory()) {
                    throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                            new Object[]{param.key()}));
                } else {
                    return Integer.parseInt(param.defaultValue());
                }
            }
            return Integer.parseInt(value);
        } catch (final MissingResourceException e) {
            if (param.mandatory()) {
                throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                        new Object[]{param.key()}));
            }
            return NumberUtils.INTEGER_ZERO;
        }
    }

    @Produces
    @Property
    public long injectConfigurationLong(final InjectionPoint ip) throws IllegalStateException {
        final Property param = ip.getAnnotated().getAnnotation(Property.class);
        if (StringUtils.isBlank(param.key())) {
            return Long.parseLong(param.defaultValue());
        }
        String value;
        try {
            value = loadBublePropertie(param.bundle(), param.key());
            if (StringUtils.isBlank(value)) {
                if (param.mandatory()) {
                    throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                            new Object[]{param.key()}));
                } else {
                    return Long.parseLong(param.defaultValue());
                }
            }
            return Long.parseLong(value);
        } catch (final MissingResourceException e) {
            if (param.mandatory()) {
                throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                        new Object[]{param.key()}));
            }
            return NumberUtils.INTEGER_ZERO;
        }
    }

    @Produces
    @Property
    public boolean injectConfigurationBoolean(final InjectionPoint ip) throws IllegalStateException {
        final Property param = ip.getAnnotated().getAnnotation(Property.class);
        if (StringUtils.isBlank(param.key())) {
            return Boolean.parseBoolean(param.defaultValue());
        }
        String value;
        try {
            value = loadBublePropertie(param.bundle(), param.key());
            if (StringUtils.isBlank(value)) {
                if (param.mandatory()) {
                    throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                            new Object[]{param.key()}));
                } else {
                    return Boolean.parseBoolean(param.defaultValue());
                }
            }
            return Boolean.parseBoolean(param.defaultValue());
        } catch (final MissingResourceException e) {
            if (param.mandatory()) {
                throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING,
                        new Object[]{param.key()}));
            }
            return false;
        }
    }

    private static String loadBublePropertie(final String bundleProperty,
            final String value) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final ResourceBundle bundle = context.getApplication().getResourceBundle(context, bundleProperty);
        return bundle.getString(value);
    }

}
