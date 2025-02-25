package com.devsu.msmovimientos.modulos.cuenta;

import com.devsu.msmovimientos.dto.CuentaDTO;

import java.util.List;


public interface CuentaService {
    CuentaDTO crearCuenta(CuentaDTO cuentaDTO);

    CuentaDTO obtenerCuenta(String numeroCuenta);

    List<CuentaDTO> obtenerCuentasPorCliente(String clienteId);

    void eliminarCuenta(String numeroCuenta);
}
