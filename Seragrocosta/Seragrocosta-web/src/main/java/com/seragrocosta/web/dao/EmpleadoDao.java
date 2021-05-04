/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.EmpleadoConversor;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.entity.Empleado;
import com.seragrocosta.ejb.facade.EmpleadoFacade;
import com.seragrocosta.ejb.facade.NominaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author USER
 */
@Stateless
public class EmpleadoDao implements Serializable {

    @EJB
    private EmpleadoFacade empleadoFacade;
 
    @EJB
    private EmpleadoConversor empleadoConversor;

    @EJB
    private NominaFacade nominaFacade;

    public List<EmpleadoDto> getEmpleadoList(final int first, final int pageSize, final String sortField,
            final SortOrder sortOrder, final Map<String, FilterMeta> filters) {
        final List<Empleado> entity = empleadoFacade.searchFilters(first,
                pageSize, sortField, sortOrder, filters);
        return empleadoConversor.getListEmpleadoFromEntity(entity);
    }

    public Long numeroRegistros(final Map<String, FilterMeta> filters) {
        return empleadoFacade.registersNumber(filters);
    }

    public void save(final EmpleadoDto empleadoDto) {
        this.empleadoFacade.save(empleadoDto);
    }

    public void delete(final EmpleadoDto empleadoDto) {
        this.empleadoFacade.delete(empleadoDto);
        this.nominaFacade.delete(empleadoDto);
    }

    public boolean isExistEmpleadoByIdent(final EmpleadoDto dto) {
        return empleadoFacade.isExistEmpleadoByIdent(dto);
    }

    public List<String> empleadosByNameOrNit(final String query) {
        return empleadoFacade.empleadosByNameOrNit(query);
    }
    
    public EmpleadoDto searchIdByIdentificacion(final String identificacion){
      return empleadoConversor.getEmpleadoFromEntity(empleadoFacade.searchIdByIdentificacion(identificacion));
    }
    
    public List<EmpleadoDto> searchEmpleadosByStatus(){
      return empleadoConversor.getListEmpleadoFromEntity(empleadoFacade.searchEmpleadosByStatus());
    }
}
