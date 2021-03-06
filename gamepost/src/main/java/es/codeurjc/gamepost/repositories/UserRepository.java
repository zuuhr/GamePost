package es.codeurjc.gamepost.repositories;

import es.codeurjc.gamepost.objects.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    public Optional<User> findByName(String name);
}