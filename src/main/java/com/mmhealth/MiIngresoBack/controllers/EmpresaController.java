package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import com.mmhealth.MiIngresoBack.dto.response.ResponseBuilder;
import com.mmhealth.MiIngresoBack.entities.Empresa;
import com.mmhealth.MiIngresoBack.services.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@Tag(name = "Empresas", description = "Operaciones CRUD para Empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Obtener todas las empresas")
    @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class)))
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<Empresa>>> findAll() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseBuilder.success("Lista de empresas obtenida", empresas);
    }

    @Operation(summary = "Obtener empresas por grupo")
    @ApiResponse(responseCode = "200", description = "Empresas del grupo obtenidas")
    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<CustomApiResponse<List<Empresa>>> findByGrupoId(
            @Parameter(description = "ID del grupo") @PathVariable Long grupoId) {
        try{
            List<Empresa> empresas = empresaService.findByGrupoId(grupoId);
            return ResponseBuilder.success("Empresas del grupo obtenidas", empresas);
        }
        catch (EntityNotFoundException e) {
            return ResponseBuilder.notFound(e.getMessage(), null);
        }
    }

    @Operation(summary = "Obtener empresa por ID")
    @ApiResponse(responseCode = "200", description = "Empresa encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Empresa>> findById(
            @Parameter(description = "ID de la empresa") @PathVariable Long id) {
        Empresa empresa = empresaService.findById(id);
        return ResponseBuilder.success("Empresa encontrada", empresa);
    }

    @Operation(summary = "Crear nueva empresa")
    @ApiResponse(responseCode = "201", description = "Empresa creada")
    @PostMapping
    public ResponseEntity<CustomApiResponse<Empresa>> create(@RequestBody Empresa empresa) {
        Empresa savedEmpresa = empresaService.save(empresa);
        return ResponseBuilder.created("Empresa creada exitosamente", savedEmpresa);
    }

    @Operation(summary = "Actualizar empresa existente")
    @ApiResponse(responseCode = "200", description = "Empresa actualizada")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Empresa>> update(
            @PathVariable Long id, @RequestBody Empresa empresa) {
        Empresa updatedEmpresa = empresaService.update(id, empresa);
        return ResponseBuilder.success("Empresa actualizada", updatedEmpresa);
    }

    @Operation(summary = "Eliminar empresa")
    @ApiResponse(responseCode = "200", description = "Empresa eliminada")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseBuilder.success("Empresa eliminada exitosamente", null);
    }
}