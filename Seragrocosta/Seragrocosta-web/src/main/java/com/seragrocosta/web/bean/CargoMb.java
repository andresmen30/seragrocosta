/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.CargoDto;
import com.seragrocosta.web.dao.CargoDao;
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
public class CargoMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private CargoDao cargoDao;

    @Getter
    @Setter
    private LazyDataModel<CargoDto> listCargo;

    @Getter
    @Setter
    private CargoDto cargoDto;

    public CargoMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listCargo = new LazyDataModel<CargoDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<CargoDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<CargoDto> data = cargoDao.getCargonList(first, pageSize, sortField, sortOrder, filters);
                listCargo.setRowCount(cargoDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        cargoDto = new CargoDto();
    }

    public void save() {
        if (this.cargoDao.isExistNombre(this.cargoDto)) {
            messageUtil.addMessageError("Ya existe un cargo con ese nombre");
        } else {
            cargoDao.save(this.cargoDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("cargoDialog");
            init();
        }
    }

    public void delete(final CargoDto dto) {
        if (dto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            cargoDao.delete(dto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }

    }

}
