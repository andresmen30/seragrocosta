/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.LoginDto;
import com.seragrocosta.web.dao.LoginDao;
import com.seragrocosta.web.security.ValidatorEncrypt;
import com.seragrocosta.web.util.JsfUtil;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author USER
 */
@Named
@SessionScoped
public class LoginMb implements Serializable {

    @Inject
    private LoginDao loginDao;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private ValidatorEncrypt validatorEncrypt;

    @Inject
    private JsfUtil jsfUtil;

    @Getter
    @Setter
    private LoginDto loginDto;

    @PostConstruct
    public void initializate() {
        loginDto = new LoginDto();
    }

    public String logIn() {
        final LoginDto login = loginDao.getLoginByEmail(this.loginDto.getEmail());
        if (null == login) {
            messageUtil.addMessageError("El correo no existe en el sistema");
            return StringUtils.EMPTY;
        } else {
            if (validatorEncrypt.checkPass(this.loginDto.getPassword(),
                    login.getPassword())) {
                this.loginDto = login;
                jsfUtil.setAtttributeLogin("user", this.loginDto);
                return "pretty:home";
            } else {
                messageUtil.addMessageError("Contraseña incorrecta");
                return StringUtils.EMPTY;
            }
        }
    }

    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "pretty:login";
    }
}
