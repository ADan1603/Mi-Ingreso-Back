package com.mmhealth.MiIngresoBack.services;

import com.mmhealth.MiIngresoBack.entities.Grupo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mmhealth.MiIngresoBack.repositories.GrupoRepository;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    public Grupo findById(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo no encontrado con id: " + id));
    }

    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo update(Long id, Grupo grupoDetails) {
        Grupo grupo = findById(id);
        grupo.setRazonSocial(grupoDetails.getRazonSocial());
        grupo.setIdentificacion(grupoDetails.getIdentificacion());
        grupo.setEstado(grupoDetails.getEstado());
        return grupoRepository.save(grupo);
    }

    public void delete(Long id) {
        Grupo grupo = findById(id);
        grupoRepository.delete(grupo);
    }
}
