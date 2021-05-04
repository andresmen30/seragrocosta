/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.AreaConversor;
import com.seragrocosta.ejb.dto.AreaDto;
import com.seragrocosta.ejb.entity.Area;
import com.seragrocosta.ejb.validator.ValidatorEjb;
import java.time.LocalDate;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author USER
 */
@Stateless
public class AreaFacade extends AbstractFacade<Area> {

    @Inject
    private ValidatorEjb validatorEjb;

    @Inject
    private AreaConversor areaConversor;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaFacade() {
        super(Area.class);
    }

    public void save(final AreaDto dto) {
        final Area entity = areaConversor.getAreaFromDto(dto);
        if (entity.getIdArea() == null) {
            entity.setFechaCreacion(LocalDate.now());
            this.create(entity);
        } else {
            this.edit(entity);
        }
    }

    public boolean isExistNombre(final AreaDto dto) {
        final TypedQuery<Area> query
                = this.em.createQuery("SELECT a FROM Area a WHERE UPPER(a.nombre)=:nombre AND a.idArea NOT IN (:idArea)", Area.class);
        query.setParameter("nombre", dto.getNombre().toUpperCase());
        query.setParameter("idArea", dto.getIdArea() == null ? NumberUtils.INTEGER_ZERO : dto.getIdArea());
        return validatorEjb.getSingleResult(query) != null;
    }

    public void delete(final AreaDto dto) {
        final Area entity = this.find(dto.getIdArea());
        this.remove(entity);
    }
}
