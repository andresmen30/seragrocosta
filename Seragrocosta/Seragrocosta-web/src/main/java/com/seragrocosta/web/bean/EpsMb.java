/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.EpsDto;
import com.seragrocosta.web.dao.EpsDao;
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
public class EpsMb implements Serializable {

    @Inject
    private EpsDao epsDao;

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Getter
    @Setter
    private LazyDataModel<EpsDto> listaEps;

    @Getter
    @Setter
    private EpsDto epsDto;

    /**
     * Creates a new instance of EpsMb
     */
    public EpsMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listaEps = new LazyDataModel<EpsDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<EpsDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<EpsDto> data = epsDao.getEpsList(first, pageSize, sortField, sortOrder, filters);
                listaEps.setRowCount(epsDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        epsDto = new EpsDto();
    }

    public void save() {
        if (epsDao.isExistNit(this.epsDto)) {
            messageUtil.addMessageError("Ya existe ese nit para una entidad");
        } else {
            epsDao.save(this.epsDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("epsDialog");
            init();
        }

    }

    public void delete(final EpsDto epsDto) {
        if (epsDto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            epsDao.delete(epsDto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }
    }

}
