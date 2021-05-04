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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "novedad_variable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NovedadVariable.findAll", query = "SELECT n FROM NovedadVariable n")
    , @NamedQuery(name = "NovedadVariable.findByIdNovedadVariable", query = "SELECT n FROM NovedadVariable n WHERE n.idNovedadVariable = :idNovedadVariable")
    , @NamedQuery(name = "NovedadVariable.findByValor", query = "SELECT n FROM NovedadVariable n WHERE n.valor = :valor")
    , @NamedQuery(name = "NovedadVariable.findByObservacion", query = "SELECT n FROM NovedadVariable n WHERE n.observacion = :observacion")})
public class NovedadVariable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_novedad_variable")
    private Integer idNovedadVariable;
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "id_concepto_nomina", referencedColumnName = "id_concepto_nomina")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConceptoNomina idConceptoNomina;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado idEmpleado;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Periodo idPeriodo;

    public NovedadVariable() {
    }

    public NovedadVariable(Integer idNovedadVariable) {
        this.idNovedadVariable = idNovedadVariable;
    }

    public NovedadVariable(Integer idNovedadVariable, BigDecimal valor) {
        this.idNovedadVariable = idNovedadVariable;
        this.valor = valor;
    }

    public Integer getIdNovedadVariable() {
        return idNovedadVariable;
    }

    public void setIdNovedadVariable(Integer idNovedadVariable) {
        this.idNovedadVariable = idNovedadVariable;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNovedadVariable != null ? idNovedadVariable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NovedadVariable)) {
            return false;
        }
        NovedadVariable other = (NovedadVariable) object;
        if ((this.idNovedadVariable == null && other.idNovedadVariable != null) || (this.idNovedadVariable != null && !this.idNovedadVariable.equals(other.idNovedadVariable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.mavenproject2.NovedadVariable[ idNovedadVariable=" + idNovedadVariable + " ]";
    }
    
}
