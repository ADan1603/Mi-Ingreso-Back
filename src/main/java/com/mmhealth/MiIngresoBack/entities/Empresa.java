package com.mmhealth.MiIngresoBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "empresa")
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grp_emp_id", referencedColumnName = "gr_id")
    @JsonBackReference
    private Grupo grupo;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Sucursal> sucursales;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pertenencia> pertenencias;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PermisoTemporal> permisosTemporales;}
