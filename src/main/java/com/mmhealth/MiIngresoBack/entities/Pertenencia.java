package com.mmhealth.MiIngresoBack.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pertenencia")
@Data
public class Pertenencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "per_gr_id", referencedColumnName = "gr_id", nullable = false)
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "per_emp_id", referencedColumnName = "emp_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "per_suc_id", referencedColumnName = "suc_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "per_ar_id", referencedColumnName = "ar_id")
    private Area area;

    @OneToMany(mappedBy = "pertenencia")
    private List<Colaborador> colaboradores;
}
