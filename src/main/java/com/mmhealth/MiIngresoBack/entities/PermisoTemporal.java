package com.mmhealth.MiIngresoBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "permisotemporal")
public class PermisoTemporal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pt_id")
    private Long id;

    @Column(name = "pt_fechavisita")
    private LocalDateTime fechaVisita;

    @Column(name = "pt_estado", length = 20)
    private String estado;

    @Column(name = "pt_motivo", length = 500)
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_gr_id", referencedColumnName = "gr_id")
    @JsonBackReference
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_emp_id", referencedColumnName = "emp_id")
    @JsonBackReference
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_suc_id", referencedColumnName = "suc_id")
    @JsonBackReference
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_ar_id", referencedColumnName = "ar_id")
    @JsonBackReference
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_col_id", referencedColumnName = "col_id")
    @JsonBackReference
    private Colaborador colaborador;

    @OneToMany(mappedBy = "permisoTemporal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RegistroTimbre> registrosTimbre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(LocalDateTime fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<RegistroTimbre> getRegistrosTimbre() {
        return registrosTimbre;
    }

    public void setRegistrosTimbre(List<RegistroTimbre> registrosTimbre) {
        this.registrosTimbre = registrosTimbre;
    }
}
