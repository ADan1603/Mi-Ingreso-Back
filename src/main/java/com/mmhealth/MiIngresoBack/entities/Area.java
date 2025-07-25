package com.mmhealth.MiIngresoBack.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "area")
@Data
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ar_id")
    private Long id;

    @Column(name = "ar_nombre", length = 50)
    private String nombre;

    @Column(name = "ar_departamento", length = 40)
    private String departamento;

    @ManyToOne
    @JoinColumn(name = "ar_suc_id", referencedColumnName = "suc_id")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "area")
    private List<Pertenencia> pertenencias;

    @OneToMany(mappedBy = "area")
    private List<PermisoTemporal> permisosTemporales;
}