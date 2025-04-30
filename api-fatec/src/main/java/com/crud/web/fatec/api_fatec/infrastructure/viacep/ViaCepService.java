package com.crud.web.fatec.api_fatec.infrastructure.viacep;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    @Value("${viacepBaseUrl}")
    private String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public EnderecoDto buscarPorCep(String cep) {
        String url = baseUrl + cep + "/json/";
        return restTemplate.getForObject(url, EnderecoDto.class);
    }
}