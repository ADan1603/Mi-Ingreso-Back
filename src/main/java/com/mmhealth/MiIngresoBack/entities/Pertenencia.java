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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_gr_id", referencedColumnName = "gr_id")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_emp_id", referencedColumnName = "emp_id")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_suc_id", referencedColumnName = "suc_id")
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_ar_id", referencedColumnName = "ar_id")
    private Area area;

    @OneToMany(mappedBy = "pertenencia", cascade = CascadeType.ALL)
    private List<Colaborador> colaboradores = new ArrayList<>();
}
