/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import com.seragrocosta.ejb.enums.TipoContable;
import com.seragrocosta.ejb.enums.TipoNomina;
import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author USER
 */
@Entity
@Table(name = "concepto_nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoNomina.findAll", query = "SELECT c FROM ConceptoNomina c")
    , @NamedQuery(name = "ConceptoNomina.findByIdConceptoNomina", query = "SELECT c FROM ConceptoNomina c WHERE c.idConceptoNomina = :idConceptoNomina")
    , @NamedQuery(name = "ConceptoNomina.findByNombre", query = "SELECT c FROM ConceptoNomina c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "ConceptoNomina.findByTipoNomina", query = "SELECT c FROM ConceptoNomina c WHERE c.tipoNomina = :tipoNomina")
    , @NamedQuery(name = "ConceptoNomina.findByTipoContable", query = "SELECT c FROM ConceptoNomina c WHERE c.tipoContable = :tipoContable")
    , @NamedQuery(name = "ConceptoNomina.findByImprimirDesprendible", query = "SELECT c FROM ConceptoNomina c WHERE c.imprimirDesprendible = :imprimirDesprendible")
    , @NamedQuery(name = "ConceptoNomina.findByValor", query = "SELECT c FROM ConceptoNomina c WHERE c.valor = :valor")})
public class ConceptoNomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concepto_nomina")
    private Integer idConceptoNomina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_nomina")
    private TipoNomina tipoNomina;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contable")
    private TipoContable tipoContable;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private String cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "formula")
    private String formula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "imprimir_desprendible")
    private boolean imprimirDesprendible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConceptoNomina", fetch = FetchType.LAZY)
    private List<NovedadFija> novedadEmpleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConceptoNomina", fetch = FetchType.LAZY)
    private List<NominaConceptoSalarial> nominaConceptoSalarialList;

    public ConceptoNomina() {
    }

    public ConceptoNomina(Integer idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }

    public ConceptoNomina(Integer idConceptoNomina, String nombre, TipoNomina tipoNomina, TipoContable tipoContable, boolean imprimirDesprendible, BigDecimal valor) {
        this.idConceptoNomina = idConceptoNomina;
        this.nombre = nombre;
        this.tipoNomina = tipoNomina;
        this.tipoContable = tipoContable;
        this.imprimirDesprendible = imprimirDesprendible;
        this.valor = valor;
    }

    public Integer getIdConceptoNomina() {
        return idConceptoNomina;
    }

    public void setIdConceptoNomina(Integer idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public TipoContable getTipoContable() {
        return tipoContable;
    }

    public void setTipoContable(TipoContable tipoContable) {
        this.tipoContable = tipoContable;
    }

    public boolean getImprimirDesprendible() {
        return imprimirDesprendible;
    }

    public void setImprimirDesprendible(boolean imprimirDesprendible) {
        this.imprimirDesprendible = imprimirDesprendible;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the cantidad
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the formula
     */
    public String getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }

    @XmlTransient
    public List<NominaConceptoSalarial> getNominaConceptoSalarialList() {
        return nominaConceptoSalarialList;
    }

    public void setNominaConceptoSalarialList(List<NominaConceptoSalarial> nominaConceptoSalarialList) {
        this.nominaConceptoSalarialList = nominaConceptoSalarialList;
    }

    @XmlTransient
    public List<NovedadFija> getNovedadEmpleadoList() {
        return novedadEmpleadoList;
    }

    public void setNovedadEmpleadoList(List<NovedadFija> novedadEmpleadoList) {
        this.novedadEmpleadoList = novedadEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConceptoNomina != null ? idConceptoNomina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptoNomina)) {
            return false;
        }
        ConceptoNomina other = (ConceptoNomina) object;
        if ((this.idConceptoNomina == null && other.idConceptoNomina != null) || (this.idConceptoNomina != null && !this.idConceptoNomina.equals(other.idConceptoNomina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.ConceptoNomina[ idConceptoNomina=" + idConceptoNomina + " ]";
    }

}
