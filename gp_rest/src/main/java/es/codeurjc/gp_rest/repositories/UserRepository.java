package es.codeurjc.gp_rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    public Optional<User> findByName(String name);
}