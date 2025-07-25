package com.mmhealth.MiIngresoBack.services;

import com.mmhealth.MiIngresoBack.entities.Empresa;
import com.mmhealth.MiIngresoBack.repositories.EmpresaRepository;
import com.mmhealth.MiIngresoBack.entities.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private GrupoService grupoService;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public List<Empresa> findByGrupoId(Long grupoId) {
        return empresaRepository.findByGrupoId(grupoId);
    }

    public Empresa findById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con id: " + id));
    }

    public Empresa save(Empresa empresa) {
        if (empresa.getGrupo() != null && empresa.getGrupo().getId() != null) {
            Grupo grupo = grupoService.findById(empresa.getGrupo().getId());
            empresa.setGrupo(grupo);
        }
        return empresaRepository.save(empresa);
    }

    public Empresa update(Long id, Empresa empresaDetails) {
        Empresa empresa = findById(id);
        empresa.setRazonSocial(empresaDetails.getRazonSocial());
        empresa.setIdentificacion(empresaDetails.getIdentificacion());
        empresa.setEstado(empresaDetails.getEstado());
        empresa.setNombreComercial(empresaDetails.getNombreComercial());
        empresa.setUbicacion(empresaDetails.getUbicacion());
        empresa.setTelefono(empresaDetails.getTelefono());

        if (empresaDetails.getGrupo() != null) {
            Grupo grupo = grupoService.findById(empresaDetails.getGrupo().getId());
            empresa.setGrupo(grupo);
        }

        return empresaRepository.save(empresa);
    }

    public void delete(Long id) {
        Empresa empresa = findById(id);
        empresaRepository.delete(empresa);
    }
}
