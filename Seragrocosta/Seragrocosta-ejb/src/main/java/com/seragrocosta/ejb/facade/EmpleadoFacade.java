/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import com.seragrocosta.ejb.conversor.EmpleadoConversor;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.entity.Empleado;
import com.seragrocosta.ejb.enums.StatusEmpleado;
import com.seragrocosta.ejb.validator.ValidatorEjb;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author USER
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @Inject
    private EmpleadoConversor empleadoConversor;

    @Inject
    private NominaFacade nominaFacade;

    @Inject
    private ValidatorEjb validatorEjb;

    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public void save(final EmpleadoDto dto) {
        final Empleado empleadoEntity = this.empleadoConversor.getEmpleadoFromDto(dto);
        empleadoEntity.setEstado(StatusEmpleado.ACTIVE);
        if (empleadoEntity.getIdNomina().getIdNomina() == null) {
            this.nominaFacade.create(empleadoEntity.getIdNomina());
        } else {
            this.nominaFacade.edit(empleadoEntity.getIdNomina());
        }
        if (empleadoEntity.getIdEmpleado() == null) {
            create(empleadoEntity);
        } else {
            edit(empleadoEntity);
        }

    }

    public void delete(final EmpleadoDto empleadoDto) {
        this.em.createQuery("DELETE FROM Empleado e WHERE e.idEmpleado =:idEmpleado")
                .setParameter("idEmpleado", empleadoDto.getIdEmpleado()).executeUpdate();
    }

    public boolean isExistEmpleadoByIdent(final EmpleadoDto dto) {
        final TypedQuery<Empleado> query
                = this.em.createQuery("SELECT e FROM Empleado e WHERE e.identificacion=:identificacion AND e.idEmpleado <>:idEmpleado", Empleado.class);
        query.setParameter("identificacion", dto.getIdentificacion());
        query.setParameter("idEmpleado", dto.getIdEmpleado() == null ? NumberUtils.INTEGER_ZERO : dto.getIdEmpleado());
        return validatorEjb.getSingleResult(query) != null;
    }

    public List<String> empleadosByNameOrNit(final String query) {
        final TypedQuery<String> queryJpa
                = this.em.createQuery("SELECT CONCAT(e.identificacion, ' - ',e.nombreCompleto) "
                        + "FROM Empleado e "
                        + "WHERE UPPER(e.identificacion) like CONCAT('%', :query, '%') "
                        + "OR UPPER(e.primerNombre) like CONCAT('%', :query, '%') "
                        + "OR UPPER(e.segundoNombre) like CONCAT('%', :query, '%') "
                        + "OR UPPER(e.primerApellido) like CONCAT('%', :query, '%') "
                        + "OR UPPER(e.segundoApellido) like CONCAT('%', :query, '%')", String.class);
        queryJpa.setParameter("query", query.toUpperCase());
        return queryJpa.getResultList();
    }

    public Empleado searchIdByIdentificacion(final String identificacion) {
        final TypedQuery<Empleado> queryJpa
                = this.em.createQuery("SELECT e FROM Empleado e WHERE e.identificacion=:identificacion", Empleado.class);
        queryJpa.setParameter("identificacion", StringUtils.trim(identificacion));
        return validatorEjb.getSingleResult(queryJpa);
    }
    
     public List<Empleado> searchEmpleadosByStatus() {
        final TypedQuery<Empleado> queryJpa
                = this.em.createQuery("SELECT e FROM Empleado e WHERE e.estado=:estado", Empleado.class);
        queryJpa.setParameter("estado", StatusEmpleado.ACTIVE);
        return queryJpa.getResultList();
    }

}
