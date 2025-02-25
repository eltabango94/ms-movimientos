package com.devsu.msmovimientos.modulos.cuenta;

import com.devsu.msmovimientos.dto.ClienteDTO;
import com.devsu.msmovimientos.dto.CuentaDTO;
import com.devsu.msmovimientos.entidades.Cuenta;
import com.devsu.msmovimientos.mappers.CuentaMapper;
import com.devsu.msmovimientos.modulos.reporte.ClienteService;
import com.devsu.msmovimientos.utils.RecursoDuplicadoException;
import com.devsu.msmovimientos.utils.RecursoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final ClienteService clienteService;
    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper, ClienteService clienteService) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
        this.clienteService = clienteService;
    }


    @Override
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {

        try {
            Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
            ClienteDTO clienteDTO = clienteService.obtenerClientePorId(cuentaDTO.getClienteId());
            if(clienteDTO == null) {
                throw new RecursoNoEncontradoException("No existe el cliente " + cuentaDTO.getClienteId());
            }
            Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

            return cuentaMapper.toDTO(cuentaGuardada);
        } catch (DataIntegrityViolationException ex) {
            throw new RecursoDuplicadoException("Ya existe una cuenta con el número: " + cuentaDTO.getNumeroCuenta());
        }
    }

    @Override
    public CuentaDTO obtenerCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada con número: " + numeroCuenta));
        return cuentaMapper.toDTO(cuenta);
    }

    @Override
    public List<CuentaDTO> obtenerCuentasPorCliente(String clienteId) {
        return cuentaRepository.findByClienteId(clienteId)
                .stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada con número: " + numeroCuenta));
        cuentaRepository.delete(cuenta);
        log.info("Cuenta eliminada con éxito: {}", numeroCuenta);
    }


}