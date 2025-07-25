package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import com.mmhealth.MiIngresoBack.dto.response.ResponseBuilder;
import com.mmhealth.MiIngresoBack.entities.Grupo;
import com.mmhealth.MiIngresoBack.services.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@Tag(name = "Grupos", description = "Operaciones CRUD para Grupos Empresariales")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Operation(summary = "Obtener todos los grupos")
    @ApiResponse(responseCode = "200", description = "Lista de grupos obtenida",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class)))
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<Grupo>>> findAll() {
        List<Grupo> grupos = grupoService.findAll();
        return ResponseBuilder.success("Lista de grupos obtenida exitosamente", grupos);
    }

    @Operation(summary = "Obtener grupo por ID")
    @ApiResponse(responseCode = "200", description = "Grupo encontrado",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class)))
    @ApiResponse(responseCode = "404", description = "Grupo no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Grupo>> findById(
            @Parameter(description = "ID del grupo", example = "1") @PathVariable Long id) {
        Grupo grupo = grupoService.findById(id);
        return ResponseBuilder.success("Grupo encontrado", grupo);
    }

    @Operation(summary = "Crear nuevo grupo")
    @ApiResponse(responseCode = "201", description = "Grupo creado exitosamente",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class)))
    @PostMapping
    public ResponseEntity<CustomApiResponse<Grupo>> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del grupo", required = true,
                    content = @Content(schema = @Schema(implementation = Grupo.class)))
            @RequestBody Grupo grupo) {
        Grupo savedGrupo = grupoService.save(grupo);
        return ResponseBuilder.created("Grupo creado exitosamente", savedGrupo);
    }

    @Operation(summary = "Actualizar grupo existente")
    @ApiResponse(responseCode = "200", description = "Grupo actualizado",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Grupo>> update(
            @Parameter(description = "ID del grupo") @PathVariable Long id,
            @RequestBody Grupo grupo) {
        Grupo updatedGrupo = grupoService.update(id, grupo);
        return ResponseBuilder.success("Grupo actualizado", updatedGrupo);
    }

    @Operation(summary = "Eliminar grupo")
    @ApiResponse(responseCode = "200", description = "Grupo eliminado",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @Parameter(description = "ID del grupo") @PathVariable Long id) {
        grupoService.delete(id);
        return ResponseBuilder.success("Grupo eliminado exitosamente", null);
    }
}