/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.LoginDto;
import com.seragrocosta.ejb.entity.Login;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class LoginConversor implements Serializable {

    public LoginDto getLoginFromDto(final Login loginEntity) {
        final LoginDto loginDto = new LoginDto();
        loginDto.setIdLogin(loginEntity.getIdLogin());
        loginDto.setNamesUser(loginEntity.getNamesUser());
        loginDto.setEmail(loginEntity.getEmail());
        loginDto.setPassword(loginEntity.getPassword());
        loginDto.setSurnamesUser(loginEntity.getSurnamesUser());
        loginDto.setStatus(loginEntity.getStatus());
        return loginDto;
    }

    public List<LoginDto> getLoginListFromDto(final List<Login> loginListEntity) {
        final List<LoginDto> listLoginDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(loginListEntity)) {
            loginListEntity.stream().forEach(loginEntity -> {
                listLoginDto.add(getLoginFromDto(loginEntity));
            });
        }
        return listLoginDto;
    }
}
