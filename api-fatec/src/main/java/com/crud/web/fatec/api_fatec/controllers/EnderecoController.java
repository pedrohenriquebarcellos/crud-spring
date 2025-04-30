package com.crud.web.fatec.api_fatec.controllers;

import com.crud.web.fatec.api_fatec.infrastructure.viacep.EnderecoDto;
import com.crud.web.fatec.api_fatec.infrastructure.viacep.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public class EnderecoController {

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public EnderecoDto buscarEndereco(@PathVariable String cep) {
        return viaCepService.buscarPorCep(cep);
    }
}
