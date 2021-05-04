/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.FondoPensionDto;
import com.seragrocosta.web.dao.FondoPensionDao;
import com.seragrocosta.web.util.JsfUtil;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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
public class FondoPensionMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private FondoPensionDao fondoPensionDao;

    @Getter
    @Setter
    private FondoPensionDto fondoPensionDto;

    @Getter
    @Setter
    private LazyDataModel<FondoPensionDto> listaFondoPension;

    public FondoPensionMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listaFondoPension = new LazyDataModel<FondoPensionDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<FondoPensionDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<FondoPensionDto> data = fondoPensionDao.getFondoPensionList(first, pageSize, sortField, sortOrder, filters);
                listaFondoPension.setRowCount(fondoPensionDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        fondoPensionDto = new FondoPensionDto();
    }

    public void save() {
        if (fondoPensionDao.isExistNit(this.fondoPensionDto)) {
            messageUtil.addMessageError("Ya existe ese nit para una entidad");
        } else {
            fondoPensionDao.save(this.fondoPensionDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("fondoPensionDialog");
            init();
        }

    }

    public void delete(final FondoPensionDto dto) {
        if (dto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            fondoPensionDao.delete(dto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }

    }

}
