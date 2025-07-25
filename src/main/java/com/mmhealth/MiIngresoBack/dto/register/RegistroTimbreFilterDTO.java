package com.mmhealth.MiIngresoBack.dto.register;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroTimbreFilterDTO {
    private String tipoTimbre;
    private Long grupoId;
    private Long empresaId;
    private Long sucursalId;
    private Long areaId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
