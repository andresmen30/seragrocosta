/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.config;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author USER
 */
@Named
@ApplicationScoped
public class BundleConfig implements Serializable {

    public String loadBublePropertie(final String bundle,
            final String namePropertie) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final ResourceBundle resourceBundle = context.getApplication()
                .getResourceBundle(context, bundle);
        return resourceBundle.getString(namePropertie);
    }

}
