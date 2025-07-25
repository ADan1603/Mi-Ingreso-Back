package com.mmhealth.MiIngresoBack.entities;

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

    @ManyToOne
    @JoinColumn(name = "reg_pt_id", referencedColumnName = "pt_id")
    private PermisoTemporal permisoTemporal;

    @ManyToOne
    @JoinColumn(name = "reg_col_id", referencedColumnName = "col_id")
    private Colaborador colaborador;
}
