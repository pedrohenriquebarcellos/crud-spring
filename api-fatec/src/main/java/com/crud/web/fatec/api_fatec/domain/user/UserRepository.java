package com.crud.web.fatec.api_fatec.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.web.fatec.api_fatec.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
