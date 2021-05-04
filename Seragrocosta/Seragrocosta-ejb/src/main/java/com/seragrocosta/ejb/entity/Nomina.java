/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.entity;

import com.seragrocosta.ejb.enums.BancoTipoCuenta;
import com.seragrocosta.ejb.enums.EstadoCivil;
import com.seragrocosta.ejb.enums.NivelArl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomina.findAll", query = "SELECT n FROM Nomina n")
    , @NamedQuery(name = "Nomina.findByIdNomina", query = "SELECT n FROM Nomina n WHERE n.idNomina = :idNomina")
    , @NamedQuery(name = "Nomina.findByAsignacionBasica", query = "SELECT n FROM Nomina n WHERE n.asignacionBasica = :asignacionBasica")
    , @NamedQuery(name = "Nomina.findByEstadoCivil", query = "SELECT n FROM Nomina n WHERE n.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "Nomina.findByLibretaMilitar", query = "SELECT n FROM Nomina n WHERE n.libretaMilitar = :libretaMilitar")
    , @NamedQuery(name = "Nomina.findByFechaInicialCajaCompensacion", query = "SELECT n FROM Nomina n WHERE n.fechaInicialCajaCompensacion = :fechaInicialCajaCompensacion")
    , @NamedQuery(name = "Nomina.findByFechaInicialArl", query = "SELECT n FROM Nomina n WHERE n.fechaInicialArl = :fechaInicialArl")
    , @NamedQuery(name = "Nomina.findByFechaInicialEps", query = "SELECT n FROM Nomina n WHERE n.fechaInicialEps = :fechaInicialEps")
    , @NamedQuery(name = "Nomina.findByFechaInicialFondoPension", query = "SELECT n FROM Nomina n WHERE n.fechaInicialFondoPension = :fechaInicialFondoPension")
    , @NamedQuery(name = "Nomina.findByFechaInicialCesantias", query = "SELECT n FROM Nomina n WHERE n.fechaInicialCesantias = :fechaInicialCesantias")
    , @NamedQuery(name = "Nomina.findByFechaFinalCajaCompensacion", query = "SELECT n FROM Nomina n WHERE n.fechaFinalCajaCompensacion = :fechaFinalCajaCompensacion")
    , @NamedQuery(name = "Nomina.findByFechaFinalArl", query = "SELECT n FROM Nomina n WHERE n.fechaFinalArl = :fechaFinalArl")
    , @NamedQuery(name = "Nomina.findByFechaFinalEps", query = "SELECT n FROM Nomina n WHERE n.fechaFinalEps = :fechaFinalEps")
    , @NamedQuery(name = "Nomina.findByFechaFinalFondoPension", query = "SELECT n FROM Nomina n WHERE n.fechaFinalFondoPension = :fechaFinalFondoPension")
    , @NamedQuery(name = "Nomina.findByFechaFinalCesantias", query = "SELECT n FROM Nomina n WHERE n.fechaFinalCesantias = :fechaFinalCesantias")
    , @NamedQuery(name = "Nomina.findByBancoCuenta", query = "SELECT n FROM Nomina n WHERE n.bancoCuenta = :bancoCuenta")
    , @NamedQuery(name = "Nomina.findByBancoTipoCuenta", query = "SELECT n FROM Nomina n WHERE n.bancoTipoCuenta = :bancoTipoCuenta")})
@Getter
@Setter
public class Nomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nomina")
    private Integer idNomina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asignacion_basica")
    private BigDecimal asignacionBasica;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "libreta_militar")
    private boolean libretaMilitar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial_caja_compensacion")

    private LocalDate fechaInicialCajaCompensacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial_arl")

    private LocalDate fechaInicialArl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial_eps")

    private LocalDate fechaInicialEps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial_fondo_pension")

    private LocalDate fechaInicialFondoPension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial_cesantias")

    private LocalDate fechaInicialCesantias;
    @Column(name = "fecha_final_caja_compensacion")

    private LocalDate fechaFinalCajaCompensacion;
    @Column(name = "fecha_final_arl")

    private LocalDate fechaFinalArl;
    @Column(name = "fecha_final_eps")

    private LocalDate fechaFinalEps;
    @Column(name = "fecha_final_fondo_pension")

    private LocalDate fechaFinalFondoPension;
    @Column(name = "fecha_final_cesantias")

    private LocalDate fechaFinalCesantias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "banco_cuenta")
    private String bancoCuenta;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "banco_tipo_cuenta")
    private BancoTipoCuenta bancoTipoCuenta;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_arl")
    private NivelArl nivelArl;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Area idArea;
    @JoinColumn(name = "id_arl", referencedColumnName = "id_arl")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Arl idArl;
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Banco idBanco;
    @JoinColumn(name = "id_caja_compensacion", referencedColumnName = "id_caja_compensacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CajaCompensacion idCajaCompensacion;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cargo idCargo;
    @JoinColumn(name = "id_cesantias", referencedColumnName = "id_cesantias")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cesantias idCesantias;
    @JoinColumn(name = "id_eps", referencedColumnName = "id_eps")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Eps idEps;
    @JoinColumn(name = "id_fondo_pension", referencedColumnName = "id_fondo_pension")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FondoPension idFondoPension;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idNomina", fetch = FetchType.EAGER)
    private Empleado empleado;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNomina != null ? idNomina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomina)) {
            return false;
        }
        Nomina other = (Nomina) object;
        if ((this.idNomina == null && other.idNomina != null) || (this.idNomina != null && !this.idNomina.equals(other.idNomina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seragrocosta.ejb.entity.Nomina[ idNomina=" + idNomina + " ]";
    }

}
