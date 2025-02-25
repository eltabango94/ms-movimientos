package com.devsu.msmovimientos.modulos.reporte;

import com.devsu.msmovimientos.dto.ReporteEstadoCuentaDTO;

import java.time.LocalDateTime;
import java.util.List;


public interface ReporteService {
    List<ReporteEstadoCuentaDTO> generarReporteEstadoCuenta(String clienteId, LocalDateTime fechaInicio,
                                                            LocalDateTime fechaFin);
}
