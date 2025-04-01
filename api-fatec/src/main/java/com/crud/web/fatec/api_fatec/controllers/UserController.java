package com.crud.web.fatec.api_fatec.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.web.fatec.api_fatec.domain.user.UserService;
import com.crud.web.fatec.api_fatec.entities.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    private final List<User> users = new ArrayList<>();

    /**
     * Criar usuário
     * @param user
     * @return
     */
    @PostMapping("/createUser")
    public ResponseEntity<User> CreateUser(@RequestBody User user) {
        var newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Deletar usuário
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUser/{id}") 
    public ResponseEntity<?> DeleteUser(@PathVariable Long id) {
        boolean deletedUser = userService.deleteUser(id);

        if (deletedUser) {
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        } 

        return new ResponseEntity<>(deletedUser, HttpStatus.NOT_FOUND);
    }

    /**
     * Atualizar usuário
     * @param updatedUser
     * @return
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> UpdateUser(@RequestBody User updatedUser) {
        for (User user: users) {
            if (user.getId().equals(updatedUser.getId())) {
                if (user.getName() != null) {
                    user.setName(updatedUser.getName());
                }

                if (user.getEmail() != null) {
                    user.setEmail(updatedUser.getEmail());
                }
                
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } 

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Obter todos os usuários
     * @return
     */
    @GetMapping("/getAllUsers")
    public List<User> GetAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Obter usuário por ID
     * @param id
     * @return
     */
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> GetUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);

        return user.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + id + " not found"));
    }
}
    