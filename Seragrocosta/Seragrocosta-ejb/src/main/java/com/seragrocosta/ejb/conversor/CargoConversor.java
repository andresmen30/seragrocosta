/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.CargoDto;
import com.seragrocosta.ejb.entity.Cargo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.NonNull;

/**
 *
 * @author USER
 */
@Stateless
public class CargoConversor implements Serializable {
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    public Cargo getCargoFromDto(@NonNull final CargoDto dto) {
        final Cargo entity = new Cargo();
        entity.setIdCargo(dto.getIdCargo());
        entity.setNombre(dto.getNombre());
        entity.setFechaCreacion(dto.getFechaCreacion());
        return entity;
    }
    
    public CargoDto getCargoFromEntity(@NonNull final Cargo entity) {
        final CargoDto dto = new CargoDto();
        dto.setIdCargo(entity.getIdCargo());
        dto.setNombre(entity.getNombre());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }
    
    public List<CargoDto> getListCargoDtoFromEntity(final List<Cargo> listEntity) {
        final List<CargoDto> listDto = new ArrayList<>();
        listEntity.stream().forEach(entity -> {
            listDto.add(getCargoFromEntity(entity));
        });
        return listDto;
    }
    
    public Cargo getCargoFromBd(final Integer idCargo) {
        return this.em.find(Cargo.class, idCargo);
    }
    
}
