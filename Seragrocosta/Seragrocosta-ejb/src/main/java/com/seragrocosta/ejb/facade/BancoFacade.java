/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.BancoConversor;
import com.seragrocosta.ejb.dto.BancoDto;
import com.seragrocosta.ejb.entity.Banco;
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
public class BancoFacade extends AbstractFacade<Banco> {

    @Inject
    private BancoConversor bancoConversor;

    @Inject
    private ValidatorEjb validatorEjb;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BancoFacade() {
        super(Banco.class);
    }

    public boolean isExistCode(final BancoDto dto) {
        final TypedQuery<Banco> query
                = this.em.createQuery("SELECT c FROM Banco c WHERE UPPER(c.codigo)=:codigo AND c.idBanco <>:idBanco", Banco.class);
        query.setParameter("codigo", dto.getCodigo().toUpperCase());
        query.setParameter("idBanco", dto.getIdBanco() == null ? NumberUtils.INTEGER_ZERO : dto.getIdBanco());
        return validatorEjb.getSingleResult(query) != null;
    }

    public void save(final BancoDto dto) {
        final Banco entity = bancoConversor.getBancoFromDto(dto);
        if (entity.getIdBanco() == null) {
            entity.setFechaCreacion(LocalDate.now());
            this.create(entity);
        } else {
            this.edit(entity);
        }
    }

    public void delete(final BancoDto dto) {
        final Banco entity = this.find(dto.getIdBanco());
        this.remove(entity);
    }

}
