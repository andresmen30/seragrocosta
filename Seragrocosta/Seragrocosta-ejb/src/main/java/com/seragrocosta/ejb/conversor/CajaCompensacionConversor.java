/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.CajaCompensacionDto;
import com.seragrocosta.ejb.entity.CajaCompensacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class CajaCompensacionConversor {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    public CajaCompensacionDto getCajaCompensacionFromEntity(@NonNull final CajaCompensacion entity) {
        final CajaCompensacionDto dto = new CajaCompensacionDto();
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setIdCajaCompensacion(entity.getIdCajaCompensacion());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setNit(entity.getNit());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }

    public CajaCompensacion getCajaCompensacionFromDto(@NonNull final CajaCompensacionDto dto) {
        final CajaCompensacion entity = new CajaCompensacion();
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setIdCajaCompensacion(dto.getIdCajaCompensacion());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setNit(dto.getNit());
        return entity;
    }

    public List<CajaCompensacionDto> getListCajaCompensacionFromEntity(final List<CajaCompensacion> listEntity) {
        final List<CajaCompensacionDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getCajaCompensacionFromEntity(entity));
            });

        }
        return listDto;
    }

    public CajaCompensacion getCajaCompensacionFromBd(final Integer idCajaCompensacion) {
        return this.em.find(CajaCompensacion.class, idCajaCompensacion);
    }
}
