/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class NominaConceptoSalarialDto implements Serializable {

    private Integer idNominaConceptoSalarial;
    private ConceptoSalarialDto idConceptoSalarial;

}
