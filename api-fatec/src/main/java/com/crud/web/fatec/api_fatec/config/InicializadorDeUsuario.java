package com.crud.web.fatec.api_fatec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.crud.web.fatec.api_fatec.domain.user.UserRepository;
import com.crud.web.fatec.api_fatec.entities.User;

import jakarta.annotation.PostConstruct;

@Component
public class InicializadorDeUsuario {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void criarUsuarioPadrao() {
        if (usuarioRepository.count() == 0) {
            User admin = new User(null, "admin", "admin@email.com", passwordEncoder.encode("1234"));
            usuarioRepository.save(admin);
            System.out.println("Usuário admin criado: nome=admin, senha=1234");
        }
    }
}
