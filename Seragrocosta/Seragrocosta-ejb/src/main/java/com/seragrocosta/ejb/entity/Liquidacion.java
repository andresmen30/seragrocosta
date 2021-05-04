/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import com.seragrocosta.ejb.enums.TipoLiquidacion;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "liquidacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Liquidacion.findAll", query = "SELECT l FROM Liquidacion l")
    , @NamedQuery(name = "Liquidacion.findByIdLiquidacion", query = "SELECT l FROM Liquidacion l WHERE l.idLiquidacion = :idLiquidacion")
    , @NamedQuery(name = "Liquidacion.findByTipoLiquidacion", query = "SELECT l FROM Liquidacion l WHERE l.tipoLiquidacion = :tipoLiquidacion")
    , @NamedQuery(name = "Liquidacion.findByFechaLiquidacion", query = "SELECT l FROM Liquidacion l WHERE l.fechaLiquidacion = :fechaLiquidacion")})
public class Liquidacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_liquidacion")
    private Integer idLiquidacion;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_liquidacion")
    private TipoLiquidacion tipoLiquidacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_liquidacion")
    private LocalDate fechaLiquidacion;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Periodo idPeriodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLiquidacion", fetch = FetchType.EAGER)
    private List<DetalleLiquidacion> detalleLiquidacionList;

    public Liquidacion() {
    }

    public Liquidacion(Integer idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public Liquidacion(Integer idLiquidacion, TipoLiquidacion tipoLiquidacion, LocalDate fechaLiquidacion) {
        this.idLiquidacion = idLiquidacion;
        this.tipoLiquidacion = tipoLiquidacion;
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public Integer getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(Integer idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public TipoLiquidacion getTipoLiquidacion() {
        return tipoLiquidacion;
    }

    public void setTipoLiquidacion(TipoLiquidacion tipoLiquidacion) {
        this.tipoLiquidacion = tipoLiquidacion;
    }

    public LocalDate getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @XmlTransient
    public List<DetalleLiquidacion> getDetalleLiquidacionList() {
        return detalleLiquidacionList;
    }

    public void setDetalleLiquidacionList(List<DetalleLiquidacion> detalleLiquidacionList) {
        this.detalleLiquidacionList = detalleLiquidacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLiquidacion != null ? idLiquidacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Liquidacion)) {
            return false;
        }
        Liquidacion other = (Liquidacion) object;
        if ((this.idLiquidacion == null && other.idLiquidacion != null) || (this.idLiquidacion != null && !this.idLiquidacion.equals(other.idLiquidacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.Liquidacion[ idLiquidacion=" + idLiquidacion + " ]";
    }

}
