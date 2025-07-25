package com.mmhealth.MiIngresoBack.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "permisotemporal")
public class PermisoTemporal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pt_id")
    private Long id;

    @Column(name = "pt_fechavisita")
    private LocalDateTime fechaVisita;

    @Column(name = "pt_estado", length = 1)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "pt_gr_id", referencedColumnName = "gr_id")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "pt_emp_id", referencedColumnName = "emp_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "pt_suc_id", referencedColumnName = "suc_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "pt_ar_id", referencedColumnName = "ar_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "pt_col_id", referencedColumnName = "col_id")
    private Colaborador colaborador;

    @OneToMany(mappedBy = "permisoTemporal")
    private List<RegistroTimbre> registrosTimbre;
}
