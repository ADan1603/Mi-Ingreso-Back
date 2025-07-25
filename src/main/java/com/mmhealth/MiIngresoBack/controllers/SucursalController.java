package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.entities.Sucursal;
import com.mmhealth.MiIngresoBack.services.SucursalService;
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
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursales", description = "Operaciones relacionadas con sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Operation(summary = "Obtener todas las sucursales", description = "Retorna una lista de todas las sucursales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sucursales encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "204", description = "No hay sucursales registradas")
    })
    @GetMapping
    public ResponseEntity<List<Sucursal>> findAll() {
        List<Sucursal> sucursales = sucursalService.findAll();
        return sucursales.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sucursales);
    }

    @Operation(summary = "Obtener sucursales por empresa", description = "Retorna sucursales asociadas a una empresa específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sucursales encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "204", description = "No hay sucursales para esta empresa")
    })
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Sucursal>> findByEmpresaId(
            @Parameter(description = "ID de la empresa", example = "1", required = true)
            @PathVariable Long empresaId) {
        List<Sucursal> sucursales = sucursalService.findByEmpresaId(empresaId);
        return sucursales.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sucursales);
    }

    @Operation(summary = "Obtener una sucursal por ID", description = "Retorna una sucursal específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> findById(
            @Parameter(description = "ID de la sucursal", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(sucursalService.findById(id));
    }

    @Operation(summary = "Crear una nueva sucursal", description = "Registra una nueva sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Sucursal> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la sucursal a crear", required = true,
                    content = @Content(schema = @Schema(implementation = Sucursal.class)))
            @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.save(sucursal));
    }

    @Operation(summary = "Actualizar una sucursal", description = "Actualiza los datos de una sucursal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> update(
            @Parameter(description = "ID de la sucursal a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados de la sucursal", required = true,
                    content = @Content(schema = @Schema(implementation = Sucursal.class)))
            @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.update(id, sucursal));
    }

    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error al eliminar la sucursal")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la sucursal a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}