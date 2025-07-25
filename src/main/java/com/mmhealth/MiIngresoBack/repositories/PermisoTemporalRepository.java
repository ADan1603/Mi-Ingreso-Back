package com.mmhealth.MiIngresoBack.repositories;

import com.mmhealth.MiIngresoBack.entities.PermisoTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoTemporalRepository extends JpaRepository<PermisoTemporal, Long> {
}
