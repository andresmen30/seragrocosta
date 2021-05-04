/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.BancoDto;
import com.seragrocosta.ejb.entity.Banco;
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
public class BancoConversor implements Serializable {
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    public BancoDto getBancoFromEntity(final Banco entity) {
        final BancoDto dto = new BancoDto();
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setIdBanco(entity.getIdBanco());
        dto.setNombre(entity.getNombre());
        dto.setCodigo(entity.getCodigo());
        dto.setNomina(entity.getNominaList().size());
        return dto;
    }
    
    public Banco getBancoFromDto(final BancoDto dto) {
        final Banco entity = new Banco();
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setIdBanco(dto.getIdBanco());
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        return entity;
    }
    
    public List<BancoDto> getListBancoFromEntity(final List<Banco> listEntity) {
        final List<BancoDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getBancoFromEntity(entity));
            });
        }
        return listDto;
    }
    
    public Banco getBancoFromBd(final Integer idBanco) {
        return this.em.find(Banco.class, idBanco);
    }
    
}
