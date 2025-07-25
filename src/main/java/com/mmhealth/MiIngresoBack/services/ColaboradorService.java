package com.mmhealth.MiIngresoBack.services;

import com.mmhealth.MiIngresoBack.entities.Colaborador;
import com.mmhealth.MiIngresoBack.repositories.ColaboradorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

   private  final Logger LOGGER = LoggerFactory.getLogger(ColaboradorService.class);

   @Autowired
   ColaboradorRepository colaboradorRepository;

    public Colaborador crearColaborador(Colaborador colaborador) {
        LOGGER.info("Creando colaborador: {}", colaborador);
        return colaboradorRepository.save(colaborador);
    }

    public Optional<Colaborador> obtenerPorIdentificacion(String identificacion) {
        return colaboradorRepository.findByIdentificacion(identificacion);
    }

    public List<Colaborador> obtenerPorEstado(String estado) {
        return colaboradorRepository.findByEstado(estado);
    }

    public Optional<Colaborador> obtenerPorEmail(String email) {
        return colaboradorRepository.findByEmail(email);
    }

    public List<Colaborador> obtenerPorPertenenciaId(Long pertenenciaId) {
        return colaboradorRepository.findByPertenenciaId(pertenenciaId);
    }

    public Colaborador actualizarColaborador(Colaborador colaborador) {
        LOGGER.info("Actualizando colaborador: {}", colaborador);
        return colaboradorRepository.save(colaborador);
    }

    public void eliminarColaborador(String identificacion) {
        LOGGER.info("Eliminando colaborador con identificacion: {}", identificacion);
        Colaborador colaborador = obtenerPorIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado con identificacion: " + identificacion));
        colaborador.setEstado("INA");
        colaboradorRepository.save(colaborador);
    }
}
