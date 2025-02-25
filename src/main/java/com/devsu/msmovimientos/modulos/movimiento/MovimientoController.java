package com.devsu.msmovimientos.modulos.movimiento;

import com.devsu.msmovimientos.dto.MovimientoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoDTO> registrarMovimiento(@Valid @RequestBody MovimientoDTO movimientoDTO) {
        return ResponseEntity.ok(movimientoService.registrarMovimiento(movimientoDTO));
    }


    @GetMapping("/cuenta/{numeroCuenta}")
    public ResponseEntity<List<MovimientoDTO>> obtenerMovimientosPorCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(movimientoService.obtenerMovimientosPorCuenta(numeroCuenta));
    }

}
