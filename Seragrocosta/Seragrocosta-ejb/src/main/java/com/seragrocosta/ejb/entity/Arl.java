/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "arl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arl.findAll", query = "SELECT a FROM Arl a")
    , @NamedQuery(name = "Arl.findByIdArl", query = "SELECT a FROM Arl a WHERE a.idArl = :idArl")
    , @NamedQuery(name = "Arl.findByNombre", query = "SELECT a FROM Arl a WHERE a.nombre = :nombre")})
@Getter
@Setter
public class Arl implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArl", fetch = FetchType.EAGER)
    private List<Nomina> nominaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_arl")
    private Integer idArl;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nit")
    private String nit;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;

    public Arl() {
    }

    public Arl(Integer idArl) {
        this.idArl = idArl;
    }

    public Arl(Integer idArl, String nombre) {
        this.idArl = idArl;
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArl != null ? idArl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arl)) {
            return false;
        }
        Arl other = (Arl) object;
        if ((this.idArl == null && other.idArl != null) || (this.idArl != null && !this.idArl.equals(other.idArl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Arl[ idArl=" + idArl + " ]";
    }

    @XmlTransient
    public List<Nomina> getNominaList() {
        return nominaList;
    }

    public void setNominaList(List<Nomina> nominaList) {
        this.nominaList = nominaList;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
