package com.devsu.msmovimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEstadoCuentaDTO {
    private String nombreCliente;
    private BigDecimal saldoInicial;
    private boolean estado;
    private List<MovimientoDTO> movimientos;
    private BigDecimal saldoDisponible;

}
