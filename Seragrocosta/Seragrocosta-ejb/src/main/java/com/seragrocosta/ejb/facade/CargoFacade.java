/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.CargoConversor;
import com.seragrocosta.ejb.dto.CargoDto;
import com.seragrocosta.ejb.entity.Cargo;
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
public class CargoFacade extends AbstractFacade<Cargo> {

    @Inject
    private CargoConversor cargoConversor;

    @Inject
    private ValidatorEjb validatorEjb;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoFacade() {
        super(Cargo.class);
    }

    public boolean isExistNombre(final CargoDto dto) {
        final TypedQuery<Cargo> query
                = this.em.createQuery("SELECT c FROM Cargo c WHERE UPPER(c.nombre)=:nombre AND c.idCargo <>:idCargo", Cargo.class);
        query.setParameter("nombre", dto.getNombre().toUpperCase());
        query.setParameter("idCargo", dto.getIdCargo() == null ? NumberUtils.INTEGER_ZERO : dto.getIdCargo());
        return validatorEjb.getSingleResult(query) != null;
    }

    public void save(final CargoDto dto) {
        final Cargo entity = cargoConversor.getCargoFromDto(dto);
        if (entity.getIdCargo() == null) {
            entity.setFechaCreacion(LocalDate.now());
            this.create(entity);
        } else {
            this.edit(entity);
        }
    }

    public void delete(final CargoDto dto) {
        final Cargo entity = this.find(dto.getIdCargo());
        this.remove(entity);
    }

}
