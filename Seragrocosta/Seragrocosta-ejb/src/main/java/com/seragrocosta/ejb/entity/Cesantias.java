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
@Table(name = "cesantias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cesantias.findAll", query = "SELECT c FROM Cesantias c")
    , @NamedQuery(name = "Cesantias.findByIdCesantias", query = "SELECT c FROM Cesantias c WHERE c.idCesantias = :idCesantias")
    , @NamedQuery(name = "Cesantias.findByNombre", query = "SELECT c FROM Cesantias c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cesantias.findByFechaCreacion", query = "SELECT c FROM Cesantias c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Cesantias.findByNit", query = "SELECT c FROM Cesantias c WHERE c.nit = :nit")
    , @NamedQuery(name = "Cesantias.findByDescripcion", query = "SELECT c FROM Cesantias c WHERE c.descripcion = :descripcion")})
@Getter
@Setter
public class Cesantias implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCesantias", fetch = FetchType.EAGER)
    private List<Nomina> nominaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cesantias")
    private Integer idCesantias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
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

    public Cesantias() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCesantias != null ? idCesantias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cesantias)) {
            return false;
        }
        Cesantias other = (Cesantias) object;
        if ((this.idCesantias == null && other.idCesantias != null) || (this.idCesantias != null && !this.idCesantias.equals(other.idCesantias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.Cesantias[ idCesantias=" + idCesantias + " ]";
    }

    @XmlTransient
    public List<Nomina> getNominaList() {
        return nominaList;
    }

    public void setNominaList(List<Nomina> nominaList) {
        this.nominaList = nominaList;
    }
    
}
