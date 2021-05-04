/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.CesantiasDto;
import com.seragrocosta.web.dao.CesantiasDao;
import com.seragrocosta.web.util.JsfUtil;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
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
public class CesantiasMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private CesantiasDao cesantiasDao;

    @Getter
    @Setter
    private CesantiasDto cesantiasDto;

    @Getter
    @Setter
    private LazyDataModel<CesantiasDto> listCesantias;

    public CesantiasMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listCesantias = new LazyDataModel<CesantiasDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<CesantiasDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<CesantiasDto> data = cesantiasDao.getCesantiasList(first, pageSize, sortField, sortOrder, filters);
                listCesantias.setRowCount(cesantiasDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        cesantiasDto = new CesantiasDto();
    }

    public void save() {
        if (cesantiasDao.isExistNit(this.cesantiasDto)) {
            messageUtil.addMessageError("Ya existe ese nit para una entidad");
        } else {
            cesantiasDao.save(this.cesantiasDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("cesantiasDialog");
            init();
        }
    }

    public void delete(final CesantiasDto dto) {
        if (dto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            cesantiasDao.delete(dto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }

    }

}
