/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.conversor;

import com.seragrocosta.ejb.dto.NominaDto;
import com.seragrocosta.ejb.entity.Nomina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author USER
 */
@Stateless
public class NominaConversor implements Serializable {
    
    @PersistenceContext(unitName = "SeragrocostaDs")
    private EntityManager em;
    
    @Inject
    private AreaConversor areaConversor;
    @Inject
    private ArlConversor arlConversor;
    @Inject
    private CajaCompensacionConversor cajaCompensacionConversor;
    @Inject
    private FondoPensionConversor fondoPensionConversor;
    @Inject
    private CargoConversor cargoConversor;
    @Inject
    private EpsConversor epsConversor;
    @Inject
    private BancoConversor bancoConversor;
    @Inject
    private CesantiasConversor cesantiasConversor;
    
    public Nomina getNominaFromDto(@NonNull final NominaDto dto) {
        final Nomina entity = new Nomina();
        entity.setAsignacionBasica(dto.getAsignacionBasica());
        entity.setEstadoCivil(dto.getEstadoCivil());
        entity.setIdArea(areaConversor.getAreaFromBd(dto.getIdArea().getIdArea()));
        entity.setIdArl(arlConversor.getArlFromBd(dto.getIdArl().getIdArl()));
        entity.setIdCajaCompensacion(cajaCompensacionConversor.getCajaCompensacionFromBd(dto.getIdCajaCompensacion()
                .getIdCajaCompensacion()));
        entity.setIdCargo(cargoConversor.getCargoFromBd(dto.getIdCargo().getIdCargo()));
        entity.setIdEps(epsConversor.getEpsFromBd(dto.getIdEps().getIdEps()));
        entity.setIdFondoPension(fondoPensionConversor.getFondoPensionFromBd(dto.getFondoPensionDto()
                .getIdFondoPension()));
        entity.setIdNomina(dto.getIdNomina());
        entity.setLibretaMilitar(dto.isLibretaMilitar());
        entity.setFechaInicialArl(dto.getFechaInicialArl());
        entity.setFechaInicialCajaCompensacion(dto.getFechaInicialCajaCompensacion());
        entity.setFechaInicialCesantias(dto.getFechaInicialCesantias());
        entity.setFechaInicialEps(dto.getFechaInicialEps());
        entity.setFechaInicialFondoPension(dto.getFechaInicialFondoPension());
        entity.setFechaFinalArl(dto.getFechaFinalArl());
        entity.setFechaFinalCajaCompensacion(dto.getFechaFinalCajaCompensacion());
        entity.setFechaFinalCesantias(dto.getFechaFinalCesantias());
        entity.setFechaFinalEps(dto.getFechaFinalEps());
        entity.setFechaFinalFondoPension(dto.getFechaFinalFondoPension());
        entity.setIdBanco(bancoConversor.getBancoFromBd(dto.getIdBanco().getIdBanco()));
        entity.setBancoCuenta(dto.getBancoCuenta());
        entity.setBancoTipoCuenta(dto.getBancoTipoCuenta());
        entity.setIdCesantias(cesantiasConversor.getCesantiasFromBd(dto.getCesantiasDto()
                .getIdCesantias()));
        entity.setNivelArl(dto.getNivelArl());
        return entity;
    }
    
    public NominaDto getNominaFromEntity(@NonNull final Nomina entity) {
        final NominaDto dto = new NominaDto();
        dto.setAsignacionBasica(entity.getAsignacionBasica());
        dto.setEstadoCivil(entity.getEstadoCivil());
        dto.setIdArea(areaConversor.getAreaFromEntity(entity.getIdArea()));
        dto.setIdArl(arlConversor.getArlFromEntity(entity.getIdArl()));
        dto.setIdCajaCompensacion(cajaCompensacionConversor.getCajaCompensacionFromEntity(entity.getIdCajaCompensacion()));
        dto.setIdCargo(cargoConversor.getCargoFromEntity(entity.getIdCargo()));
        dto.setFondoPensionDto(fondoPensionConversor.getFondoPensionFromDto(entity.getIdFondoPension()));
        dto.setIdEps(epsConversor.getEpsFromEntity(entity.getIdEps()));
        dto.setIdNomina(entity.getIdNomina());
        dto.setLibretaMilitar(entity.isLibretaMilitar());
        dto.setFechaInicialArl(entity.getFechaInicialArl());
        dto.setFechaInicialCajaCompensacion(entity.getFechaInicialCajaCompensacion());
        dto.setFechaInicialCesantias(entity.getFechaInicialCesantias());
        dto.setFechaInicialEps(entity.getFechaInicialEps());
        dto.setFechaInicialFondoPension(entity.getFechaInicialFondoPension());
        dto.setFechaFinalArl(entity.getFechaFinalArl());
        dto.setFechaFinalCajaCompensacion(entity.getFechaFinalCajaCompensacion());
        dto.setFechaFinalCesantias(entity.getFechaFinalCesantias());
        dto.setFechaFinalEps(entity.getFechaFinalEps());
        dto.setFechaFinalFondoPension(entity.getFechaFinalFondoPension());
        dto.setIdBanco(bancoConversor.getBancoFromEntity(entity.getIdBanco()));
        dto.setBancoCuenta(entity.getBancoCuenta());
        dto.setBancoTipoCuenta(entity.getBancoTipoCuenta());
        dto.setCesantiasDto(cesantiasConversor.getCesantiasFromEntity(entity.getIdCesantias()));
        dto.setNivelArl(entity.getNivelArl());
        return dto;
    }
    
    public List<NominaDto> getListNominaFromEntity(final List<Nomina> listEntity) {
        final List<NominaDto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(getNominaFromEntity(entity));
            });
        }
        return listDto;
    }
    
}
