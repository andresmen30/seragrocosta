/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.ejb.entity.Periodo;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author USER
 */
@Stateless
public class PeriodoConversor implements Serializable {
    
    @Inject
    private PeriodoConceptoSaConversor conceptoSaConversor;
    
    public PeriodosDto getPeriodoFromEntity(final Periodo periodo) {
        final PeriodosDto periodosDto = new PeriodosDto();
        periodosDto.setIdPeriodo(periodo.getIdPeriodo());
        periodosDto.setNombre(periodo.getNombre());
        periodosDto.setFechaInicial(periodo.getFechaInicial());
        periodosDto.setFechaFinal(periodo.getFechaFinal());
        periodosDto.setEstadoPeriodo(periodo.getEstadoPeriodo());
        periodosDto.setPeriodoConceptoSalarialList(conceptoSaConversor
                .getPeriodoConceptSalListFromEntity(periodo.getPeriodoConceptoSalarialList()));
        periodosDto.setNombreFechas(nombreFechas(periodo));
        return periodosDto;
    }
    
    public List<PeriodosDto> getPeriodoListFromEntity(final List<Periodo> listPeriodoEntity) {
        List<PeriodosDto> listaPeriodoDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listPeriodoEntity)) {
            listPeriodoEntity.stream().forEach(periodoEntity -> {
                listaPeriodoDto.add(getPeriodoFromEntity(periodoEntity));
            });
        }
        return listaPeriodoDto;
    }
    
    public Periodo getPeriodoFromDto(final PeriodosDto periodoDto) {
        final Periodo periodosEntity = new Periodo();
        periodosEntity.setIdPeriodo(periodoDto.getIdPeriodo());
        periodosEntity.setNombre(periodoDto.getNombre());
        periodosEntity.setFechaInicial(periodoDto.getFechaInicial());
        periodosEntity.setFechaFinal(periodoDto.getFechaFinal());
        periodosEntity.setEstadoPeriodo(periodoDto.getEstadoPeriodo());
        return periodosEntity;
    }
    
    public List<Periodo> getPeriodoListFromDto(final List<PeriodosDto> listPeriodoDto) {
        List<Periodo> listaPeriodoEntity = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listPeriodoDto)) {
            listPeriodoDto.stream().forEach(periodoDto -> {
                listaPeriodoEntity.add(getPeriodoFromDto(periodoDto));
            });
        }
        return listaPeriodoEntity;
    }
    
    private String nombreFechas(final Periodo periodo) {
        final StringBuilder builder = new StringBuilder();
        builder.append(periodo.getNombre()).append(StringUtils.SPACE);
        builder.append("(")
                .append(parseLocalDateToString(periodo.getFechaInicial(), "dd/MM/yyyy"));
        builder.append(StringUtils.SPACE).append("-").append(StringUtils.SPACE);
        builder.append(parseLocalDateToString(periodo.getFechaFinal(), "dd/MM/yyyy")).append(")");
        return builder.toString();
    }
    
    public String parseLocalDateToString(final LocalDate localDate, final String pattern) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }
    
}
