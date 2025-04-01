package com.crud.web.fatec.api_fatec.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.web.fatec.api_fatec.entities.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Buscar todos os usuários
     * @return
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Criar Usuário
     * @param user
     * @return
     */
    public User createUser(User user) {
        var userCreated = userRepository.save(user);
        return userCreated;
    }

    /**
     * Buscar usuário por ID
     * @param id
     * @return
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Atualizar usuário
     * @param id
     * @param newUser
     * @return
     */
    public boolean updateUser(Long id, User newUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());

            userRepository.save(user);
            return true;
        }

        return false;
    }

    /**
     * Deletar usuário
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
