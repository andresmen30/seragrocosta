/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import com.seragrocosta.ejb.enums.StatusUser;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class LoginDto implements Serializable {

    private int idLogin;
    private String namesUser;
    private String surnamesUser;
    private String email;
    private String password;
    private StatusUser status;

}
