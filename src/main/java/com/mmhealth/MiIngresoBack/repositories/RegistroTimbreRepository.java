package com.mmhealth.MiIngresoBack.repositories;

import com.mmhealth.MiIngresoBack.entities.RegistroTimbre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;

@Repository
public interface RegistroTimbreRepository extends JpaRepository<RegistroTimbre, Long> {
    @Query("SELECT rt FROM RegistroTimbre rt " +
            "JOIN FETCH rt.permisoTemporal pt " +
            "JOIN FETCH pt.grupo g " +
            "JOIN FETCH pt.empresa e " +
            "JOIN FETCH pt.sucursal s " +
            "JOIN FETCH pt.area a " +
            "WHERE (:tipoTimbre IS NULL OR rt.tipoTimbre = :tipoTimbre) " +
            "AND (:grupoId IS NULL OR g.id = :grupoId) " +
            "AND (:empresaId IS NULL OR e.id = :empresaId) " +
            "AND (:sucursalId IS NULL OR s.id = :sucursalId) " +
            "AND (:areaId IS NULL OR a.id = :areaId) " +
            "AND (:fechaInicio IS NULL OR rt.fechaRegistro >= :fechaInicio) " +
            "AND (:fechaFin IS NULL OR rt.fechaRegistro <= :fechaFin)")
    Page<RegistroTimbre> findByFilters(
            @Param("tipoTimbre") String tipoTimbre,
            @Param("grupoId") Long grupoId,
            @Param("empresaId") Long empresaId,
            @Param("sucursalId") Long sucursalId,
            @Param("areaId") Long areaId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            Pageable pageable);
}
