/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "novedad_fija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NovedadFija.findAll", query = "SELECT n FROM NovedadFija n")
    , @NamedQuery(name = "NovedadFija.findByIdNovedadFija", query = "SELECT n FROM NovedadFija n WHERE n.idNovedadFija = :idNovedadFija")
    , @NamedQuery(name = "NovedadFija.findByCuotas", query = "SELECT n FROM NovedadFija n WHERE n.cuotas = :cuotas")
    , @NamedQuery(name = "NovedadFija.findByValor", query = "SELECT n FROM NovedadFija n WHERE n.valor = :valor")})
public class NovedadFija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_novedad_fija")
    private Integer idNovedadFija;
    @Basic(optional = false)
    @Column(name = "cuotas")
    private String cuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "id_concepto_nomina", referencedColumnName = "id_concepto_nomina")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConceptoNomina idConceptoNomina;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado idEmpleado;

    public NovedadFija() {
    }

    public NovedadFija(Integer idNovedadFija) {
        this.idNovedadFija = idNovedadFija;
    }

    public NovedadFija(Integer idNovedadFija, String cuotas, BigDecimal valor) {
        this.idNovedadFija = idNovedadFija;
        this.cuotas = cuotas;
        this.valor = valor;
    }

    public Integer getIdNovedadFija() {
        return idNovedadFija;
    }

    public void setIdNovedadFija(Integer idNovedadFija) {
        this.idNovedadFija = idNovedadFija;
    }

    public String getCuotas() {
        return cuotas;
    }

    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ConceptoNomina getIdConceptoNomina() {
        return idConceptoNomina;
    }

    public void setIdConceptoNomina(ConceptoNomina idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNovedadFija != null ? idNovedadFija.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NovedadFija)) {
            return false;
        }
        NovedadFija other = (NovedadFija) object;
        if ((this.idNovedadFija == null && other.idNovedadFija != null) || (this.idNovedadFija != null && !this.idNovedadFija.equals(other.idNovedadFija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.NovedadFija[ idNovedadFija=" + idNovedadFija + " ]";
    }
    
}
