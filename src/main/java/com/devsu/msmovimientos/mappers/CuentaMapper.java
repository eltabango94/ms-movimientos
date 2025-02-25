package com.devsu.msmovimientos.mappers;

import com.devsu.msmovimientos.dto.CuentaDTO;
import com.devsu.msmovimientos.entidades.Cuenta;
import com.devsu.msmovimientos.enums.TipoCuenta;
import org.springframework.stereotype.Component;


@Component
public class CuentaMapper {

    public Cuenta toEntity(CuentaDTO dto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(TipoCuenta.valueOf(dto.getTipoCuenta()));
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        cuenta.setEstado(dto.getEstado());
        cuenta.setClienteId(dto.getClienteId());
        return cuenta;
    }

    public CuentaDTO toDTO(Cuenta cuenta) {
        CuentaDTO dto = new CuentaDTO();
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(String.valueOf(cuenta.getTipoCuenta()));
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado());
        dto.setClienteId(cuenta.getClienteId());
        return dto;
    }
}