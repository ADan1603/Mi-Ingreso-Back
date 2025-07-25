package com.mmhealth.MiIngresoBack.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "area")
@Data
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ar_id")
    private Long id;

    @Column(name = "ar_nombre", length = 50)
    private String nombre;

    @Column(name = "ar_departamento", length = 40)
    private String departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ar_suc_id", referencedColumnName = "suc_id")
    private Sucursal sucursal;
}