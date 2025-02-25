package com.devsu.msmovimientos.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoMovimiento {
    DEPOSITO,
    RETIRO,
    TRANSFERENCIA;

    @JsonCreator
    public static TipoMovimiento fromString(String value) {
        for (TipoMovimiento tipo : TipoMovimiento.values()) {
            if (tipo.name().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de movimiento inválido: " + value);
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
