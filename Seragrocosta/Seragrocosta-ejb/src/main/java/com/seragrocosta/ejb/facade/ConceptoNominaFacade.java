/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.ConceptoNominaConversor;
import com.seragrocosta.ejb.dto.ConceptoNominaDto;
import com.seragrocosta.ejb.entity.ConceptoNomina;
import com.seragrocosta.ejb.entity.NominaConceptoSalarial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author USER
 */
@Stateless
public class ConceptoNominaFacade extends AbstractFacade<ConceptoNomina> {

    @Inject
    private ConceptoSalarialFacade conceptoSalarialFacade;

    @Inject
    private ConceptoNominaConversor conceptoNominaConversor;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptoNominaFacade() {
        super(ConceptoNomina.class);
    }

    public List<ConceptoNomina> completeConceptoNomina() {
        final TypedQuery<ConceptoNomina> query
                = this.em.createQuery("SELECT c FROM ConceptoNomina c ORDER BY c.nombre ASC", ConceptoNomina.class);
        return query.getResultList();
    }

    public void save(final ConceptoNominaDto dto, final String... conceptosCheck) {
        final ConceptoNomina entity = conceptoNominaConversor.getConceptoNominaFromDto(dto);
        entity.setNominaConceptoSalarialList(new ArrayList<>());
        if (entity.getIdConceptoNomina() == null) {
            this.em.persist(entity);
        } else {
            this.em.merge(entity);
        }
        deleteConceptSala(entity);
        if (ArrayUtils.isNotEmpty(conceptosCheck)) {
            final Stream<String> conceptos = Arrays.stream(conceptosCheck);
            conceptos.forEach(concept -> {
                entity.getNominaConceptoSalarialList().add(getConceptoSalarial(entity, concept));
            });
            this.em.merge(entity);
        }
    }

    private void deleteConceptSala(final ConceptoNomina conceptoNomina) {
        this.em.createQuery("DELETE FROM NominaConceptoSalarial n "
                + "WHERE n.idConceptoSalarial.idConceptoSalarial=:idConceptoSalarial")
                .setParameter("idConceptoSalarial", conceptoNomina.getIdConceptoNomina()).executeUpdate();
    }

    private NominaConceptoSalarial getConceptoSalarial(final ConceptoNomina conceptoNomina, final String value) {
        final NominaConceptoSalarial conceptoSalarial = new NominaConceptoSalarial();
        conceptoSalarial.setIdConceptoNomina(conceptoNomina);
        conceptoSalarial.setIdConceptoSalarial(conceptoSalarialFacade
                .getParametrizationByNombre(value));
        return conceptoSalarial;
    }

}
