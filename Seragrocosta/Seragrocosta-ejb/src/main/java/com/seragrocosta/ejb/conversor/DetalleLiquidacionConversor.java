/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.DetalleLiquidacionDto;
import com.seragrocosta.ejb.entity.DetalleLiquidacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class DetalleLiquidacionConversor implements Serializable {
    
    @Inject
    private EmpleadoConversor empleadoConversor;
    @Inject
    private LiquidacionConversor liquidacionConversor;
    
    public DetalleLiquidacionDto getDetalleLiquidacionFromEntity(final DetalleLiquidacion entity) {
        final DetalleLiquidacionDto dto = new DetalleLiquidacionDto();
        dto.setAuxilioTransporte(entity.getAuxilioTransporte());
        dto.setDiasTrabajados(entity.getDiasTrabajados());
        dto.setIdDetalleLiquidacion(entity.getIdDetalleLiquidacion());
        dto.setIdEmpleado(empleadoConversor.getEmpleadoFromEntity(entity.getIdEmpleado()));
        dto.setIdLiquidacion(liquidacionConversor.getLiquidacionFromEntity(entity.getIdLiquidacion()));
        dto.setPension(entity.getPension());
        dto.setSalud(entity.getSalud());
        dto.setTotalDeducido(entity.getTotalDeducido());
        dto.setTotalDevengado(entity.getTotalDevengado());
        dto.setTotalNeto(entity.getTotalNeto());
        dto.setSalarioDevengado(entity.getSalarioDevengado());
        return dto;
    }
    
    public DetalleLiquidacion getDetalleLiquidacionFromDto(final DetalleLiquidacionDto dto) {
        final DetalleLiquidacion entity = new DetalleLiquidacion();
        entity.setAuxilioTransporte(dto.getAuxilioTransporte());
        entity.setDiasTrabajados(dto.getDiasTrabajados());
        entity.setIdDetalleLiquidacion(dto.getIdDetalleLiquidacion());
        entity.setIdEmpleado(empleadoConversor.getEmpleadoFromDto(dto.getIdEmpleado()));
        entity.setIdLiquidacion(liquidacionConversor.getLiquidacionFromDto(dto.getIdLiquidacion()));
        entity.setPension(dto.getPension());
        entity.setSalud(dto.getSalud());
        entity.setTotalDeducido(dto.getTotalDeducido());
        entity.setTotalDevengado(dto.getTotalDevengado());
        entity.setTotalNeto(dto.getTotalNeto());
        entity.setSalarioDevengado(dto.getSalarioDevengado());
        return entity;
    }
    
    public List<DetalleLiquidacionDto> getListDetalleLiquidacionFromEntity(final List<DetalleLiquidacion> listEntity) {
        final List<DetalleLiquidacionDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getDetalleLiquidacionFromEntity(entity));
            });
        }
        return listDto;
    }
    
}
