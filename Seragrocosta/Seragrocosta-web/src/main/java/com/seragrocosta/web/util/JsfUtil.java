/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

/**
 *
 * @author USER
 */
@Stateless
public class JsfUtil {

    public void setAtttributeLogin(final String key, final Object value) {
        final FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put(key, value);
    }

    public Object getAtttributeLogin(final String key) {
        final FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().get(key);
    }

    public String getRemoteAddress() {
        final HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress != null) {
            ipAddress = ipAddress.replaceFirst(",.*", "");
        } else {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public void opendDialog(final String widgetVar) {
        final PrimeFaces current = PrimeFaces.current();
        final StringBuilder builder = new StringBuilder();
        builder.append("PF('").append(widgetVar)
                .append("').show()");
        current.executeScript(builder.toString());
    }

    public void hideDialog(final String widgetVar) {
        final PrimeFaces current = PrimeFaces.current();
        final StringBuilder builder = new StringBuilder();
        builder.append("PF('").append(widgetVar)
                .append("').hide()");
        current.executeScript(builder.toString());
    }

    public void updateComponenScheduleEvent(final String widgetVar) {
        final PrimeFaces current = PrimeFaces.current();
        final StringBuilder builder = new StringBuilder();
        builder.append("PF('").append(widgetVar)
                .append("').update()");
        current.executeScript(builder.toString());
    }

    public void updateComponent(final String id) {
        PrimeFaces.current().ajax().update(id);
    }

    public void reloadPage() {
        final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            System.err.print("Error al recargar la página " + e.getMessage());
        }

    }

}
