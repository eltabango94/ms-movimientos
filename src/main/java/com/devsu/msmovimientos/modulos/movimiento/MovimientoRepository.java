package com.devsu.msmovimientos.modulos.movimiento;

import com.devsu.msmovimientos.entidades.Cuenta;
import com.devsu.msmovimientos.entidades.Movimiento;
import com.devsu.msmovimientos.enums.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuenta_NumeroCuenta(String numeroCuenta);

    @Query("SELECT COUNT(m) > 0 FROM Movimiento m WHERE m.cuenta = :cuenta AND m.tipoMovimiento = :tipoMovimiento " +
            "AND m.valor = :valor AND m.fecha BETWEEN :inicio AND :fin")
    boolean existsByCuentaAndTipoMovimientoAndValorAndFechaBetween(
            @Param("cuenta") Cuenta cuenta,
            @Param("tipoMovimiento") TipoMovimiento tipoMovimiento,
            @Param("valor") Double valor,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetweenOrderByFechaDesc(
            String numeroCuenta, LocalDateTime inicio, LocalDateTime fin);

}