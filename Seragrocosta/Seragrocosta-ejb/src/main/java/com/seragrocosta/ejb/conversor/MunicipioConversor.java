/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.MunicipioDto;
import com.seragrocosta.ejb.entity.Municipio;
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
public class MunicipioConversor implements Serializable {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    public MunicipioDto getMunicipioFromEntity(@NonNull final Municipio entity) {
        final MunicipioDto dto = new MunicipioDto();
        dto.setIdMunicipio(entity.getIdMunicipio());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public List<MunicipioDto> getListMunicipioFromEntity(final List<Municipio> listEntity) {
        final List<MunicipioDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getMunicipioFromEntity(entity));
            });
        }
        return listDto;
    }

    public Municipio getMunicipioFromBd(final Integer idMunicipio) {
        return this.em.find(Municipio.class, idMunicipio);
    }
}
