/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.BancoDto;
import com.seragrocosta.web.dao.BancoDao;
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
public class BancoMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private BancoDao bancoDao;

    @Getter
    @Setter
    private BancoDto bancoDto;

    @Getter
    @Setter
    private LazyDataModel<BancoDto> listaBanco;

    public BancoMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listaBanco = new LazyDataModel<BancoDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<BancoDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<BancoDto> data = bancoDao.getBancoList(first, pageSize, sortField, sortOrder, filters);
                listaBanco.setRowCount(bancoDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        bancoDto = new BancoDto();
    }

    public void save() {
        if (bancoDao.isExistCode(this.bancoDto)) {
            messageUtil.addMessageError("Ya existe ese codigo para una entidad");
        } else {
            bancoDao.save(this.bancoDto);
            messageUtil.addMessageInfo("Se ha guardado correctamente");
            jsfUtil.hideDialog("bancoDialog");
            init();
        }
    }

    public void delete(final BancoDto dto) {
        if (dto.getNomina() > NumberUtils.INTEGER_ZERO) {
            messageUtil.addMessageError("No se puede eliminar este registro, ya que esta siendo utilizado por otro recurso");
        } else {
            bancoDao.delete(dto);
            messageUtil.addMessageInfo("Se ha eliminado correctamente");
        }

    }

}
