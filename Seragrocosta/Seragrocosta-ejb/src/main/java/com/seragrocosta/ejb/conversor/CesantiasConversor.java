/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.CesantiasDto;
import com.seragrocosta.ejb.entity.Cesantias;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class CesantiasConversor implements Serializable {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    public CesantiasDto getCesantiasFromEntity(final Cesantias entity) {
        final CesantiasDto dto = new CesantiasDto();
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setIdCesantias(entity.getIdCesantias());
        dto.setNit(entity.getNit());
        dto.setNombre(entity.getNombre());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }

    public Cesantias getCesantiasFromDto(final CesantiasDto dto) {
        final Cesantias entity = new Cesantias();
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setIdCesantias(dto.getIdCesantias());
        entity.setNit(dto.getNit());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    public List<CesantiasDto> getListCesantiasFtomEntity(final List<Cesantias> listEntity) {
        final List<CesantiasDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getCesantiasFromEntity(entity));
            });

        }
        return listDto;
    }

    public Cesantias getCesantiasFromBd(final Integer idCesantias) {
        return this.em.find(Cesantias.class, idCesantias);
    }
}
