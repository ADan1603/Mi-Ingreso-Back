package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.entities.Sucursal;
import com.mmhealth.MiIngresoBack.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> findAll() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Sucursal>> findByEmpresaId(@PathVariable Long empresaId) {
        return ResponseEntity.ok(sucursalService.findByEmpresaId(empresaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> findById(@PathVariable Long id) {
        return ResponseEntity.ok(sucursalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Sucursal> create(@RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.save(sucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.update(id, sucursal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
