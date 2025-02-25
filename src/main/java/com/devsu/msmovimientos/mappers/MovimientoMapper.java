package com.devsu.msmovimientos.mappers;

import com.devsu.msmovimientos.dto.MovimientoDTO;
import com.devsu.msmovimientos.entidades.Movimiento;
import com.devsu.msmovimientos.enums.TipoMovimiento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MovimientoMapper {

    public Movimiento toEntity(MovimientoDTO dto) {
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(dto.getFecha());
        movimiento.setTipoMovimiento(TipoMovimiento.fromString(dto.getTipoMovimiento()));

        movimiento.setValor(dto.getValor());
        movimiento.setSaldo(dto.getSaldo());
        return movimiento;
    }

    public MovimientoDTO toDTO(Movimiento movimiento) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(String.valueOf(movimiento.getTipoMovimiento()));
        dto.setValor(movimiento.getValor());
        dto.setTipoCuenta(String.valueOf(movimiento.getCuenta().getTipoCuenta()));
        dto.setSaldo(movimiento.getSaldo());
        dto.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        dto.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        return dto;
    }
}