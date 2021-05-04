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
@Table(name = "fondo_pension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FondoPension.findAll", query = "SELECT f FROM FondoPension f")
    , @NamedQuery(name = "FondoPension.findByIdFondoPension", query = "SELECT f FROM FondoPension f WHERE f.idFondoPension = :idFondoPension")
    , @NamedQuery(name = "FondoPension.findByNombre", query = "SELECT f FROM FondoPension f WHERE f.nombre = :nombre")})
@Getter
@Setter
public class FondoPension implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFondoPension", fetch = FetchType.EAGER)
    private List<Nomina> nominaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fondo_pension")
    private Integer idFondoPension;
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

    public FondoPension() {
    }

    public FondoPension(Integer idFondoPension) {
        this.idFondoPension = idFondoPension;
    }

    public FondoPension(Integer idFondoPension, String nombre) {
        this.idFondoPension = idFondoPension;
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFondoPension != null ? idFondoPension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FondoPension)) {
            return false;
        }
        FondoPension other = (FondoPension) object;
        if ((this.idFondoPension == null && other.idFondoPension != null) || (this.idFondoPension != null && !this.idFondoPension.equals(other.idFondoPension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.FondoPension[ idFondoPension=" + idFondoPension + " ]";
    }

    @XmlTransient
    public List<Nomina> getNominaList() {
        return nominaList;
    }

    public void setNominaList(List<Nomina> nominaList) {
        this.nominaList = nominaList;
    }

}
