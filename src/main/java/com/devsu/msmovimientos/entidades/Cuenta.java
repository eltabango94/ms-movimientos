package com.devsu.msmovimientos.entidades;

import com.devsu.msmovimientos.enums.TipoCuenta;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;

    @Column(nullable = false)
    private String clienteId;
}