package com.mmhealth.MiIngresoBack.services;

import com.mmhealth.MiIngresoBack.entities.Empresa;
import com.mmhealth.MiIngresoBack.entities.Sucursal;
import com.mmhealth.MiIngresoBack.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private EmpresaService empresaService;

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public List<Sucursal> findByEmpresaId(Long empresaId) {
        return sucursalRepository.findByEmpresaId(empresaId);
    }

    public Sucursal findById(Long id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + id));
    }

    public Sucursal save(Sucursal sucursal) {
        if (sucursal.getEmpresa() != null && sucursal.getEmpresa().getId() != null) {
            Empresa empresa = empresaService.findById(sucursal.getEmpresa().getId());
            sucursal.setEmpresa(empresa);
        }
        return sucursalRepository.save(sucursal);
    }

    public Sucursal update(Long id, Sucursal sucursalDetails) {
        Sucursal sucursal = findById(id);
        sucursal.setNombre(sucursalDetails.getNombre());
        sucursal.setUbicacion(sucursalDetails.getUbicacion());
        sucursal.setTelefono(sucursalDetails.getTelefono());

        if (sucursalDetails.getEmpresa() != null) {
            Empresa empresa = empresaService.findById(sucursalDetails.getEmpresa().getId());
            sucursal.setEmpresa(empresa);
        }

        return sucursalRepository.save(sucursal);
    }

    public void delete(Long id) {
        Sucursal sucursal = findById(id);
        sucursalRepository.delete(sucursal);
    }
}
