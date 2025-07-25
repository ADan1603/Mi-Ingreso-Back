package com.mmhealth.MiIngresoBack.services;

import com.mmhealth.MiIngresoBack.entities.Area;
import com.mmhealth.MiIngresoBack.repositories.AreaRepository;
import com.mmhealth.MiIngresoBack.entities.Sucursal;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private SucursalService sucursalService;

    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    public List<Area> findBySucursalId(Long sucursalId) {
        return areaRepository.findBySucursalId(sucursalId);
    }

    public Area findById(Long id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área no encontrada con id: " + id));
    }

    public Area save(Area area) {
        if (area.getSucursal() != null && area.getSucursal().getId() != null) {
            Sucursal sucursal = sucursalService.findById(area.getSucursal().getId());
            area.setSucursal(sucursal);
        }
        return areaRepository.save(area);
    }

    public Area update(Long id, Area areaDetails) {
        Area area = findById(id);
        area.setNombre(areaDetails.getNombre());
        area.setDepartamento(areaDetails.getDepartamento());

        if (areaDetails.getSucursal() != null) {
            Sucursal sucursal = sucursalService.findById(areaDetails.getSucursal().getId());
            area.setSucursal(sucursal);
        }

        return areaRepository.save(area);
    }

    public void delete(Long id) {
        Area area = findById(id);
        areaRepository.delete(area);
    }
}
