package com.devsu.msmovimientos;

import com.devsu.msmovimientos.dto.CuentaDTO;
import com.devsu.msmovimientos.enums.TipoCuenta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CuentaControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private com.devsu.msmovimientos.dto.CuentaDTO cuentaDTO;

    @BeforeEach
    void setUp() {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta("98765432");
        cuentaDTO.setTipoCuenta(String.valueOf(TipoCuenta.AHORROS));
        cuentaDTO.setSaldoInicial(1500.0);
        cuentaDTO.setEstado(true);
        cuentaDTO.setClienteId("CL002");
    }

    @Test
    void testCrearCuenta() throws Exception {
        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuentaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("987654324"))
                .andExpect(jsonPath("$.tipoCuenta").value("AHORROS"));
    }

    @Test
    void testObtenerCuenta() throws Exception {
        testCrearCuenta(); // Primero creamos la cuenta

        mockMvc.perform(get("/cuentas/98765432"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("98765432"));
    }

    @Test
    void testEliminarCuenta() throws Exception {
        testCrearCuenta(); // Primero creamos la cuenta

        mockMvc.perform(delete("/cuentas/98765432"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/cuentas/98765432"))
                .andExpect(status().isNotFound());
    }
}
