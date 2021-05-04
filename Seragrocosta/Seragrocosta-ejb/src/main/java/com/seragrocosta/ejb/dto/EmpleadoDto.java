/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.dto;

import com.seragrocosta.ejb.enums.Sexo;
import com.seragrocosta.ejb.enums.StatusEmpleado;
import com.seragrocosta.ejb.enums.TipoContrato;
import com.seragrocosta.ejb.enums.TipoIdentificacion;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
public class EmpleadoDto {

    private Integer idEmpleado;
    private TipoIdentificacion tipoIdentificacion;
    private String identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Sexo sexo;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String celular;
    private String email;
    private LocalDate fechaIngreso;
    private LocalDate fechaRetiro;
    private TipoContrato tipoContrato;
    private DepartamentoDto departamentoDto;
    private MunicipioDto municipioDto;
    private NominaDto nominaDto;
    private NovedadFijaDto novedadEmpleadoDto;
    private NovedadVariableDto novedadVariableDto;
    private StatusEmpleado estado;

}
