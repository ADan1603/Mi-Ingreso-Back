package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.entities.Area;
import com.mmhealth.MiIngresoBack.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping
    public ResponseEntity<List<Area>> findAll() {
        return ResponseEntity.ok(areaService.findAll());
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Area>> findBySucursalId(@PathVariable Long sucursalId) {
        return ResponseEntity.ok(areaService.findBySucursalId(sucursalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> findById(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Area> create(@RequestBody Area area) {
        return ResponseEntity.ok(areaService.save(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> update(@PathVariable Long id, @RequestBody Area area) {
        return ResponseEntity.ok(areaService.update(id, area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        areaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
