/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.EpsDto;
import com.seragrocosta.ejb.entity.Eps;
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
public class EpsConversor implements Serializable {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    public EpsDto getEpsFromEntity(@NonNull final Eps entity) {
        final EpsDto dto = new EpsDto();
        dto.setIdEps(entity.getIdEps());
        dto.setNombre(entity.getNombre());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setDescripcion(entity.getDescripcion());
        dto.setNit(entity.getNit());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }

    public Eps getEpsFromDto(@NonNull final EpsDto dto) {
        final Eps entity = new Eps();
        entity.setIdEps(dto.getIdEps());
        entity.setNombre(dto.getNombre());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setNit(dto.getNit());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }

    public List<Eps> getListEpsFromDto(final List<EpsDto> listDto) {
        final List<Eps> listEntity = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listDto)) {
            listDto.stream().forEach(entity -> {
                listEntity.add(getEpsFromDto(entity));
            });
        }
        return listEntity;
    }

    public List<EpsDto> getListEpsFromEntity(final List<Eps> listEntity) {
        final List<EpsDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(dto -> {
                listDto.add(getEpsFromEntity(dto));
            });
        }
        return listDto;
    }

    public Eps getEpsFromBd(final Integer idEps) {
        return this.em.find(Eps.class, idEps);
    }

}
