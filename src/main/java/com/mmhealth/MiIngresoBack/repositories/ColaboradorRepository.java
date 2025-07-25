package com.mmhealth.MiIngresoBack.repositories;

import com.mmhealth.MiIngresoBack.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Optional<Colaborador> findByIdentificacion(String identificacion);

    List<Colaborador> findByEstado(String estado);

    Optional<Colaborador> findByEmail(String email);

    List<Colaborador> findByPertenenciaId(Long pertenenciaId);

    Colaborador save(Colaborador colaborador);

}
