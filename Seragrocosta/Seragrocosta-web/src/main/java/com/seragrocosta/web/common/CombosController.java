/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.common;

import com.seragrocosta.ejb.dto.AreaDto;
import com.seragrocosta.ejb.dto.ArlDto;
import com.seragrocosta.ejb.dto.BancoDto;
import com.seragrocosta.ejb.dto.CajaCompensacionDto;
import com.seragrocosta.ejb.dto.CargoDto;
import com.seragrocosta.ejb.dto.CesantiasDto;
import com.seragrocosta.ejb.dto.ConceptoNominaDto;
import com.seragrocosta.ejb.dto.ConceptoSalarialDto;
import com.seragrocosta.ejb.dto.DepartamentoDto;
import com.seragrocosta.ejb.dto.EpsDto;
import com.seragrocosta.ejb.dto.FondoPensionDto;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.ejb.enums.BancoTipoCuenta;
import com.seragrocosta.ejb.enums.ConditionBoolean;
import com.seragrocosta.ejb.enums.EstadoCivil;
import com.seragrocosta.ejb.enums.NivelArl;
import com.seragrocosta.ejb.enums.Sexo;
import com.seragrocosta.ejb.enums.TipoContable;
import com.seragrocosta.ejb.enums.TipoContrato;
import com.seragrocosta.ejb.enums.TipoIdentificacion;
import com.seragrocosta.ejb.enums.TipoLiquidacion;
import com.seragrocosta.ejb.enums.TipoNomina;
import com.seragrocosta.web.dao.AreaDao;
import com.seragrocosta.web.dao.ArlDao;
import com.seragrocosta.web.dao.BancoDao;
import com.seragrocosta.web.dao.CajaCompensacionDao;
import com.seragrocosta.web.dao.CargoDao;
import com.seragrocosta.web.dao.CesantiasDao;
import com.seragrocosta.web.dao.ConceptoNominaDao;
import com.seragrocosta.web.dao.ConceptoSalarialDao;
import com.seragrocosta.web.dao.DepartamentoDao;
import com.seragrocosta.web.dao.EpsDao;
import com.seragrocosta.web.dao.FondoPensionDao;
import com.seragrocosta.web.dao.PeriodoDao;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author USER
 */
@Named
@ViewScoped
public class CombosController implements Serializable {

    @Inject
    private ConceptoSalarialDao conceptoSalarialFacade;

    @Inject
    private DepartamentoDao departamentoDao;

    @Inject
    private CargoDao cargoDao;

    @Inject
    private AreaDao areaDao;

    @Inject
    private CajaCompensacionDao cajaCompensacionDao;

    @Inject
    private EpsDao epsDao;

    @Inject
    private FondoPensionDao fondoPensionDao;

    @Inject
    private ArlDao arlDao;

    @Inject
    private BancoDao bancoDao;

    @Inject
    private CesantiasDao cesantiasDao;

    @Inject
    private ConceptoNominaDao conceptoNominaDao;

    @Inject
    private PeriodoDao periodoDao;
    
    @Produces
    @Named("conceptosSalariales")
    public List<ConceptoSalarialDto> getParametrizationConcepSala() {
        return conceptoSalarialFacade.getConceptoSalarialAll();
    }

    @Produces
    @Named("listCargos")
    public List<CargoDto> getListCargos() {
        return cargoDao.getListCargos();
    }

    @Produces
    @Named("departamentos")
    public List<DepartamentoDto> getListDepartamentos() {
        return departamentoDao.getListDepartamentos();
    }

    @Produces
    @Named("listAreas")
    public List<AreaDto> getListAreas() {
        return areaDao.getListAreas();
    }

    @Produces
    @Named("listCajaCompensaciones")
    public List<CajaCompensacionDto> getListCajaCompensaciones() {
        return cajaCompensacionDao.getListCajaCompensaciones();
    }

    @Produces
    @Named("listEps")
    public List<EpsDto> getListEps() {
        return epsDao.getListEps();
    }

    @Produces
    @Named("listFondoPensiones")
    public List<FondoPensionDto> getListFondoPensiones() {
        return fondoPensionDao.getListFondoPensiones();
    }

    @Produces
    @Named("listArls")
    public List<ArlDto> getListArls() {
        return arlDao.getListArls();
    }

    @Produces
    @Named("listBancos")
    public List<BancoDto> getListBancos() {
        return bancoDao.getListBancos();
    }

    @Produces
    @Named("listCesantias")
    public List<CesantiasDto> getListCesantias() {
        return cesantiasDao.getListCesantias();
    }

    @Produces
    @Named("tiposIdentificacion")
    public TipoIdentificacion[] getTiposIdentificacion() {
        return TipoIdentificacion.values();
    }

    @Produces
    @Named("sexos")
    public Sexo[] getSexos() {
        return Sexo.values();
    }

    @Produces
    @Named("estadosCivil")
    public EstadoCivil[] getEstadosCivil() {
        return EstadoCivil.values();
    }

    @Produces
    @Named("conditionBooleans")
    public ConditionBoolean[] getCondtionBooleans() {
        final ConditionBoolean[] conditionBooleans = ConditionBoolean.values();
        Arrays.sort(conditionBooleans);
        return conditionBooleans;
    }

    @Produces
    @Named("tiposCuenta")
    public BancoTipoCuenta[] getTipoCuenta() {
        return BancoTipoCuenta.values();
    }

    @Produces
    @Named("tipoContratos")
    public TipoContrato[] getTipoContratos() {
        return TipoContrato.values();
    }

    @Produces
    @Named("tiposNomina")
    public TipoNomina[] getTiposNomina() {
        return TipoNomina.values();
    }

    @Produces
    @Named("tiposContable")
    public TipoContable[] getTiposContable() {
        return TipoContable.values();
    }

    @Produces
    @Named("conceptosNomina")
    public List<ConceptoNominaDto> getConceptosNomina() {
        return conceptoNominaDao.completeConceptoNomina();
    }

    @Produces
    @Named("periodos")
    public List<PeriodosDto> getPeriodos() {
        return periodoDao.getPeriodoList();
    }

    @Produces
    @Named("nivelesArl")
    public NivelArl[] getNivelesArl() {
        return NivelArl.values();
    }

    @Produces
    @Named("tiposLiquidacion")
    public TipoLiquidacion[] getTipoLiquidaciones() {
        return TipoLiquidacion.values();
    }

}
