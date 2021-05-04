
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.security;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;

/**
 *
 * @author Usuario
 */
@WebFilter(filterName = "Security", urlPatterns = {"/pages/*"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ERROR})
public class Security extends HttpFilter {

    @Override
    public void doFilter(final HttpServletRequest request, final HttpServletResponse response,
            final HttpSession session, final FilterChain chain)
            throws ServletException, IOException {

        if (!request.getRequestURI().startsWith(request.getContextPath() 
                + ResourceHandler.RESOURCE_IDENTIFIER)) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        }
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            Servlets.facesRedirect(request, response, "login");
        }
    }

}
