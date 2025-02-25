package com.devsu.msmovimientos.modulos.movimiento;

import com.devsu.msmovimientos.dto.MovimientoDTO;

import java.time.LocalDateTime;
import java.util.List;


public interface MovimientoService {
    MovimientoDTO registrarMovimiento(MovimientoDTO movimientoDTO);

    List<MovimientoDTO> obtenerMovimientosPorCuenta(String numeroCuenta);

    List<MovimientoDTO> findByCuenta_NumeroCuentaAndFechaBetween(String cuentaId, LocalDateTime inicio, LocalDateTime fin);
}
