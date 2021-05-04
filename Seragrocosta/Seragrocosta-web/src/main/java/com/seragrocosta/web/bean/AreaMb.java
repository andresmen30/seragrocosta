/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.AreaDto;
import com.seragrocosta.web.dao.AreaDao;
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
public class AreaMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private AreaDao areaDao;

    @Getter
    @Setter
    private AreaDto areaDto;

    @Getter
    @Setter
    private LazyDataModel<AreaDto> listaArea;

    public AreaMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listaArea = new LazyDataModel<AreaDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<AreaDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<AreaDto> data = areaDao.getAreaList(first, pageSize, sortField, sortOrder, filters);
                listaArea.setRowCount(areaDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        areaDto = new AreaDto();
    }

    public void save() {
        if (areaDao.isExistNombre(this.areaDto)) {
            messageUtil.addMessageError("Ya existe una area con ese nombre");
        } else {
            areaDao.save(this.areaDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("areaDialog");
            init();
        }
    }

    public void delete(final AreaDto dto) {
        if (dto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            areaDao.delete(dto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }

    }

}
