/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.ConceptoNominaDto;
import com.seragrocosta.ejb.enums.TipoContable;
import com.seragrocosta.web.dao.ConceptoNominaDao;
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
import org.apache.commons.collections4.CollectionUtils;
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
public class ConceptoNominaMb implements Serializable {
    
    @Inject
    private JsfUtil jsfUtil;
    
    @Inject
    private MessageUtil messageUtil;
    
    @Inject
    private ConceptoNominaDao concepoNominaDao;
    
    @Getter
    @Setter
    private String[] conceptosSalariales;
    
    @Getter
    @Setter
    private ConceptoNominaDto conceptoNominaDto;
    
    @Getter
    @Setter
    private LazyDataModel<ConceptoNominaDto> listConceptoNomina;
    
    public ConceptoNominaMb() {
        super();
    }
    
    @PostConstruct
    public void inicializate() {
        listConceptoNomina = new LazyDataModel<ConceptoNominaDto>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public List<ConceptoNominaDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<ConceptoNominaDto> data = concepoNominaDao.getList(first, pageSize, sortField, sortOrder, filters);
                listConceptoNomina.setRowCount(concepoNominaDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }
    
    public void init() {
        conceptoNominaDto = new ConceptoNominaDto();
        conceptosSalariales = null;
    }
    
    public void clearCalculator() {
        conceptoNominaDto = new ConceptoNominaDto();
    }
    
    public void guardar() {
        this.conceptoNominaDto.setFormula(this.conceptoNominaDto.getResultCalculator());
        if (!this.conceptoNominaDto.getTipoContable().equals(TipoContable.CANTIDAD)) {
            this.conceptoNominaDto.setCantidad(null);
        }
        concepoNominaDao.save(this.conceptoNominaDto, conceptosSalariales);
        jsfUtil.hideDialog("conceptosNominaDialog");
        init();
        messageUtil.addMessageInfo("Se ha guardado correctamente");
    }
    
    public void preEdit(final ConceptoNominaDto conceptoNominaDto) {
        this.conceptoNominaDto = conceptoNominaDto;
        this.conceptoNominaDto.setResultCalculator(conceptoNominaDto.getFormula());
        if (CollectionUtils.isNotEmpty(conceptoNominaDto.getListConceptoSalarial())) {
            final int index = conceptoNominaDto.getListConceptoSalarial().size();
            conceptosSalariales = new String[index];
            for (int i = NumberUtils.INTEGER_ZERO; i < index; i++) {
                conceptosSalariales[i] = conceptoNominaDto.getListConceptoSalarial().get(i).getIdConceptoSalarial().getNombre();
            }
        }
    }
    
}
