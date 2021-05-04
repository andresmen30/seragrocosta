/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.security;

import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author USER
 */
@Stateless
public class ValidatorEncrypt {

    /**
     *
     * @param contrasena
     * @param hashedContrasena
     * @return
     */
    public boolean checkPass(final String contrasena, final String hashedContrasena) {
        return BCrypt.checkpw(contrasena,
                hashedContrasena);
    }

    /**
     *
     * @param password
     * @return
     */
    public String hashPassword(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
