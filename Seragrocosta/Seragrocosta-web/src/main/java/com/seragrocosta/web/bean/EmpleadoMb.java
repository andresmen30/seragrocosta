/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.AreaDto;
import com.seragrocosta.ejb.dto.ArlDto;
import com.seragrocosta.ejb.dto.BancoDto;
import com.seragrocosta.ejb.dto.CajaCompensacionDto;
import com.seragrocosta.ejb.dto.CargoDto;
import com.seragrocosta.ejb.dto.CesantiasDto;
import com.seragrocosta.ejb.dto.ConceptoNominaDto;
import com.seragrocosta.ejb.dto.DepartamentoDto;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.dto.EpsDto;
import com.seragrocosta.ejb.dto.FondoPensionDto;
import com.seragrocosta.ejb.dto.MunicipioDto;
import com.seragrocosta.ejb.dto.NominaDto;
import com.seragrocosta.ejb.dto.NovedadFijaDto;
import com.seragrocosta.ejb.dto.NovedadVariableDto;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.web.dao.EmpleadoDao;
import com.seragrocosta.web.dao.MunicipioDao;
import com.seragrocosta.web.dao.NovedadFijaDao;
import com.seragrocosta.web.dao.NovedadVariableDao;
import com.seragrocosta.web.util.JsfUtil;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author USER
 */
