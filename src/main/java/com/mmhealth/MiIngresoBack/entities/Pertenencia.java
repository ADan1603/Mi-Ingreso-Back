package com.mmhealth.MiIngresoBack.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pertenencia")
@Data
public class Pertenencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "per_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_gr_id", referencedColumnName = "gr_id", nullable = false)
    @JsonBackReference
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_emp_id", referencedColumnName = "emp_id")
    @JsonBackReference
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_suc_id", referencedColumnName = "suc_id")
    @JsonBackReference
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_ar_id", referencedColumnName = "ar_id")
    @JsonBackReference
    private Area area;

    @OneToMany(mappedBy = "pertenencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Colaborador> colaboradores;
}
