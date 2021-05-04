/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.util;

/**
 *
 * @author USER
 */
public interface Constants {
    
    
    final Long[] NUMEROS_QUINCENAL = {16L,13L,14L};
    final Long[] NUMEROS_MENSUAL = {27L,29L,30L};
    
    final Integer THIRTY = 30;
    final Integer FIFTEEN = 15;

    String DEFAULT_HOME_PAGE = "index.xhtml";
    String DEFAULT_LOGIN_PAGE = "login.xhtml";
    String DEFAULT_ERROR_PAGE = "500.xhtml";
    String DEFAULT_ACCESS_DENIED_PAGE = "403.xhtml";
    String DEFAULT_EXPIRED_PAGE = "expired.xhtml";
    String DEFAULT_OPTIMISTIC_PAGE = "optimistic.xhtml";
    String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    String DEFAULT_PAGE_FORMAT = "xhtml";

    interface InitialParams {

        String DISABLE_FILTER = "com.github.adminfaces.DISABLE_FILTER";
        String LOGIN_PAGE = "com.github.adminfaces.LOGIN_PAGE";
        String ERROR_PAGE = "com.github.adminfaces.ERROR_PAGE";
        String INDEX_PAGE = "com.github.adminfaces.INDEX_PAGE";
    }

}
