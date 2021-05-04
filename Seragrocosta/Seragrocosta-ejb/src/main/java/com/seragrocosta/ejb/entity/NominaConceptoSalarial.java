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
@Table(name = "nomina_concepto_salarial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NominaConceptoSalarial.findAll", query = "SELECT n FROM NominaConceptoSalarial n")
    , @NamedQuery(name = "NominaConceptoSalarial.findByIdNominaConceptoSalarial", query = "SELECT n FROM NominaConceptoSalarial n WHERE n.idNominaConceptoSalarial = :idNominaConceptoSalarial")})
public class NominaConceptoSalarial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nomina_concepto_salarial")
    private Integer idNominaConceptoSalarial;
    @JoinColumn(name = "id_concepto_nomina", referencedColumnName = "id_concepto_nomina")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConceptoNomina idConceptoNomina;
    @JoinColumn(name = "id_concepto_salarial", referencedColumnName = "id_concepto_salarial")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConceptoSalarial idConceptoSalarial;

    public NominaConceptoSalarial() {
    }

    public NominaConceptoSalarial(Integer idNominaConceptoSalarial) {
        this.idNominaConceptoSalarial = idNominaConceptoSalarial;
    }

    public Integer getIdNominaConceptoSalarial() {
        return idNominaConceptoSalarial;
    }

    public void setIdNominaConceptoSalarial(Integer idNominaConceptoSalarial) {
        this.idNominaConceptoSalarial = idNominaConceptoSalarial;
    }

    public ConceptoNomina getIdConceptoNomina() {
        return idConceptoNomina;
    }

    public void setIdConceptoNomina(ConceptoNomina idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }

    public ConceptoSalarial getIdConceptoSalarial() {
        return idConceptoSalarial;
    }

    public void setIdConceptoSalarial(ConceptoSalarial idConceptoSalarial) {
        this.idConceptoSalarial = idConceptoSalarial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNominaConceptoSalarial != null ? idNominaConceptoSalarial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NominaConceptoSalarial)) {
            return false;
        }
        NominaConceptoSalarial other = (NominaConceptoSalarial) object;
        if ((this.idNominaConceptoSalarial == null && other.idNominaConceptoSalarial != null) || (this.idNominaConceptoSalarial != null && !this.idNominaConceptoSalarial.equals(other.idNominaConceptoSalarial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.NominaConceptoSalarial[ idNominaConceptoSalarial=" + idNominaConceptoSalarial + " ]";
    }
    
}
