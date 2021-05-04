/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.LoginConversor;
import com.seragrocosta.ejb.dto.LoginDto;
import com.seragrocosta.ejb.entity.Login;
import com.seragrocosta.ejb.facade.LoginFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@Stateless
public class LoginDao implements Serializable {
    
    
    @EJB
    private LoginFacade iLoginView;
    
    @EJB
    private LoginConversor loginConversor;
    
    public LoginDto getLoginByEmail(final String email) {
        final Login login = iLoginView.getLoginByEmail(email);
        return loginConversor.getLoginFromDto(login);
    }
}
