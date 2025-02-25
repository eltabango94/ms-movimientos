package com.devsu.msmovimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTO {
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private String tipoCuenta;
    private String numeroCuenta;
    private Double valor;
    private Double saldo;

}