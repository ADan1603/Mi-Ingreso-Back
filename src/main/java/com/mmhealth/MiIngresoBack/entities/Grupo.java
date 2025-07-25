package com.mmhealth.MiIngresoBack.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo")
@Data
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gr_id")
    private Long id;
    @Column(name = "gr_razon_social", length = 50)
    private String razonSocial;

    @Column(name = "gr_identificacion", length = 40)
    private String identificacion;

    @Column(name = "gr_estado", length = 20)
    private String estado;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Empresa> empresas;

    @OneToMany(mappedBy = "grupo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "grupo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pertenencia> pertenencias;

    @OneToMany(mappedBy = "grupo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PermisoTemporal> permisosTemporales;
}
