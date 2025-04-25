package com.crud.web.fatec.api_fatec.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.web.fatec.api_fatec.entities.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Buscar todos os usuários
     * 
     * @return
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Criar Usuário
     * 
     * @param user
     * @return
     */
    public User createUser(User user) {
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        var userCreated = userRepository.save(user);
        return userCreated;
    }

    /**
     * Buscar usuário por ID
     * 
     * @param id
     * @return
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Atualizar usuário
     * 
     * @param id
     * @param newUser
     * @return
     */
    public boolean updateUser(Long id, User newUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(newUser.getName());
        userRepository.save(existingUser);
        return true;
    }

    /**
     * Deletar usuário
     * 
     * @param id
     * @return
     */
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }

        return false;
    }
}
