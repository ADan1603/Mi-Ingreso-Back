package com.mmhealth.MiIngresoBack.services;

import com.mmhealth.MiIngresoBack.dto.register.RegistroTimbreFilterDTO;
import com.mmhealth.MiIngresoBack.entities.*;
import com.mmhealth.MiIngresoBack.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroTimbreService {

    @Autowired
    private RegistroTimbreRepository registroTimbreRepository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private PermisoTemporalRepository permisoTemporalRepository;

    @Transactional
    public RegistroTimbre save(RegistroTimbre registroTimbre, String cedula) {
        if ("VISITA".equalsIgnoreCase(registroTimbre.getTipoTimbre())) {
            // Validar que exista un permiso APROBADO para la cédula
            Colaborador colaborador = colaboradorRepository.findByIdentificacion(cedula)
                    .orElseThrow(() -> new EntityNotFoundException("Colaborador no encontrado con cédula: " + cedula));

            List<PermisoTemporal> permisos = permisoTemporalRepository
                    .findByColaboradorAndEstado(colaborador, "APROBADO");

            if (permisos.isEmpty()) {
                throw new EntityNotFoundException("No hay permisos APROBADOS para la cédula: " + cedula);
            }

            // Asignar el primer permiso encontrado (o lógica adicional si es necesario)
            registroTimbre.setPermisoTemporal(permisos.get(0));
            registroTimbre.setColaborador(colaborador);
        }

        registroTimbre.setFechaRegistro(LocalDateTime.now());
        return registroTimbreRepository.save(registroTimbre);
    }

    public RegistroTimbre findById(Long id) {
        return registroTimbreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de timbre no encontrado con id: " + id));
    }

    public Page findByFilters(RegistroTimbreFilterDTO filters, Pageable pageable) {
        return registroTimbreRepository.findByFilters(
                filters.getTipoTimbre(),
                filters.getGrupoId(),
                filters.getEmpresaId(),
                filters.getSucursalId(),
                filters.getAreaId(),
                filters.getFechaInicio(),
                filters.getFechaFin(),
                pageable);
    }
}

