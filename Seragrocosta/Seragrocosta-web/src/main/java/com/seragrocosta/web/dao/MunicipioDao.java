/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.conversor.MunicipioConversor;
import com.seragrocosta.ejb.dto.MunicipioDto;
import com.seragrocosta.ejb.facade.MunicipioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@Stateless
public class MunicipioDao {

    @EJB
    private MunicipioConversor municipioConversor;

    @EJB
    private MunicipioFacade municipioFacade;

    public List<MunicipioDto> getListMunicipioByDepartamento(final int idDepartamento) {
        return municipioConversor.getListMunicipioFromEntity(municipioFacade
                .getListMunicipioByDepartamento(idDepartamento));
    }
}
