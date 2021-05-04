/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.PeriodoConceptoSalarialDto;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.web.dao.PeriodoDao;
import com.seragrocosta.web.util.JsfUtil;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author USER
 */
@Named
@ViewScoped
public class PeriodosMb implements Serializable {

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private JsfUtil jsfUtil;

    @Getter
    @Setter
    private ScheduleModel lazyEventModel;

    @Getter
    @Setter
    private PeriodosDto periodosDto;

    @Getter
    @Setter
    private String[] conceptosSalariales;

    @Getter
    @Setter
    private List<PeriodosDto> listPeriodos;

    @Inject
    private PeriodoDao periodoDao;

    @PostConstruct
    public void init() {
        inicializate();
        lazyEventModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(final LocalDateTime start, final LocalDateTime end) {
                listPeriodos = periodoDao.getListPeriodsByStartAndEnd(start.toLocalDate(), end.toLocalDate());
                listPeriodos.stream().forEach(periodo -> {
                    final String title = periodo.getNombre();
                    addEvent(DefaultScheduleEvent.builder()
                            .title(title).description(builderConceptosSalariales(periodo))
                            .startDate(periodo.getFechaInicial().atStartOfDay())
                            .endDate(periodo.getFechaFinal().atTime(LocalTime.MAX))
                            .data(periodo)
                            .build());
                });
            }
        };

    }

    public void inicializate() {
        periodosDto = new PeriodosDto();
        conceptosSalariales = null;
    }

    private String builderConceptosSalariales(final PeriodosDto periodosDto) {
        final StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(periodosDto.getPeriodoConceptoSalarialList())) {
            periodosDto.getPeriodoConceptoSalarialList().stream().forEach(concepto -> {
                builder.append(concepto.getIdConceptoSalarial().getNombre())
                        .append(",");
            });
            builder.deleteCharAt(builder.length() - NumberUtils.INTEGER_ONE);
        } else {
            builder.append("No tiene conceptos salariales");
        }
        return builder.toString();
    }

    public void onEventSelect(SelectEvent<ScheduleEvent> selectEvent) {
        this.periodosDto = (PeriodosDto) selectEvent.getObject().getData();
        preEdit(this.periodosDto.getPeriodoConceptoSalarialList());

    }

    public void guardar() {
        if (this.periodosDto.getFechaInicial()
                .isAfter(this.periodosDto.getFechaFinal())) {
            messageUtil.addMessageError("La fecha inicial no puede ser mayor a la fecha final");
        } else if (this.periodosDto.getFechaFinal()
                .isBefore(this.periodosDto.getFechaInicial())) {
            messageUtil.addMessageError("La fecha final no puede ser menor a la fecha inicial");
        } else if (ArrayUtils.isEmpty(conceptosSalariales)) {
            messageUtil.addMessageError("Debe seleccionar al menos 1 concepto salarial");
        } else if (periodoDao.isValidateRangeDates(this.periodosDto.getIdPeriodo(), this.periodosDto.getFechaInicial(),
                this.periodosDto.getFechaFinal())) {
            messageUtil.addMessageError("El rango de fechas se cruza con otro periodo");
        }  else {
            periodoDao.save(this.periodosDto, conceptosSalariales);
            jsfUtil.hideDialog("periodoDialog");
            jsfUtil.updateComponenScheduleEvent("periodCalendar");
            inicializate();
        }
    }

    private void preEdit(List<PeriodoConceptoSalarialDto> conceptList) {
        if (CollectionUtils.isNotEmpty(conceptList)) {
            final int index = conceptList.size();
            conceptosSalariales = new String[index];
            for (int i = NumberUtils.INTEGER_ZERO; i < index; i++) {
                conceptosSalariales[i] = conceptList.get(i).getIdConceptoSalarial().getNombre();
            }
        }
    }

    public void delete(final PeriodosDto periodosDto) {
        periodoDao.delete(periodosDto);
        messageUtil.addMessageInfo("Se ha eliminado correctamente");
    }

}
