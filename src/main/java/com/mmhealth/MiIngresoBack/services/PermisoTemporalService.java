package com.mmhealth.MiIngresoBack.services;

import com.mmhealth.MiIngresoBack.entities.PermisoTemporal;
import com.mmhealth.MiIngresoBack.repositories.PermisoTemporalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermisoTemporalService {

    @Autowired
    private PermisoTemporalRepository permisoTemporalRepository;

    public PermisoTemporal findById(Long id) {
        return permisoTemporalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permiso Temporal no encontrado con id: " + id));
    }

    public PermisoTemporal save(PermisoTemporal permisoTemporal) {
        permisoTemporal.setEstado("INGRESADO");
        return permisoTemporalRepository.save(permisoTemporal);
    }

    public void deleteById(Long id) {
        if (!permisoTemporalRepository.existsById(id)) {
            throw new EntityNotFoundException("Permiso Temporal no encontrado con id: " + id);
        }
        permisoTemporalRepository.deleteById(id);
    }
    public PermisoTemporal updateEstado(Long id, String nuevoEstado) {
        PermisoTemporal permiso = permisoTemporalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permiso Temporal no encontrado con id: " + id));

        permiso.setEstado(nuevoEstado);
        return permisoTemporalRepository.save(permiso);
    }
}
