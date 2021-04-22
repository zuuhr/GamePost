package es.codeurjc.gp_rest.repositories.enums;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.enums.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
    Optional<Genre> findByText(String text);
}
