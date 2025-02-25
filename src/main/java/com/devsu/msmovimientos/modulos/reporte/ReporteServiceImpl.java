package com.devsu.msmovimientos.modulos.reporte;

import com.devsu.msmovimientos.dto.ClienteDTO;
import com.devsu.msmovimientos.dto.CuentaDTO;
import com.devsu.msmovimientos.dto.MovimientoDTO;
import com.devsu.msmovimientos.dto.ReporteEstadoCuentaDTO;
import com.devsu.msmovimientos.modulos.cuenta.CuentaService;
import com.devsu.msmovimientos.modulos.movimiento.MovimientoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;
    private final ClienteService clienteService;

    public ReporteServiceImpl(CuentaService cuentaService, MovimientoService movimientoService, ClienteService clienteService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
        this.clienteService = clienteService;
    }


    @Override
    public List<ReporteEstadoCuentaDTO> generarReporteEstadoCuenta(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<CuentaDTO> cuentas = cuentaService.obtenerCuentasPorCliente(clienteId);
        ClienteDTO clienteDTO = clienteService.obtenerClientePorId(clienteId);
        return cuentas.stream().map(cuenta -> {
            List<MovimientoDTO> movimientos = movimientoService.findByCuenta_NumeroCuentaAndFechaBetween(
                    cuenta.getNumeroCuenta(), fechaInicio, fechaFin);
            BigDecimal saldoDisponible = movimientos.stream()
                    .map(mov -> BigDecimal.valueOf(mov.getValor()))
                    .reduce(BigDecimal.valueOf(cuenta.getSaldoInicial()), BigDecimal::add);
            return new ReporteEstadoCuentaDTO(
                    clienteDTO.getNombre(),
                    BigDecimal.valueOf(cuenta.getSaldoInicial()),
                    cuenta.getEstado(),
                    movimientos,
                    saldoDisponible
            );
        }).collect(Collectors.toList());
    }
}