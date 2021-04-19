package es.codeurjc.gp_rest.repositories.enums;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.enums.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{
    Optional<Platform> findByText(String text);
}
