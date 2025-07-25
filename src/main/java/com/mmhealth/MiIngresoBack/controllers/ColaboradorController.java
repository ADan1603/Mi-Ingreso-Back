package com.mmhealth.MiIngresoBack.controllers;

import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import com.mmhealth.MiIngresoBack.dto.response.ResponseBuilder;
import com.mmhealth.MiIngresoBack.entities.Colaborador;
import com.mmhealth.MiIngresoBack.services.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

        @Autowired
        private ColaboradorService colaboradorService;

        @Operation(summary = "Crear nuevo colaborador")
        @ApiResponse(responseCode = "201", description = "Colaborador creado")
        @PostMapping
        public ResponseEntity<CustomApiResponse<Colaborador>> create(@RequestBody Colaborador colaborador) {
            Colaborador saved = colaboradorService.crearColaborador(colaborador);
            return ResponseBuilder.created("Colaborador creado exitosamente", saved);
        }

        @Operation(summary = "Obtener colaborador por identificación")
        @ApiResponse(responseCode = "200", description = "Colaborador encontrado")
        @GetMapping("/identificacion/{identificacion}")
        public ResponseEntity<CustomApiResponse<Colaborador>> findByIdentificacion(@PathVariable String identificacion) {
            Optional<Colaborador> colaborador = colaboradorService.obtenerPorIdentificacion(identificacion);
            return colaborador
                    .map(c -> ResponseBuilder.success("Colaborador encontrado", c))
                    .orElseGet(() -> ResponseBuilder.notFound("Colaborador no encontrado",null));
        }

        @Operation(summary = "Obtener colaboradores por estado")
        @ApiResponse(responseCode = "200", description = "Colaboradores encontrados")
        @GetMapping("/estado/{estado}")
        public ResponseEntity<CustomApiResponse<List<Colaborador>>> findByEstado(@PathVariable String estado) {
            List<Colaborador> lista = colaboradorService.obtenerPorEstado(estado);
            return ResponseBuilder.success("Colaboradores encontrados", lista);
        }

        @Operation(summary = "Obtener colaborador por email")
        @ApiResponse(responseCode = "200", description = "Colaborador encontrado")
        @GetMapping("/email/{email}")
        public ResponseEntity<CustomApiResponse<Colaborador>> findByEmail(@PathVariable String email) {
            try{
                Optional<Colaborador> colaborador = colaboradorService.obtenerPorEmail(email);
                return ResponseBuilder.success("Colaboradores encontrados", colaborador.get());

            }catch (EntityNotFoundException e) {
                return ResponseBuilder.notFound(e.getMessage(), null);
            }

        }

        @Operation(summary = "Obtener colaboradores por pertenencia")
        @ApiResponse(responseCode = "200", description = "Colaboradores encontrados")
        @GetMapping("/pertenencia/{pertenenciaId}")
        public ResponseEntity<CustomApiResponse<List<Colaborador>>> findByPertenenciaId(@PathVariable Long pertenenciaId) {
            List<Colaborador> lista = colaboradorService.obtenerPorPertenenciaId(pertenenciaId);
            return ResponseBuilder.success("Colaboradores encontrados", lista);
        }

        @Operation(summary = "Actualizar colaborador")
        @ApiResponse(responseCode = "200", description = "Colaborador actualizado")
        @PutMapping
        public ResponseEntity<CustomApiResponse<Colaborador>> update(@RequestBody Colaborador colaborador) {
            Colaborador actualizado = colaboradorService.actualizarColaborador(colaborador);
            return ResponseBuilder.success("Colaborador actualizado", actualizado);
        }

        @Operation(summary = "Eliminar colaborador por identificación")
        @ApiResponse(responseCode = "200", description = "Colaborador eliminado")
        @DeleteMapping("/identificacion/{identificacion}")
        public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable String identificacion) {
            colaboradorService.eliminarColaborador(identificacion);
            return ResponseBuilder.success("Colaborador eliminado exitosamente", null);
        }
}
