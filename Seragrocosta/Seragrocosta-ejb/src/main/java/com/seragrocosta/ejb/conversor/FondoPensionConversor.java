/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.FondoPensionDto;
import com.seragrocosta.ejb.entity.FondoPension;
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
public class FondoPensionConversor implements Serializable {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    public FondoPension getFondoPensionFromEntity(@NonNull final FondoPensionDto dto) {
        final FondoPension entity = new FondoPension();
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setIdFondoPension(dto.getIdFondoPension());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setNit(dto.getNit());
        return entity;
    }

    public FondoPensionDto getFondoPensionFromDto(@NonNull final FondoPension entity) {
        final FondoPensionDto dto = new FondoPensionDto();
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setIdFondoPension(entity.getIdFondoPension());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setNit(entity.getNit());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }

    public List<FondoPensionDto> getListFondoPensionFromEntity(final List<FondoPension> listEntity) {
        final List<FondoPensionDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getFondoPensionFromDto(entity));
            });
        }
        return listDto;
    }

    public FondoPension getFondoPensionFromBd(final Integer idFondoPension) {
        return this.em.find(FondoPension.class, idFondoPension);
    }

}
