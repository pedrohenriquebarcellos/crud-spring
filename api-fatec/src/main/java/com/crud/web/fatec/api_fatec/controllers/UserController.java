package com.crud.web.fatec.api_fatec.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> CreateUser(@RequestBody User user) {
        logger.info("Received user: " + user);

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
        }

        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
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
    public ResponseEntity<User> UpdateUser(@PathVariable Long id,@RequestBody User updatedUser) {
        for (User user: users) {
            if (user.getId().equals(id)) {
                if (updatedUser.getName() != null) {
                    user.setName(updatedUser.getName());
                }

                if (updatedUser.getEmail() != null) {
                    user.setEmail(updatedUser.getEmail());
                }
                
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } 

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
}
    