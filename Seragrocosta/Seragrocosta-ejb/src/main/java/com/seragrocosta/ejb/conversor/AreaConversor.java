/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.AreaDto;
import com.seragrocosta.ejb.entity.Area;
import java.io.Serializable;
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
public class AreaConversor implements Serializable {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    public Area getAreaFromDto(@NonNull final AreaDto dto) {
        final Area entity = new Area();
        entity.setIdArea(dto.getIdArea());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    public AreaDto getAreaFromEntity(@NonNull final Area entity) {
        final AreaDto dto = new AreaDto();
        dto.setIdArea(entity.getIdArea());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setNombre(entity.getNombre());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }

    public List<AreaDto> getListAreaFromEntity(final List<Area> listEntity) {
        final List<AreaDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getAreaFromEntity(entity));
            });
        }
        return listDto;
    }

    public Area getAreaFromBd(final Integer idArea) {
        return this.em.find(Area.class, idArea);
    }
}
