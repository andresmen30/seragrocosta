/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.PeriodoConversor;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.ejb.entity.Periodo;
import com.seragrocosta.ejb.entity.PeriodoConceptoSalarial;
import com.seragrocosta.ejb.enums.EstadoPeriodo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author USER
 */
@Stateless
public class PeriodoFacade extends AbstractFacade<Periodo> {

    @Inject
    private ConceptoSalarialFacade conceptoSalarialFacade;

    @Inject
    private PeriodoConversor periodoConversor;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodoFacade() {
        super(Periodo.class);
    }

    public void save(final PeriodosDto periodosDto, final String... conceptosCheck) {
        final Periodo periodo = periodoConversor.getPeriodoFromDto(periodosDto);
        periodo.setPeriodoConceptoSalarialList(new ArrayList<>());
        if (periodo.getIdPeriodo() == null) {
            periodo.setEstadoPeriodo(EstadoPeriodo.PENDIENTE);
            this.em.persist(periodo);
        } else {
            this.em.merge(periodo);
        }
        deleteConceptSala(periodo);
        if (ArrayUtils.isNotEmpty(conceptosCheck)) {
            final Stream<String> conceptos = Arrays.stream(conceptosCheck);
            conceptos.forEach(concept -> {
                periodo.getPeriodoConceptoSalarialList().add(getConceptoSalarial(periodo, concept));
            });
            this.em.merge(periodo);
        }

    }

    public void delete(final PeriodosDto periodosDto) {
        final Periodo periodo = this.find(periodosDto.getIdPeriodo());
        this.em.remove(periodo);
    }

    public boolean isValidateRangeDates(final Integer idPeriodo, final LocalDate startDate, final LocalDate endDate) {
        return CollectionUtils.isNotEmpty(this.em.createQuery("SELECT p FROM Periodo p "
                + "WHERE (p.idPeriodo <>:idPeriodo AND p.fechaInicial <=:startDate "
                + "AND p.fechaInicial <=:startDate "
                + "AND ((p.fechaFinal >=:endDate "
                + "AND p.fechaFinal >=:endDate) "
                + "OR (p.fechaFinal >=:endDate "
                + "AND p.fechaFinal <=:endDate))) "
                + "OR (p.idPeriodo <>:idPeriodo AND p.fechaInicial >=:startDate "
                + "AND p.fechaInicial <=:startDate "
                + "AND ((p.fechaFinal >=:endDate "
                + "AND p.fechaFinal >=:endDate) "
                + "OR (p.fechaFinal >=:endDate "
                + "AND p.fechaFinal <=:endDate))) ")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("idPeriodo", idPeriodo == null ? NumberUtils.INTEGER_ZERO : idPeriodo).getResultList());
    }

    private PeriodoConceptoSalarial getConceptoSalarial(final Periodo periodo, final String value) {
        final PeriodoConceptoSalarial conceptoSalarial = new PeriodoConceptoSalarial();
        conceptoSalarial.setIdPeriodo(periodo);
        conceptoSalarial.setIdConceptoSalarial(conceptoSalarialFacade
                .getParametrizationByNombre(value));
        return conceptoSalarial;
    }

    private void deleteConceptSala(final Periodo periodo) {
        this.em.createQuery("DELETE FROM PeriodoConceptoSalarial p "
                + "WHERE p.idPeriodo.idPeriodo=:idPeriodo")
                .setParameter("idPeriodo", periodo.getIdPeriodo()).executeUpdate();
    }

    public List<Periodo> getListPeriods() {
        return this.em.createNamedQuery("Periodo.findAll").getResultList();
    }

    public List<Periodo> getListPeriodsByStartAndEnd(final LocalDate startDate, final LocalDate endDate) {
        return this.em.createQuery("SELECT p FROM Periodo p WHERE p.fechaInicial >=:fechaInicial AND p.fechaFinal <=:fechaFinal")
                .setParameter("fechaInicial", startDate).setParameter("fechaFinal", endDate).getResultList();
    }

}
