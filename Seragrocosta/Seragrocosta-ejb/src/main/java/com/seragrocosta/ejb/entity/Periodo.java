/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import com.seragrocosta.ejb.enums.EstadoPeriodo;
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
@Table(name = "periodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p")
    , @NamedQuery(name = "Periodo.findByIdPeriodo", query = "SELECT p FROM Periodo p WHERE p.idPeriodo = :idPeriodo")
    , @NamedQuery(name = "Periodo.findByNombre", query = "SELECT p FROM Periodo p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Periodo.findByFechaInicial", query = "SELECT p FROM Periodo p WHERE p.fechaInicial = :fechaInicial")
    , @NamedQuery(name = "Periodo.findByFechaFinal", query = "SELECT p FROM Periodo p WHERE p.fechaFinal = :fechaFinal")})
@Getter
@Setter
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo")
    private Integer idPeriodo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial")
    private LocalDate fechaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_final")
    private LocalDate fechaFinal;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_periodo")
    private EstadoPeriodo estadoPeriodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodo", fetch = FetchType.LAZY)
    private List<PeriodoConceptoSalarial> periodoConceptoSalarialList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodo", fetch = FetchType.LAZY)
    private List<Liquidacion> liquidacionList;

    public Periodo() {
        super();
    }

    @XmlTransient
    public List<PeriodoConceptoSalarial> getPeriodoConceptoSalarialList() {
        return periodoConceptoSalarialList;
    }

    public void setPeriodoConceptoSalarialList(List<PeriodoConceptoSalarial> periodoConceptoSalarialList) {
        this.periodoConceptoSalarialList = periodoConceptoSalarialList;
    }
    
    @XmlTransient
    public List<Liquidacion> getLiquidacionList() {
        return liquidacionList;
    }

    public void setLiquidacionList(List<Liquidacion> liquidacionList) {
        this.liquidacionList = liquidacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.Periodo[ idPeriodo=" + idPeriodo + " ]";
    }

}
