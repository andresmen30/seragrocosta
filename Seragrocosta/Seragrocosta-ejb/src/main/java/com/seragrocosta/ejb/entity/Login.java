/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import com.seragrocosta.ejb.enums.StatusUser;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
    , @NamedQuery(name = "Login.findByIdLogin", query = "SELECT l FROM Login l WHERE l.idLogin = :idLogin")
    , @NamedQuery(name = "Login.findByEmail", query = "SELECT l FROM Login l WHERE l.email = :email")
    , @NamedQuery(name = "Login.findByNameUser", query = "SELECT l FROM Login l WHERE l.namesUser = :namesUser")
    , @NamedQuery(name = "Login.findByStatus", query = "SELECT l FROM Login l WHERE l.status = :status")
    , @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password")})
@Getter
@Setter
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_login")
    private Integer idLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "names_user")
    private String namesUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "surnames_user")
    private String surnamesUser;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusUser status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "password")
    private String password;

    public Login() {
        super();
    }

    public Login(final Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Login(final Integer idLogin, final String email, final String surnamesUser,
            final String namesUser, final StatusUser status, final String password) {
        this.idLogin = idLogin;
        this.email = email;
        this.status = status;
        this.namesUser = namesUser;
        this.surnamesUser = surnamesUser;
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        return !((this.idLogin == null && other.idLogin != null)
                || (this.idLogin != null && !this.idLogin.equals(other.idLogin)));
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.Login[ idLogin=" + idLogin + " ]";
    }

}
