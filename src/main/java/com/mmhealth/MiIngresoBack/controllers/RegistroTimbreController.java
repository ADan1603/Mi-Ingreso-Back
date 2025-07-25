package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.dto.register.RegistroTimbreFilterDTO;
import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import com.mmhealth.MiIngresoBack.dto.response.ResponseBuilder;
import com.mmhealth.MiIngresoBack.entities.RegistroTimbre;
import com.mmhealth.MiIngresoBack.services.RegistroTimbreService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.awt.print.Pageable;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/registros-timbre")
@Tag(name = "Registros de Timbre", description = "Gestión de registros de timbre (entradas/salidas)")
public class RegistroTimbreController {

    @Autowired
    private RegistroTimbreService registroTimbreService;

    @Operation(
            summary = "Crear registro de timbre",
            description = "Crea un registro de timbre. Si el tipo es VISITA, se requiere una cédula válida con permisos APROBADOS."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Registro creado exitosamente",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Colaborador no encontrado o sin permisos APROBADOS"
    )
    @PostMapping
    public ResponseEntity<CustomApiResponse<RegistroTimbre>> save(
            @RequestBody RegistroTimbre registroTimbre,
            @Parameter(description = "Cédula del colaborador (requerida si tipoTimbre=VISITA)", example = "0951234567")
            @RequestParam(required = false) String cedula) {

        RegistroTimbre savedRegistro = registroTimbreService.save(registroTimbre, cedula);
        return ResponseBuilder.success("Registro de timbre creado", savedRegistro);
    }

    @Operation(summary = "Obtener registro por ID")
    @ApiResponse(
            responseCode = "200",
            description = "Registro encontrado",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<RegistroTimbre>> findById(
            @Parameter(description = "ID del registro", example = "1") @PathVariable Long id) {
        RegistroTimbre registro = registroTimbreService.findById(id);
        return ResponseBuilder.success("Registro encontrado", registro);
    }
    @Operation(summary = "Listar registros de timbre con filtros",
            description = "Filtra registros por tipo, entidades asociadas y rango de fechas")
    @ApiResponse(responseCode = "200",
            description = "Registros encontrados",
            content = @Content(schema = @Schema(implementation = CustomApiResponse.class)))
    @GetMapping("/filtrar")
    public ResponseEntity<CustomApiResponse<Page<RegistroTimbre>>> findByFilters(
            @Parameter(description = "Tipo de timbre (VISITA, etc)") @RequestParam(required = false) String tipoTimbre,
            @Parameter(description = "ID del grupo") @RequestParam(required = false) Long grupoId,
            @Parameter(description = "ID de la empresa") @RequestParam(required = false) Long empresaId,
            @Parameter(description = "ID de la sucursal") @RequestParam(required = false) Long sucursalId,
            @Parameter(description = "ID del área") @RequestParam(required = false) Long areaId,
            @Parameter(description = "Fecha inicio (formato: yyyy-MM-dd'T'HH:mm:ss)", example = "2023-01-01T00:00:00")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @Parameter(description = "Fecha fin (formato: yyyy-MM-dd'T'HH:mm:ss)", example = "2023-12-31T23:59:59")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @Parameter(description = "Número de página (0-based)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10") @RequestParam(defaultValue = "10") int size) {

        RegistroTimbreFilterDTO filters = new RegistroTimbreFilterDTO();
        filters.setTipoTimbre(tipoTimbre);
        filters.setGrupoId(grupoId);
        filters.setEmpresaId(empresaId);
        filters.setSucursalId(sucursalId);
        filters.setAreaId(areaId);
        filters.setFechaInicio(fechaInicio);
        filters.setFechaFin(fechaFin);

        Page<RegistroTimbre> registros = (Page<RegistroTimbre>) registroTimbreService.findByFilters(
                filters,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fechaRegistro")));

        return ResponseBuilder.success("Registros encontrados", registros);
    }
}
