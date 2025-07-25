package com.mmhealth.MiIngresoBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrotimbre")
public class RegistroTimbre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reg_id")
    private Long id;

    @Column(name = "reg_fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "reg_estado", length = 1)
    private String estado;

    @Column(name = "reg_tipotimbre", length = 50)
    private String tipoTimbre;

    @Column(name = "reg_identificacion", length = 150)
    private String identificacion;

    @Column(name = "reg_nombre", length = 150)
    private String nombre;

    @Column(name = "reg_razonsocial", length = 150)
    private String razonSocial;

    @Column(name = "reg_nombrecomercial", length = 150)
    private String nombreComercial;

    @Column(name = "reg_suc_nombre", length = 150)
    private String sucursalNombre;

    @Column(name = "reg_area_nombre", length = 150)
    private String areaNombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reg_pt_id", referencedColumnName = "pt_id")
    @JsonBackReference
    private PermisoTemporal permisoTemporal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reg_col_id", referencedColumnName = "col_id")
    @JsonBackReference
    private Colaborador colaborador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoTimbre() {
        return tipoTimbre;
    }

    public void setTipoTimbre(String tipoTimbre) {
        this.tipoTimbre = tipoTimbre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getSucursalNombre() {
        return sucursalNombre;
    }

    public void setSucursalNombre(String sucursalNombre) {
        this.sucursalNombre = sucursalNombre;
    }

    public String getAreaNombre() {
        return areaNombre;
    }

    public void setAreaNombre(String areaNombre) {
        this.areaNombre = areaNombre;
    }

    public PermisoTemporal getPermisoTemporal() {
        return permisoTemporal;
    }

    public void setPermisoTemporal(PermisoTemporal permisoTemporal) {
        this.permisoTemporal = permisoTemporal;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
