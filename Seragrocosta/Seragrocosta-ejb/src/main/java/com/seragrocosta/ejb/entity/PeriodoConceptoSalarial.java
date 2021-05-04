/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import java.io.Serializable;
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
@Table(name = "periodo_concepto_salarial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoConceptoSalarial.findAll", query = "SELECT p FROM PeriodoConceptoSalarial p")
    , @NamedQuery(name = "PeriodoConceptoSalarial.findByIdPeriodoConceptoSalarial", query = "SELECT p FROM PeriodoConceptoSalarial p WHERE p.idPeriodoConceptoSalarial = :idPeriodoConceptoSalarial")})
public class PeriodoConceptoSalarial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo_concepto_salarial")
    private Integer idPeriodoConceptoSalarial;
    @JoinColumn(name = "id_concepto_salarial", referencedColumnName = "id_concepto_salarial")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConceptoSalarial idConceptoSalarial;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Periodo idPeriodo;

    public PeriodoConceptoSalarial() {
    }

    public PeriodoConceptoSalarial(Integer idPeriodoConceptoSalarial) {
        this.idPeriodoConceptoSalarial = idPeriodoConceptoSalarial;
    }

    public Integer getIdPeriodoConceptoSalarial() {
        return idPeriodoConceptoSalarial;
    }

    public void setIdPeriodoConceptoSalarial(Integer idPeriodoConceptoSalarial) {
        this.idPeriodoConceptoSalarial = idPeriodoConceptoSalarial;
    }

    public ConceptoSalarial getIdConceptoSalarial() {
        return idConceptoSalarial;
    }

    public void setIdConceptoSalarial(ConceptoSalarial idConceptoSalarial) {
        this.idConceptoSalarial = idConceptoSalarial;
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
        hash += (idPeriodoConceptoSalarial != null ? idPeriodoConceptoSalarial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoConceptoSalarial)) {
            return false;
        }
        PeriodoConceptoSalarial other = (PeriodoConceptoSalarial) object;
        if ((this.idPeriodoConceptoSalarial == null && other.idPeriodoConceptoSalarial != null) || (this.idPeriodoConceptoSalarial != null && !this.idPeriodoConceptoSalarial.equals(other.idPeriodoConceptoSalarial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.PeriodoConceptoSalarial[ idPeriodoConceptoSalarial=" + idPeriodoConceptoSalarial + " ]";
    }

}
