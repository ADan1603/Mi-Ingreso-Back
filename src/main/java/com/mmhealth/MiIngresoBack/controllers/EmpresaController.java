package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.entities.Empresa;
import com.mmhealth.MiIngresoBack.services.EmpresaService;
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
@RequestMapping("/api/empresas")
@Tag(name = "Empresas", description = "Operaciones relacionadas con empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Obtener todas las empresas", description = "Retorna una lista de todas las empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "204", description = "No hay empresas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Empresa>> findAll() {
        List<Empresa> empresas = empresaService.findAll();
        return empresas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(empresas);
    }

    @Operation(summary = "Obtener empresas por grupo", description = "Retorna empresas asociadas a un grupo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "204", description = "No hay empresas para este grupo")
    })
    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<Empresa>> findByGrupoId(
            @Parameter(description = "ID del grupo", example = "1", required = true)
            @PathVariable Long grupoId) {
        List<Empresa> empresas = empresaService.findByGrupoId(grupoId);
        return empresas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(empresas);
    }

    @Operation(summary = "Obtener una empresa por ID", description = "Retorna una empresa específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> findById(
            @Parameter(description = "ID de la empresa", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(empresaService.findById(id));
    }

    @Operation(summary = "Crear una nueva empresa", description = "Registra una nueva empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Empresa> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la empresa a crear", required = true,
                    content = @Content(schema = @Schema(implementation = Empresa.class)))
            @RequestBody Empresa empresa) {
        return ResponseEntity.ok(empresaService.save(empresa));
    }

    @Operation(summary = "Actualizar una empresa", description = "Actualiza los datos de una empresa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(
            @Parameter(description = "ID de la empresa a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados de la empresa", required = true,
                    content = @Content(schema = @Schema(implementation = Empresa.class)))
            @RequestBody Empresa empresa) {
        return ResponseEntity.ok(empresaService.update(id, empresa));
    }

    @Operation(summary = "Eliminar una empresa", description = "Elimina una empresa del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error al eliminar la empresa")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la empresa a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}