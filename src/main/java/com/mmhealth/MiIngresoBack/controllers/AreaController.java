package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import com.mmhealth.MiIngresoBack.dto.response.ResponseBuilder;
import com.mmhealth.MiIngresoBack.entities.Area;
import com.mmhealth.MiIngresoBack.services.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@Tag(name = "Áreas", description = "Operaciones CRUD para Áreas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Operation(summary = "Obtener todas las áreas")
    @ApiResponse(responseCode = "200", description = "Lista de áreas obtenida")
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<Area>>> findAll() {
        List<Area> areas = areaService.findAll();
        return ResponseBuilder.success("Lista de áreas obtenida", areas);
    }

    @Operation(summary = "Obtener áreas por sucursal")
    @ApiResponse(responseCode = "200", description = "Áreas de la sucursal obtenidas")
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<CustomApiResponse<List<Area>>> findBySucursalId(
            @PathVariable Long sucursalId) {
        List<Area> areas = areaService.findBySucursalId(sucursalId);
        return ResponseBuilder.success("Áreas de la sucursal obtenidas", areas);
    }

    @Operation(summary = "Obtener área por ID")
    @ApiResponse(responseCode = "200", description = "Área encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Area>> findById(@PathVariable Long id) {
        try{
        Area area = areaService.findById(id);
        return ResponseBuilder.success("Área encontrada", area);
        } catch (
        EntityNotFoundException e) {
            return ResponseBuilder.notFound(e.getMessage(), null);
        }
    }

    @Operation(summary = "Crear nueva área")
    @ApiResponse(responseCode = "201", description = "Área creada")
    @PostMapping
    public ResponseEntity<CustomApiResponse<Area>> create(@RequestBody Area area) {
        Area savedArea = areaService.save(area);
        return ResponseBuilder.created("Área creada exitosamente", savedArea);
    }

    @Operation(summary = "Actualizar área existente")
    @ApiResponse(responseCode = "200", description = "Área actualizada")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Area>> update(
            @PathVariable Long id, @RequestBody Area area) {
        Area updatedArea = areaService.update(id, area);
        return ResponseBuilder.success("Área actualizada", updatedArea);
    }

    @Operation(summary = "Eliminar área")
    @ApiResponse(responseCode = "200", description = "Área eliminada")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        areaService.delete(id);
        return ResponseBuilder.success("Área eliminada exitosamente", null);
    }
}