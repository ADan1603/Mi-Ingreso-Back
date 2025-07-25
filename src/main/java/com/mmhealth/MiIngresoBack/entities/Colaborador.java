package com.mmhealth.MiIngresoBack.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "colaborador")
@Data
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id")
    private Long id;
    @Column(name = "col_identificacion", length = 250)
    private String identificacion;

    @Column(name = "col_nombre", length = 255)
    private String nombre;

    @Column(name = "col_estado", length = 20)
    private String estado;

    @Column(name = "col_email", length = 255)
    private String email;

    @ManyToOne
    @JoinColumn(name = "col_per_id", referencedColumnName = "per_id")
    private Pertenencia pertenencia;

    @OneToMany(mappedBy = "colaborador")
    private List<PermisoTemporal> permisosTemporales;

    @OneToMany(mappedBy = "colaborador")
    private List<RegistroTimbre> registrosTimbre;
}
