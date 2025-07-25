package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import com.mmhealth.MiIngresoBack.dto.response.ResponseBuilder;
import com.mmhealth.MiIngresoBack.entities.Sucursal;
import com.mmhealth.MiIngresoBack.services.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursales", description = "Operaciones CRUD para Sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Operation(summary = "Obtener todas las sucursales")
    @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida")
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<Sucursal>>> findAll() {
        List<Sucursal> sucursales = sucursalService.findAll();
        return ResponseBuilder.success("Lista de sucursales obtenida", sucursales);
    }

    @Operation(summary = "Obtener sucursales por empresa")
    @ApiResponse(responseCode = "200", description = "Sucursales de la empresa obtenidas")
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<CustomApiResponse<List<Sucursal>>> findByEmpresaId(
            @PathVariable Long empresaId) {
        List<Sucursal> sucursales = sucursalService.findByEmpresaId(empresaId);
        return ResponseBuilder.success("Sucursales de la empresa obtenidas", sucursales);
    }

    @Operation(summary = "Obtener sucursal por ID")
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Sucursal>> findById(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        return ResponseBuilder.success("Sucursal encontrada", sucursal);
    }

    @Operation(summary = "Crear nueva sucursal")
    @ApiResponse(responseCode = "201", description = "Sucursal creada")
    @PostMapping
    public ResponseEntity<CustomApiResponse<Sucursal>> create(@RequestBody Sucursal sucursal) {
        Sucursal savedSucursal = sucursalService.save(sucursal);
        return ResponseBuilder.created("Sucursal creada exitosamente", savedSucursal);
    }

    @Operation(summary = "Actualizar sucursal existente")
    @ApiResponse(responseCode = "200", description = "Sucursal actualizada")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Sucursal>> update(
            @PathVariable Long id, @RequestBody Sucursal sucursal) {
        Sucursal updatedSucursal = sucursalService.update(id, sucursal);
        return ResponseBuilder.success("Sucursal actualizada", updatedSucursal);
    }

    @Operation(summary = "Eliminar sucursal")
    @ApiResponse(responseCode = "200", description = "Sucursal eliminada")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseBuilder.success("Sucursal eliminada exitosamente", null);
    }
}