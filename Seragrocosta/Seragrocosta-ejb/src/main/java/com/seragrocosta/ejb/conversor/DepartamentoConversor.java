/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.DepartamentoDto;
import com.seragrocosta.ejb.entity.Departamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class DepartamentoConversor implements Serializable {

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Inject
    private MunicipioConversor municipioConversor;

    public DepartamentoDto getDepartamentoFromEntity(final Departamento entity) {
        final DepartamentoDto dto = new DepartamentoDto();
        dto.setIdDepartamento(entity.getIdDepartamento());
        dto.setNombre(entity.getNombre());
        dto.setListaMunicipios(municipioConversor
                .getListMunicipioFromEntity(entity.getMunicipioList()));
        return dto;
    }

    public List<DepartamentoDto> getListDepartamentoFromEntity(final List<Departamento> listEntity) {
        final List<DepartamentoDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getDepartamentoFromEntity(entity));
            });
        }
        return listDto;
    }

    public Departamento getDepartamentoFromBd(final Integer idDepartamento) {
        return this.em.find(Departamento.class, idDepartamento);
    }
}
