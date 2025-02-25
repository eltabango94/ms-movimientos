package com.devsu.msmovimientos.modulos.reporte;

import com.devsu.msmovimientos.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteService {

    private final RestTemplate restTemplate;

    @Value("${mscliente.url}")
    private String msClienteUrl;

    public ClienteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDTO obtenerClientePorId(String clienteId) {
        String url = msClienteUrl + "/" + clienteId;
        ResponseEntity<ClienteDTO> response = restTemplate.getForEntity(url, ClienteDTO.class);
        return response.getBody();
    }
}
