/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.ArlDto;
import com.seragrocosta.web.dao.ArlDao;
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
public class ArlMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private ArlDao arlDao;

    @Getter
    @Setter
    private ArlDto arlDto;

    @Getter
    @Setter
    private LazyDataModel<ArlDto> listaArl;

    public ArlMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listaArl = new LazyDataModel<ArlDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<ArlDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<ArlDto> data = arlDao.getArlList(first, pageSize, sortField, sortOrder, filters);
                listaArl.setRowCount(arlDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        arlDto = new ArlDto();
    }

    public void save() {
        if (arlDao.isExistNit(this.arlDto)) {
            messageUtil.addMessageError("Ya existe una arl con ese nit");
        } else {
            arlDao.save(this.arlDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("arlDialog");
            init();
        }
    }

    public void delete(final ArlDto dto) {
        if (dto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            arlDao.delete(dto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }

    }

}
