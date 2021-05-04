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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "parametrizacion_nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrizacionNomina.findAll", query = "SELECT p FROM ParametrizacionNomina p")
    , @NamedQuery(name = "ParametrizacionNomina.findByIdParametrizacion", query = "SELECT p FROM ParametrizacionNomina p WHERE p.idParametrizacion = :idParametrizacion")
    , @NamedQuery(name = "ParametrizacionNomina.findBySalarioMinimo", query = "SELECT p FROM ParametrizacionNomina p WHERE p.salarioMinimo = :salarioMinimo")
    , @NamedQuery(name = "ParametrizacionNomina.findByAuxilioTransporte", query = "SELECT p FROM ParametrizacionNomina p WHERE p.auxilioTransporte = :auxilioTransporte")
    , @NamedQuery(name = "ParametrizacionNomina.findBySaludEmpresa", query = "SELECT p FROM ParametrizacionNomina p WHERE p.saludEmpresa = :saludEmpresa")
    , @NamedQuery(name = "ParametrizacionNomina.findByPension", query = "SELECT p FROM ParametrizacionNomina p WHERE p.pension = :pension")
    , @NamedQuery(name = "ParametrizacionNomina.findByCajaCompensacion", query = "SELECT p FROM ParametrizacionNomina p WHERE p.cajaCompensacion = :cajaCompensacion")
    , @NamedQuery(name = "ParametrizacionNomina.findBySaludEmpleado", query = "SELECT p FROM ParametrizacionNomina p WHERE p.saludEmpleado = :saludEmpleado")
    , @NamedQuery(name = "ParametrizacionNomina.findByInteresCesantias", query = "SELECT p FROM ParametrizacionNomina p WHERE p.interesCesantias = :interesCesantias")
    , @NamedQuery(name = "ParametrizacionNomina.findByPrimaServicios", query = "SELECT p FROM ParametrizacionNomina p WHERE p.primaServicios = :primaServicios")
    , @NamedQuery(name = "ParametrizacionNomina.findByVacaciones", query = "SELECT p FROM ParametrizacionNomina p WHERE p.vacaciones = :vacaciones")})
public class ParametrizacionNomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parametrizacion")
    private Integer idParametrizacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario_minimo")
    private BigDecimal salarioMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auxilio_transporte")
    private BigDecimal auxilioTransporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salud_empresa")
    private BigDecimal saludEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pension")
    private BigDecimal pension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_compensacion")
    private BigDecimal cajaCompensacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salud_empleado")
    private BigDecimal saludEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "interes_cesantias")
    private BigDecimal interesCesantias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prima_servicios")
    private BigDecimal primaServicios;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vacaciones")
    private BigDecimal vacaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cesantias")
    private BigDecimal cesantias;

    public ParametrizacionNomina() {
    }

    public ParametrizacionNomina(Integer idParametrizacion) {
        this.idParametrizacion = idParametrizacion;
    }

    public ParametrizacionNomina(Integer idParametrizacion, BigDecimal salarioMinimo, BigDecimal auxilioTransporte, BigDecimal saludEmpresa, BigDecimal pension, BigDecimal cajaCompensacion, BigDecimal saludEmpleado, BigDecimal interesCesantias, BigDecimal primaServicios, BigDecimal vacaciones) {
        this.idParametrizacion = idParametrizacion;
        this.salarioMinimo = salarioMinimo;
        this.auxilioTransporte = auxilioTransporte;
        this.saludEmpresa = saludEmpresa;
        this.pension = pension;
        this.cajaCompensacion = cajaCompensacion;
        this.saludEmpleado = saludEmpleado;
        this.interesCesantias = interesCesantias;
        this.primaServicios = primaServicios;
        this.vacaciones = vacaciones;
    }

    public Integer getIdParametrizacion() {
        return idParametrizacion;
    }

    public void setIdParametrizacion(Integer idParametrizacion) {
        this.idParametrizacion = idParametrizacion;
    }

    public BigDecimal getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(BigDecimal salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public BigDecimal getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(BigDecimal auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    public BigDecimal getSaludEmpresa() {
        return saludEmpresa;
    }

    public void setSaludEmpresa(BigDecimal saludEmpresa) {
        this.saludEmpresa = saludEmpresa;
    }

    public BigDecimal getPension() {
        return pension;
    }

    public void setPension(BigDecimal pension) {
        this.pension = pension;
    }

    public BigDecimal getCajaCompensacion() {
        return cajaCompensacion;
    }

    public void setCajaCompensacion(BigDecimal cajaCompensacion) {
        this.cajaCompensacion = cajaCompensacion;
    }

    public BigDecimal getSaludEmpleado() {
        return saludEmpleado;
    }

    public void setSaludEmpleado(BigDecimal saludEmpleado) {
        this.saludEmpleado = saludEmpleado;
    }

    public BigDecimal getInteresCesantias() {
        return interesCesantias;
    }

    public void setInteresCesantias(BigDecimal interesCesantias) {
        this.interesCesantias = interesCesantias;
    }

    public BigDecimal getPrimaServicios() {
        return primaServicios;
    }

    public void setPrimaServicios(BigDecimal primaServicios) {
        this.primaServicios = primaServicios;
    }

    public BigDecimal getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(BigDecimal vacaciones) {
        this.vacaciones = vacaciones;
    }
    
     /**
     * @return the cesantias
     */
    public BigDecimal getCesantias() {
        return cesantias;
    }

    /**
     * @param cesantias the cesantias to set
     */
    public void setCesantias(BigDecimal cesantias) {
        this.cesantias = cesantias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametrizacion != null ? idParametrizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrizacionNomina)) {
            return false;
        }
        ParametrizacionNomina other = (ParametrizacionNomina) object;
        if ((this.idParametrizacion == null && other.idParametrizacion != null) || (this.idParametrizacion != null && !this.idParametrizacion.equals(other.idParametrizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.ParametrizacionNomina[ idParametrizacion=" + idParametrizacion + " ]";
    }
    
}
