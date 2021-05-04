/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.bean;

import com.seragrocosta.ejb.dto.DetalleLiquidacionDto;
import com.seragrocosta.ejb.dto.EmpleadoDto;
import com.seragrocosta.ejb.dto.LiquidacionDto;
import com.seragrocosta.ejb.dto.ParametrizacionNominaDto;
import com.seragrocosta.ejb.dto.PeriodosDto;
import com.seragrocosta.web.dao.DetalleLiquidacionDao;
import com.seragrocosta.web.dao.EmpleadoDao;
import com.seragrocosta.web.dao.LiquidacionDao;
import com.seragrocosta.web.dao.ParametrizacionNominaDao;
import com.seragrocosta.web.dao.PeriodoDao;
import com.seragrocosta.web.util.Constants;
import com.seragrocosta.web.util.JsfUtil;
import com.seragrocosta.web.util.MessageUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class LiquidacionMb implements Serializable {

    @Inject
    private JsfUtil jsfUtil;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private PeriodoDao periodoDao;

    @Inject
    private LiquidacionDao liquidacionDao;

    @Inject
    private EmpleadoDao empleadoDao;

    @Inject
    private ParametrizacionNominaDao parametrizacionNominaDao;

    @Inject
    private DetalleLiquidacionDao detalleLiquidacionDao;

    @Getter
    @Setter
    private LiquidacionDto liquidacionDto;

    @Getter
    @Setter
    private LazyDataModel<LiquidacionDto> listaLiquidacion;

    /**
     * Creates a new instance of LiquidacionMb
     */
    public LiquidacionMb() {
        super();
    }

    @PostConstruct
    public void inicializate() {
        listaLiquidacion = new LazyDataModel<LiquidacionDto>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<LiquidacionDto> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<LiquidacionDto> data = liquidacionDao.getPeriodosList(first, pageSize, sortField, sortOrder, filters);
                listaLiquidacion.setRowCount(liquidacionDao.numeroRegistros(filters).intValue());
                return data;
            }
        };
        init();
    }

    public void init() {
        liquidacionDto = new LiquidacionDto();
        liquidacionDto.setLiquidacionAll(true);
        liquidacionDto.setIdPeriodo(new PeriodosDto());
    }

    public void changeSelectLiquidacion() {
        if (liquidacionDto.isLiquidacionAll()) {
            liquidacionDto.setSearchEmpleado(StringUtils.EMPTY);
        }
    }

    public void liquidar() {
        switch (this.liquidacionDto.getTipoLiquidacion()) {
            case PERIODICO:
                final ParametrizacionNominaDto param = parametrizacionNominaDao.getParametrizacionDto();
                if (liquidacionDto.isLiquidacionAll()) {
                    final List<EmpleadoDto> empleadosActivos = empleadoDao.searchEmpleadosByStatus();
                    if (CollectionUtils.isNotEmpty(empleadosActivos)) {
                        final PeriodosDto periodoDto = this.periodoDao.getPeriodoById(this.liquidacionDto.getIdPeriodo().getIdPeriodo());
                        this.liquidacionDto.setIdPeriodo(periodoDto);
                        final LiquidacionDto liquidacionSaveDto = liquidacionDao.save(this.liquidacionDto);
                        final List<DetalleLiquidacionDto> listDetalleLiquiProce = new ArrayList<>();
                        empleadosActivos.stream().forEach(empleado -> {
                            DetalleLiquidacionDto detalleLiquiProce = new DetalleLiquidacionDto();
                            detalleLiquiProce.setIdEmpleado(empleado);
                            detalleLiquiProce.setDiasTrabajados(calcularDiasPorFechas(liquidacionSaveDto.getIdPeriodo()));
                            detalleLiquiProce.setSalarioDevengado(obtenerSalarioDevengado(empleado, detalleLiquiProce.getDiasTrabajados()));
                            detalleLiquiProce.setAuxilioTransporte(obtenerAuxilioTransporte(empleado, param, detalleLiquiProce.getDiasTrabajados()));
                            detalleLiquiProce.setTotalDevengado(obtenerTotalDevengado(detalleLiquiProce.getSalarioDevengado(),
                                    detalleLiquiProce.getAuxilioTransporte()));
                            detalleLiquiProce.setPension(obtenerPensionEmpleado(detalleLiquiProce, param));
                            detalleLiquiProce.setSalud(obtenerSaludEmpleado(detalleLiquiProce, param));
                            detalleLiquiProce.setTotalDeducido(obtenerTotalDeducciones(detalleLiquiProce));
                            detalleLiquiProce.setTotalNeto(obtenerTotalNeto(detalleLiquiProce));
                            detalleLiquiProce.setIdLiquidacion(liquidacionSaveDto);
                            listDetalleLiquiProce.add(detalleLiquiProce);
                        });
                        this.detalleLiquidacionDao.save(listDetalleLiquiProce);
                        messageUtil.addMessageInfo("Se ha liquidado el periodo ".concat(liquidacionSaveDto.getIdPeriodo().getNombreFechas()));
                    }
                } else {
                    final PeriodosDto periodoDto = this.periodoDao.getPeriodoById(this.liquidacionDto.getIdPeriodo().getIdPeriodo());
                    this.liquidacionDto.setIdPeriodo(periodoDto);
                    final LiquidacionDto liquidacionSaveDto = liquidacionDao.save(this.liquidacionDto);
                    final List<DetalleLiquidacionDto> listDetalleLiquiProce = new ArrayList<>();
                    DetalleLiquidacionDto detalleLiquiProce = new DetalleLiquidacionDto();
                    final String identificacion = StringUtils
                            .split(this.liquidacionDto.getSearchEmpleado(), "-")[NumberUtils.INTEGER_ZERO];
                    final EmpleadoDto empleado = empleadoDao.searchIdByIdentificacion(identificacion);
                    detalleLiquiProce.setIdEmpleado(empleado);
                    detalleLiquiProce.setDiasTrabajados(calcularDiasPorFechas(liquidacionSaveDto.getIdPeriodo()));
                    detalleLiquiProce.setSalarioDevengado(obtenerSalarioDevengado(empleado, detalleLiquiProce.getDiasTrabajados()));
                    detalleLiquiProce.setAuxilioTransporte(obtenerAuxilioTransporte(empleado, param, detalleLiquiProce.getDiasTrabajados()));
                    detalleLiquiProce.setTotalDevengado(obtenerTotalDevengado(detalleLiquiProce.getSalarioDevengado(),
                            detalleLiquiProce.getAuxilioTransporte()));
                    detalleLiquiProce.setPension(obtenerPensionEmpleado(detalleLiquiProce, param));
                    detalleLiquiProce.setSalud(obtenerSaludEmpleado(detalleLiquiProce, param));
                    detalleLiquiProce.setTotalDeducido(obtenerTotalDeducciones(detalleLiquiProce));
                    detalleLiquiProce.setTotalNeto(obtenerTotalNeto(detalleLiquiProce));
                    detalleLiquiProce.setIdLiquidacion(liquidacionSaveDto);
                    listDetalleLiquiProce.add(detalleLiquiProce);
                    this.detalleLiquidacionDao.save(listDetalleLiquiProce);
                    messageUtil.addMessageInfo("Se ha liquidado el empleado " 
                            + empleado.getPrimerNombre().concat(" ")
                                    .concat(empleado.getPrimerApellido()).concat(" para el periodo ")
                                    .concat(liquidacionSaveDto.getIdPeriodo().getNombreFechas()));
                }
                break;
        }
    }

    private Integer calcularDiasPorFechas(final PeriodosDto periodosDto) {
        final Long daysBetween = DAYS.between(periodosDto.getFechaInicial(),
                periodosDto.getFechaFinal());
        final Long daysLiquidados = Arrays.stream(Constants.NUMEROS_QUINCENAL)
                .anyMatch(numberDayQ -> numberDayQ.compareTo(daysBetween) == NumberUtils.INTEGER_ZERO)
                ? Constants.FIFTEEN.longValue() : Arrays.stream(Constants.NUMEROS_MENSUAL)
                .anyMatch(numberDayM -> numberDayM.compareTo(daysBetween) == NumberUtils.INTEGER_ZERO)
                ? Constants.THIRTY.longValue() : daysBetween;
        return daysLiquidados.intValue();
    }

    private BigDecimal obtenerSalarioDevengado(final EmpleadoDto empleadoDto, final Integer days) {
        final BigDecimal daysDecimal = BigDecimal.valueOf(days.longValue());
        final BigDecimal dividir = empleadoDto.getNominaDto()
                .getAsignacionBasica().divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP);
        final BigDecimal resultado = dividir.multiply(daysDecimal);
        return resultado;
    }

    private BigDecimal obtenerAuxilioTransporte(final EmpleadoDto empleadoDto, final ParametrizacionNominaDto param, final Integer days) {
        final BigDecimal validarSalario = param.getSalarioMinimo()
                .add(param.getSalarioMinimo());
        if (empleadoDto.getNominaDto().getAsignacionBasica().compareTo(validarSalario) == -1) {
            final BigDecimal daysDecimal = BigDecimal.valueOf(days.longValue());
            final BigDecimal auxilioTransporte = param.getAuxilioTransporte();
            final BigDecimal division = auxilioTransporte.divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP);
            final BigDecimal resultado = division.multiply(daysDecimal);
            return resultado;
        } else {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal obtenerPensionEmpleado(final DetalleLiquidacionDto detalleLiquidacionDto, final ParametrizacionNominaDto param) {
        final BigDecimal salario = detalleLiquidacionDto.getSalarioDevengado();
        final BigDecimal resultado = salario.multiply(param.getPension());
        return resultado;
    }

    private BigDecimal obtenerSaludEmpleado(final DetalleLiquidacionDto detalleLiquidacionDto, final ParametrizacionNominaDto param) {
        final BigDecimal salario = detalleLiquidacionDto.getSalarioDevengado();
        final BigDecimal resultado = salario.multiply(param.getSaludEmpleado());
        return resultado;
    }

    private BigDecimal obtenerTotalDevengado(final BigDecimal salarioDevengado, final BigDecimal auxilioTransporte) {
        final BigDecimal totalDevengado = salarioDevengado.add(auxilioTransporte);
        return totalDevengado;
    }

    private BigDecimal obtenerTotalDeducciones(final DetalleLiquidacionDto detalleLiquidacionDto) {
        final BigDecimal resultado = detalleLiquidacionDto.getPension().add(detalleLiquidacionDto.getSalud());
        return resultado;
    }

    private BigDecimal obtenerTotalNeto(final DetalleLiquidacionDto detalleLiquidacionDto) {
        final BigDecimal resultado = detalleLiquidacionDto.getTotalDevengado().subtract(detalleLiquidacionDto.getTotalDeducido());
        return resultado;
    }

    public List<String> empleadosByNameOrNit(final String query) {
        return empleadoDao.empleadosByNameOrNit(query);
    }

}
