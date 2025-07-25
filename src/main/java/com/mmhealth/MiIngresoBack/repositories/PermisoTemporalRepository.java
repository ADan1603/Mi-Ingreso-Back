package com.mmhealth.MiIngresoBack.repositories;

import com.mmhealth.MiIngresoBack.entities.Colaborador;
import com.mmhealth.MiIngresoBack.entities.PermisoTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoTemporalRepository extends JpaRepository<PermisoTemporal, Long> {
    List<PermisoTemporal> findByColaboradorAndEstado(Colaborador colaborador, String estado);
}
