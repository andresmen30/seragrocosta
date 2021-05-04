/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.ConceptoSalarialConversor;
import com.seragrocosta.ejb.dto.ConceptoSalarialDto;
import com.seragrocosta.ejb.entity.ConceptoSalarial;
import com.seragrocosta.ejb.facade.ConceptoSalarialFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@Stateless
public class ConceptoSalarialDao implements Serializable {

    @EJB
    private ConceptoSalarialConversor parametrizationConversor;

    @EJB
    private ConceptoSalarialFacade conceptoSalarialFacade;

    public List<ConceptoSalarialDto> getConceptoSalarialAll() {
        final List<ConceptoSalarial> listConceptoSalarial = conceptoSalarialFacade
                .getConceptoSalarialAll();
        return parametrizationConversor.getConceptoSalarialListFromEntity(listConceptoSalarial);
    }

    public boolean existParamValue(final String value) {
        return conceptoSalarialFacade.existParamValue(value);
    }

}
