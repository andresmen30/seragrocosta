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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "concepto_salarial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoSalarial.findAll", query = "SELECT p FROM ConceptoSalarial p")
    , @NamedQuery(name = "ConceptoSalarial.findByIdConceptoSalarial", query = "SELECT p FROM ConceptoSalarial p WHERE p.idConceptoSalarial = :idConceptoSalarial")
    , @NamedQuery(name = "ConceptoSalarial.findByNombre", query = "SELECT p FROM ConceptoSalarial p WHERE p.nombre = :nombre")})
@Getter
@Setter
public class ConceptoSalarial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concepto_salarial")
    private Integer idConceptoSalarial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;

    public ConceptoSalarial() {
    }

    public ConceptoSalarial(Integer idConceptoSalarial) {
        this.idConceptoSalarial = idConceptoSalarial;
    }

    public ConceptoSalarial(Integer idConceptoSalarial, String nombre) {
        this.idConceptoSalarial = idConceptoSalarial;
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConceptoSalarial != null ? idConceptoSalarial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptoSalarial)) {
            return false;
        }
        ConceptoSalarial other = (ConceptoSalarial) object;
        if ((this.idConceptoSalarial == null && other.idConceptoSalarial != null)
                || (this.idConceptoSalarial != null && !this.idConceptoSalarial.equals(other.idConceptoSalarial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.ConceptoSalarial[ idConceptoSalarial=" + idConceptoSalarial + " ]";
    }

}
