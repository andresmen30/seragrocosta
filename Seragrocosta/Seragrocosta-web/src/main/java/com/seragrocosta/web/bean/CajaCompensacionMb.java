/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.CajaCompensacionDto;
import com.seragrocosta.web.dao.CajaCompensacionDao;
import com.seragrocosta.web.util.JsfUtil;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author USER
 */
@Named
@ViewScoped
public class CajaCompensacionMb implements Serializable {

    @Inject
    private CajaCompensacionDao cajaCompensacionDao;

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Getter
    @Setter
    private LazyDataModel<CajaCompensacionDto> listaCajaCompensacion;

    @Getter
    @Setter
    private CajaCompensacionDto cajaCompensacionDto;

    public CajaCompensacionMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listaCajaCompensacion = new LazyDataModel<CajaCompensacionDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<CajaCompensacionDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<CajaCompensacionDto> data = cajaCompensacionDao.getCajaCompensacionList(first, pageSize, sortField, sortOrder, filters);
                listaCajaCompensacion.setRowCount(cajaCompensacionDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        cajaCompensacionDto = new CajaCompensacionDto();
    }

    public void save() {
        if (cajaCompensacionDao.isExistNit(this.cajaCompensacionDto)) {
            messageUtil.addMessageError("Ya existe ese nit para una entidad");
        } else {
            cajaCompensacionDao.save(this.cajaCompensacionDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("cajaCompensacionDialog");
            init();
        }
    }

    public void delete(final CajaCompensacionDto dto) {
        if (dto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            cajaCompensacionDao.delete(dto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }
    }

}
