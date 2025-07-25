package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.entities.Grupo;
import com.mmhealth.MiIngresoBack.services.GrupoService;
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
@RequestMapping("/api/grupos")
@Tag(name = "Grupos", description = "Operaciones relacionadas con grupos empresariales")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Operation(summary = "Obtener todos los grupos", description = "Retorna una lista de todos los grupos empresariales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de grupos encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grupo.class))),
            @ApiResponse(responseCode = "204", description = "No hay grupos registrados")
    })
    @GetMapping
    public ResponseEntity<List<Grupo>> findAll() {
        List<Grupo> grupos = grupoService.findAll();
        return grupos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(grupos);
    }

    @Operation(summary = "Obtener un grupo por ID", description = "Retorna un grupo específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grupo.class))),
            @ApiResponse(responseCode = "404", description = "Grupo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Grupo> findById(
            @Parameter(description = "ID del grupo a buscar", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(grupoService.findById(id));
    }

    @Operation(summary = "Crear un nuevo grupo", description = "Registra un nuevo grupo empresarial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grupo.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Grupo> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del grupo a crear", required = true,
                    content = @Content(schema = @Schema(implementation = Grupo.class)))
            @RequestBody Grupo grupo) {
        return ResponseEntity.ok(grupoService.save(grupo));
    }

    @Operation(summary = "Actualizar un grupo", description = "Actualiza los datos de un grupo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grupo.class))),
            @ApiResponse(responseCode = "404", description = "Grupo no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Grupo> update(
            @Parameter(description = "ID del grupo a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del grupo", required = true,
                    content = @Content(schema = @Schema(implementation = Grupo.class)))
            @RequestBody Grupo grupo) {
        return ResponseEntity.ok(grupoService.update(id, grupo));
    }

    @Operation(summary = "Eliminar un grupo", description = "Elimina un grupo del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Grupo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Grupo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error al eliminar el grupo")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del grupo a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        grupoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
