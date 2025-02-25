package com.devsu.msmovimientos;

import com.devsu.msmovimientos.dto.MovimientoDTO;
import com.devsu.msmovimientos.enums.TipoMovimiento;
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

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ReporteControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MovimientoDTO movimientoDTO;

    @BeforeEach
    void setUp() {
        movimientoDTO = new MovimientoDTO();
        movimientoDTO.setFecha(LocalDateTime.now());
        movimientoDTO.setTipoMovimiento(String.valueOf(TipoMovimiento.DEPOSITO));
        movimientoDTO.setValor(5000.0);
        movimientoDTO.setSaldo(2000.0);
        movimientoDTO.setNumeroCuenta("98765432");
    }

    @Test
    void testRegistrarMovimiento() throws Exception {
        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movimientoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoMovimiento").value("DEPOSITO"))
                .andExpect(jsonPath("$.valor").value(5000.0));
    }

    @Test
    void testObtenerMovimientosPorCuenta() throws Exception {
        mockMvc.perform(get("/movimientos/cuenta/98765432"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoMovimiento").value("DEPOSITO"));
    }
}