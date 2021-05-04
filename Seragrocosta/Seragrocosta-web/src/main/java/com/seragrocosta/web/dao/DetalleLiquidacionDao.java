/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.dao;

import com.seragrocosta.ejb.dto.DetalleLiquidacionDto;
import com.seragrocosta.ejb.facade.DetalleLiquidacionFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author USER
 */
@Stateless
public class DetalleLiquidacionDao implements Serializable {
    
    @EJB
    private DetalleLiquidacionFacade detalleLiquidacionFacade;
    
    public void save(final List<DetalleLiquidacionDto> listaDetalleDto) {
        detalleLiquidacionFacade.save(listaDetalleDto);
    }
    
}
