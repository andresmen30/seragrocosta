/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.ArlDto;
import com.seragrocosta.ejb.entity.Arl;
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
public class ArlConversor implements Serializable {
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    public ArlDto getArlFromEntity(@NonNull final Arl entity) {
        final ArlDto dto = new ArlDto();
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setIdArl(entity.getIdArl());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setNit(entity.getNit());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }
    
    public Arl getArlFromDto(@NonNull final ArlDto dto) {
        final Arl entity = new Arl();
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setIdArl(dto.getIdArl());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setNit(dto.getNit());
        return entity;
    }
    
    public List<ArlDto> getListArlFromEntity(final List<Arl> listEntity) {
        final List<ArlDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getArlFromEntity(entity));
            });
        }
        return listDto;
    }
    
    public Arl getArlFromBd(final Integer idArl) {
        return this.em.find(Arl.class, idArl);
    }
    
}
