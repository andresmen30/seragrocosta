/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class FondoPensionDto {

    private Integer idFondoPension;
    private String nombre;
    private LocalDate fechaCreacion;
    private String nit;
    private String descripcion;
    private Integer nomina;

    public FondoPensionDto() {
        super();
    }

}
