package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.entities.PermisoTemporal;
import com.mmhealth.MiIngresoBack.services.PermisoTemporalService;
import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import com.mmhealth.MiIngresoBack.dto.response.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permisos-temporales")
@Tag(name = "Permisos Temporales", description = "Gestión de permisos temporales")
public class PermisoTemporalController {

    @Autowired
    private PermisoTemporalService permisoTemporalService;

    @Operation(
            summary = "Obtener un permiso temporal por ID",
            description = "Retorna un permiso temporal basado en su ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Permiso temporal encontrado",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Permiso temporal no encontrado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<PermisoTemporal>> findById(
            @Parameter(description = "ID del permiso temporal", example = "1") @PathVariable Long id
    ) {
        PermisoTemporal permisoTemporal = permisoTemporalService.findById(id);
        return ResponseBuilder.success("Permiso temporal encontrado", permisoTemporal);
    }

    @Operation(
            summary = "Crear un nuevo permiso temporal",
            description = "Registra un nuevo permiso temporal en el sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Permiso temporal creado exitosamente",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class))
    )
    @PostMapping
    public ResponseEntity<CustomApiResponse<PermisoTemporal>> save(
            @RequestBody PermisoTemporal permisoTemporal
    ) {
        PermisoTemporal savedPermiso = permisoTemporalService.save(permisoTemporal);
        return ResponseBuilder.success("Permiso temporal creado exitosamente", savedPermiso);
    }

    @Operation(
            summary = "Eliminar un permiso temporal",
            description = "Elimina un permiso temporal basado en su ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Permiso temporal eliminado exitosamente",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Permiso temporal no encontrado"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<String>> deleteById(
            @Parameter(description = "ID del permiso temporal", example = "1") @PathVariable Long id
    ) {
        permisoTemporalService.deleteById(id);
        return ResponseBuilder.success("Permiso temporal eliminado exitosamente", null);
    }

    @Operation(
            summary = "Actualizar estado de un permiso temporal",
            description = "Actualiza únicamente el estado de un permiso temporal existente"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Estado actualizado correctamente",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Permiso temporal no encontrado"
    )
    @PatchMapping("/{id}/estado")  // Usamos PATCH para actualización parcial
    public ResponseEntity<CustomApiResponse<PermisoTemporal>> updateEstado(
            @Parameter(description = "ID del permiso temporal", example = "1") @PathVariable Long id,
            @Parameter(description = "Nuevo estado del permiso", example = "APROBADO")
            @RequestParam String estado
    ) {
        PermisoTemporal permisoActualizado = permisoTemporalService.updateEstado(id, estado);
        return ResponseBuilder.success("Estado actualizado correctamente", permisoActualizado);
    }
}
