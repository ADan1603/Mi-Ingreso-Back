package com.mmhealth.MiIngresoBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sucursal")
@Data
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "suc_id")
    private Long id;

    @Column(name = "suc_nombre", length = 250)
    private String nombre;

    @Column(name = "sub_ubicacion", length = 140)
    private String ubicacion;

    @Column(name = "suc_telefono", length = 40)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suc_emp_id", referencedColumnName = "emp_id")
    @JsonBackReference
    private Empresa empresa;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Area> areas;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pertenencia> pertenencias;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PermisoTemporal> permisosTemporales;
}
