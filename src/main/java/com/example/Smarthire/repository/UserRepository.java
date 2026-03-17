package com.example.Smarthire.repository;

import com.example.Smarthire.entity.Aplication;
import com.example.Smarthire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
//In is jparepository is i from manytoone relation maintain here
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // user may or may is not exist it avoid null pointer exception

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);

}
