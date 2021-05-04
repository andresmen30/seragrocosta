/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.LiquidacionDto;
import com.seragrocosta.ejb.entity.Liquidacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class LiquidacionConversor implements Serializable {

    @Inject
    private PeriodoConversor periodoConversor;

    public Liquidacion getLiquidacionFromDto(@NonNull final LiquidacionDto dto) {
        final Liquidacion entity = new Liquidacion();
        entity.setIdLiquidacion(dto.getIdLiquidacion());
        entity.setTipoLiquidacion(dto.getTipoLiquidacion());
        entity.setFechaLiquidacion(dto.getFechaLiquidacion());
        entity.setIdPeriodo(periodoConversor.getPeriodoFromDto(dto.getIdPeriodo()));
        return entity;
    }

    public LiquidacionDto getLiquidacionFromEntity(@NonNull final Liquidacion entity) {
        final LiquidacionDto dto = new LiquidacionDto();
        dto.setIdLiquidacion(entity.getIdLiquidacion());
        dto.setFechaLiquidacion(entity.getFechaLiquidacion());
        dto.setTipoLiquidacion(entity.getTipoLiquidacion());
        dto.setIdPeriodo(periodoConversor.getPeriodoFromEntity(entity.getIdPeriodo()));
        return dto;
    }

    public List<LiquidacionDto> getListLiquidacionFromEntity(final List<Liquidacion> listEntity) {
        final List<LiquidacionDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getLiquidacionFromEntity(entity));
            });
        }
        return listDto;
    }

}
