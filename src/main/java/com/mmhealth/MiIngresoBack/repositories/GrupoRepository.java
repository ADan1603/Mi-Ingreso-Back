package com.mmhealth.MiIngresoBack.repositories;

import com.mmhealth.MiIngresoBack.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
