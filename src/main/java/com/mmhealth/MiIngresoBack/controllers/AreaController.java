package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.entities.Area;
import com.mmhealth.MiIngresoBack.services.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@Tag(name = "Áreas", description = "Operaciones relacionadas con áreas de trabajo")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Operation(summary = "Obtener todas las áreas", description = "Retorna una lista de todas las áreas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de áreas encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Area.class))),
            @ApiResponse(responseCode = "204", description = "No hay áreas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Area>> findAll() {
        List<Area> areas = areaService.findAll();
        return areas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(areas);
    }

    @Operation(summary = "Obtener áreas por sucursal", description = "Retorna áreas asociadas a una sucursal específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de áreas encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Area.class))),
            @ApiResponse(responseCode = "204", description = "No hay áreas para esta sucursal")
    })
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Area>> findBySucursalId(
            @Parameter(description = "ID de la sucursal", example = "1", required = true)
            @PathVariable Long sucursalId) {
        List<Area> areas = areaService.findBySucursalId(sucursalId);
        return areas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(areas);
    }

    @Operation(summary = "Obtener un área por ID", description = "Retorna un área específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Área encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Area.class))),
            @ApiResponse(responseCode = "404", description = "Área no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Area> findById(
            @Parameter(description = "ID del área", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(areaService.findById(id));
    }

    @Operation(summary = "Crear un nuevo área", description = "Registra un nuevo área")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Área creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Area.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Area> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del área a crear", required = true,
                    content = @Content(schema = @Schema(implementation = Area.class)))
            @RequestBody Area area) {
        return ResponseEntity.ok(areaService.save(area));
    }

    @Operation(summary = "Actualizar un área", description = "Actualiza los datos de un área existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Área actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Area.class))),
            @ApiResponse(responseCode = "404", description = "Área no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Area> update(
            @Parameter(description = "ID del área a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del área", required = true,
                    content = @Content(schema = @Schema(implementation = Area.class)))
            @RequestBody Area area) {
        return ResponseEntity.ok(areaService.update(id, area));
    }

    @Operation(summary = "Eliminar un área", description = "Elimina un área del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Área eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Área no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error al eliminar el área")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del área a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        areaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}