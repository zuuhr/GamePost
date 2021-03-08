package es.codeurjc.gamepost.repositories.enums;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.enums.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{
    Optional<Platform> findByText(String text);
}
