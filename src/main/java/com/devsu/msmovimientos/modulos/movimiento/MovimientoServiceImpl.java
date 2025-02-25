package com.devsu.msmovimientos.modulos.movimiento;

import com.devsu.msmovimientos.dto.MovimientoDTO;
import com.devsu.msmovimientos.entidades.Cuenta;
import com.devsu.msmovimientos.entidades.Movimiento;
import com.devsu.msmovimientos.enums.TipoMovimiento;
import com.devsu.msmovimientos.mappers.MovimientoMapper;
import com.devsu.msmovimientos.modulos.cuenta.CuentaRepository;
import com.devsu.msmovimientos.utils.RecursoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository, MovimientoMapper movimientoMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    public MovimientoDTO registrarMovimiento(MovimientoDTO movimientoDTO) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimientoDTO.getNumeroCuenta())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada"));

        Movimiento movimiento = movimientoMapper.toEntity(movimientoDTO);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());

        if (movimiento.getTipoMovimiento() == TipoMovimiento.RETIRO && cuenta.getSaldoInicial() < movimiento.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente para el retiro");
        }

        cuenta.setSaldoInicial(movimiento.getTipoMovimiento() == TipoMovimiento.DEPOSITO ?
                cuenta.getSaldoInicial() + movimiento.getValor() :
                cuenta.getSaldoInicial() - movimiento.getValor());

        movimiento.setSaldo(cuenta.getSaldoInicial());
        movimiento.setCuenta(cuenta);

        cuentaRepository.save(cuenta);
        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);

        return movimientoMapper.toDTO(movimientoGuardado);
    }

    @Override
    public List<MovimientoDTO> obtenerMovimientosPorCuenta(String numeroCuenta) {
        return movimientoRepository.findByCuenta_NumeroCuenta(numeroCuenta)
                .stream()
                .map(movimientoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoDTO> findByCuenta_NumeroCuentaAndFechaBetween(String cuentaId, LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetweenOrderByFechaDesc(cuentaId, inicio, fin).stream()
                .map(movimientoMapper::toDTO)
                .collect(Collectors.toList());
    }
}