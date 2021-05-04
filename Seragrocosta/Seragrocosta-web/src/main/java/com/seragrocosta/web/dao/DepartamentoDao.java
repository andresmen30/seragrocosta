/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.DepartamentoConversor;
import com.seragrocosta.ejb.dto.DepartamentoDto;
import com.seragrocosta.ejb.facade.DepartamentoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@Stateless
public class DepartamentoDao {

    @EJB
    private DepartamentoFacade departamentoFacade;

    @EJB
    private DepartamentoConversor departamentoConversor;

    public List<DepartamentoDto> getListDepartamentos() {
        return departamentoConversor.getListDepartamentoFromEntity(departamentoFacade.getListDepartamentos());
    }
}
