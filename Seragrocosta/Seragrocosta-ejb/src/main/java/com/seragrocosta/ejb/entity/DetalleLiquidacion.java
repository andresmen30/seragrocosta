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
@Table(name = "detalle_liquidacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleLiquidacion.findAll", query = "SELECT d FROM DetalleLiquidacion d")
    , @NamedQuery(name = "DetalleLiquidacion.findByIdDetalleLiquidacion", query = "SELECT d FROM DetalleLiquidacion d WHERE d.idDetalleLiquidacion = :idDetalleLiquidacion")
    , @NamedQuery(name = "DetalleLiquidacion.findByDiasTrabajados", query = "SELECT d FROM DetalleLiquidacion d WHERE d.diasTrabajados = :diasTrabajados")
    , @NamedQuery(name = "DetalleLiquidacion.findBySalud", query = "SELECT d FROM DetalleLiquidacion d WHERE d.salud = :salud")
    , @NamedQuery(name = "DetalleLiquidacion.findByPension", query = "SELECT d FROM DetalleLiquidacion d WHERE d.pension = :pension")
    , @NamedQuery(name = "DetalleLiquidacion.findByAuxilioTransporte", query = "SELECT d FROM DetalleLiquidacion d WHERE d.auxilioTransporte = :auxilioTransporte")
    , @NamedQuery(name = "DetalleLiquidacion.findByTotalDevengado", query = "SELECT d FROM DetalleLiquidacion d WHERE d.totalDevengado = :totalDevengado")
    , @NamedQuery(name = "DetalleLiquidacion.findByTotalDeducido", query = "SELECT d FROM DetalleLiquidacion d WHERE d.totalDeducido = :totalDeducido")
    , @NamedQuery(name = "DetalleLiquidacion.findByTotalNeto", query = "SELECT d FROM DetalleLiquidacion d WHERE d.totalNeto = :totalNeto")})
public class DetalleLiquidacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_liquidacion")
    private Integer idDetalleLiquidacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario_devengado")
    private BigDecimal salarioDevengado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias_trabajados")
    private int diasTrabajados;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "salud")
    private BigDecimal salud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pension")
    private BigDecimal pension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auxilio_transporte")
    private BigDecimal auxilioTransporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_devengado")
    private BigDecimal totalDevengado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_deducido")
    private BigDecimal totalDeducido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_neto")
    private BigDecimal totalNeto;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado idEmpleado;
    @JoinColumn(name = "id_liquidacion", referencedColumnName = "id_liquidacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Liquidacion idLiquidacion;

    public DetalleLiquidacion() {
    }

    public DetalleLiquidacion(Integer idDetalleLiquidacion) {
        this.idDetalleLiquidacion = idDetalleLiquidacion;
    }

    public DetalleLiquidacion(Integer idDetalleLiquidacion, int diasTrabajados, BigDecimal salud, BigDecimal pension, BigDecimal auxilioTransporte, BigDecimal totalDevengado, BigDecimal totalDeducido, BigDecimal totalNeto) {
        this.idDetalleLiquidacion = idDetalleLiquidacion;
        this.diasTrabajados = diasTrabajados;
        this.salud = salud;
        this.pension = pension;
        this.auxilioTransporte = auxilioTransporte;
        this.totalDevengado = totalDevengado;
        this.totalDeducido = totalDeducido;
        this.totalNeto = totalNeto;
    }

    public Integer getIdDetalleLiquidacion() {
        return idDetalleLiquidacion;
    }

    public void setIdDetalleLiquidacion(Integer idDetalleLiquidacion) {
        this.idDetalleLiquidacion = idDetalleLiquidacion;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public BigDecimal getSalud() {
        return salud;
    }

    public void setSalud(BigDecimal salud) {
        this.salud = salud;
    }

    public BigDecimal getPension() {
        return pension;
    }

    public void setPension(BigDecimal pension) {
        this.pension = pension;
    }

    public BigDecimal getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(BigDecimal auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    public BigDecimal getTotalDevengado() {
        return totalDevengado;
    }

    public void setTotalDevengado(BigDecimal totalDevengado) {
        this.totalDevengado = totalDevengado;
    }

    public BigDecimal getTotalDeducido() {
        return totalDeducido;
    }

    public void setTotalDeducido(BigDecimal totalDeducido) {
        this.totalDeducido = totalDeducido;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Liquidacion getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(Liquidacion idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    /**
     * @return the salarioDevengado
     */
    public BigDecimal getSalarioDevengado() {
        return salarioDevengado;
    }

    /**
     * @param salarioDevengado the salarioDevengado to set
     */
    public void setSalarioDevengado(BigDecimal salarioDevengado) {
        this.salarioDevengado = salarioDevengado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleLiquidacion != null ? idDetalleLiquidacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleLiquidacion)) {
            return false;
        }
        DetalleLiquidacion other = (DetalleLiquidacion) object;
        if ((this.idDetalleLiquidacion == null && other.idDetalleLiquidacion != null) || (this.idDetalleLiquidacion != null && !this.idDetalleLiquidacion.equals(other.idDetalleLiquidacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.mavenproject3.DetalleLiquidacion[ idDetalleLiquidacion=" + idDetalleLiquidacion + " ]";
    }

}
