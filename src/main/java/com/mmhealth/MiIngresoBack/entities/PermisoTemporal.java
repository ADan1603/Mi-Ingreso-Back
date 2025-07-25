package com.mmhealth.MiIngresoBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_gr_id", referencedColumnName = "gr_id")
    @JsonBackReference
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_emp_id", referencedColumnName = "emp_id")
    @JsonBackReference
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_suc_id", referencedColumnName = "suc_id")
    @JsonBackReference
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_ar_id", referencedColumnName = "ar_id")
    @JsonBackReference
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_col_id", referencedColumnName = "col_id")
    @JsonBackReference
    private Colaborador colaborador;

    @OneToMany(mappedBy = "permisoTemporal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RegistroTimbre> registrosTimbre;
}
