package es.codeurjc.gamepost.repositories.enums;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.enums.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer>{
    Optional<Developer> findByText(String text);
}