@Named
@ViewScoped
public class EmpleadoMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private EmpleadoDao empleadoDao;

    @Inject
    private MunicipioDao municipioDao;

    @Inject
    private NovedadFijaDao novedadEmpleadoDao;

    @Inject
    private NovedadVariableDao novedadVariableDao;

    @Getter
    @Setter
    private EmpleadoDto empleadoDto;

    @Getter
    @Setter
    private List<MunicipioDto> listMunicipioDto;

    @Getter
    @Setter
    private LazyDataModel<EmpleadoDto> listEmpleado;

    @Getter
    @Setter
    private LazyDataModel<NovedadFijaDto> listNovedadEmpleado;

    @Getter
    @Setter
    private LazyDataModel<NovedadVariableDto> listNovedadVariable;

    @PostConstruct
    public void inicitalizate() {
        listEmpleado = new LazyDataModel<EmpleadoDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<EmpleadoDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<EmpleadoDto> data = empleadoDao.getEmpleadoList(first, pageSize, sortField, sortOrder, filters);
                listEmpleado.setRowCount(empleadoDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        listNovedadEmpleado = new LazyDataModel<NovedadFijaDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<NovedadFijaDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                if (StringUtils.isNotEmpty(empleadoDto.getIdentificacion())) {
                    final Map<String, FilterMeta> filter = new HashMap<>();
                    filter.put("idEmpleado.identificacion", new FilterMeta("idEmpleado.identificacion", empleadoDto.getIdentificacion()));
                    List<NovedadFijaDto> data = novedadEmpleadoDao.getNovedadEmpleadoList(first, pageSize, sortField, sortOrder, filter);
                    listNovedadEmpleado.setRowCount(novedadEmpleadoDao.numeroRegistros(filter).intValue());
                    return data;
                } else {
                    return new ArrayList<>();
                }

            }
        };
        listNovedadVariable = new LazyDataModel<NovedadVariableDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<NovedadVariableDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                if (StringUtils.isNotEmpty(empleadoDto.getIdentificacion())) {
                    final Map<String, FilterMeta> filter = new HashMap<>();
                    filter.put("idEmpleado.identificacion", new FilterMeta("idEmpleado.identificacion", empleadoDto.getIdentificacion()));
                    List<NovedadVariableDto> data = novedadVariableDao.getNovedadVariableList(first, pageSize, sortField, sortOrder, filter);
                    listNovedadVariable.setRowCount(novedadVariableDao.numeroRegistros(filter).intValue());
                    return data;
                } else {
                    return new ArrayList<>();
                }

            }
        };
        init();
    }

    public void init() {
        empleadoDto = new EmpleadoDto();
        empleadoDto.setDepartamentoDto(new DepartamentoDto());
        empleadoDto.setMunicipioDto(new MunicipioDto());
        empleadoDto.setNominaDto(new NominaDto());
        empleadoDto.setNovedadEmpleadoDto(new NovedadFijaDto());
        empleadoDto.getNovedadEmpleadoDto().setIdConceptoNomina(new ConceptoNominaDto());
        empleadoDto.setNovedadVariableDto(new NovedadVariableDto());
        empleadoDto.getNovedadVariableDto().setIdConceptoNomina(new ConceptoNominaDto());
        empleadoDto.getNovedadVariableDto().setIdPeriodo(new PeriodosDto());
        empleadoDto.getNominaDto().setIdArea(new AreaDto());
        empleadoDto.getNominaDto().setIdArl(new ArlDto());
        empleadoDto.getNominaDto().setIdCajaCompensacion(new CajaCompensacionDto());
        empleadoDto.getNominaDto().setFondoPensionDto(new FondoPensionDto());
        empleadoDto.getNominaDto().setIdCargo(new CargoDto());
        empleadoDto.getNominaDto().setIdEps(new EpsDto());
        empleadoDto.getNominaDto().setIdBanco(new BancoDto());
        empleadoDto.getNominaDto().setCesantiasDto(new CesantiasDto());
        listMunicipioDto = new ArrayList<>();
    }

    public void onRowEdit(final RowEditEvent<NovedadFijaDto> event) {
        this.empleadoDto.setNovedadEmpleadoDto(event.getObject());
        novedadEmpleadoDao.saveNovedadEmpleado(this.empleadoDto);
        messageUtil.addMessageInfo("Se ha guardado correctamente");

    }

    public void onRowEditVariable(final RowEditEvent<NovedadVariableDto> event) {
        this.empleadoDto.setNovedadVariableDto(event.getObject());
        novedadVariableDao.saveNovedadVariable(this.empleadoDto);
        messageUtil.addMessageInfo("Se ha guardado correctamente");

    }

    public void findMunicipioByDepartamento() {
        final Integer idDepartamento = this.empleadoDto.getDepartamentoDto()
                .getIdDepartamento();
        if (idDepartamento != null) {
            this.listMunicipioDto = municipioDao
                    .getListMunicipioByDepartamento(idDepartamento);
        }

    }

    public void save() {
        if (empleadoDao.isExistEmpleadoByIdent(this.empleadoDto)) {
            messageUtil.addMessageError("Ya existe un empleado con esa identificación");
        } else {
            empleadoDao.save(this.empleadoDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("empleadoDialog");
            init();
        }

    }

    public void saveNovedadEmpleado() {
        novedadEmpleadoDao.saveNovedadEmpleado(this.empleadoDto);
        messageUtil.addMessageInfo("Se ha guardado correctamente");
        empleadoDto.setNovedadEmpleadoDto(new NovedadFijaDto());
        empleadoDto.getNovedadEmpleadoDto().setIdConceptoNomina(new ConceptoNominaDto());
    }

    public void saveNovedadVariable() {
        novedadVariableDao.saveNovedadVariable(this.empleadoDto);
        messageUtil.addMessageInfo("Se ha guardado correctamente");
        empleadoDto.setNovedadVariableDto(new NovedadVariableDto());
        empleadoDto.getNovedadVariableDto().setIdConceptoNomina(new ConceptoNominaDto());
        empleadoDto.getNovedadVariableDto().setIdPeriodo(new PeriodosDto());

    }

    public void delete(final EmpleadoDto dto) {
        empleadoDao.delete(dto);
        messageUtil.addMessageInfo("Se ha eliminado correctamente");
    }

    public void preEdit(final EmpleadoDto dto) {
        this.empleadoDto = dto;
        this.listMunicipioDto = municipioDao
                .getListMunicipioByDepartamento(dto.getDepartamentoDto().getIdDepartamento());

    }

}
