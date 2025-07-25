package com.mmhealth.MiIngresoBack.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "empresa")
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_razon_social", length = 50)
    private String razonSocial;

    @Column(name = "emp_identificacion", length = 40)
    private String identificacion;

    @Column(name = "emp_estado", length = 20)
    private String estado;

    @Column(name = "emp_nombre_comercial", length = 255)
    private String nombreComercial;

    @Column(name = "emp_ubicacion", length = 255)
    private String ubicacion;

    @Column(name = "emp_telefono", length = 20)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "grp_emp_id", referencedColumnName = "gr_id")
    private Grupo grupo;

    @OneToMany(mappedBy = "empresa")
    private List<Sucursal> sucursales;

    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "empresa")
    private List<Pertenencia> pertenencias;

    @OneToMany(mappedBy = "empresa")
    private List<PermisoTemporal> permisosTemporales;}
