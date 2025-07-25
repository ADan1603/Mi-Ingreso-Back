package com.mmhealth.MiIngresoBack.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;

    @Column(name = "usu_nombre", length = 50)
    private String nombre;

    @Column(name = "usu_contrasena", length = 40)
    private String contrasena;

    @Column(name = "gr_estado", length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usu_gr_id", referencedColumnName = "gr_id")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "usu_emp_id", referencedColumnName = "emp_id")
    private Empresa empresa;
}
