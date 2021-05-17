package es.codeurjc.gp_rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.Description;
import es.codeurjc.gp_rest.objects.enums.Genre;

public interface DescriptionRepository extends JpaRepository<Description, Integer>{
    List<Description> findByGenreContaining(Genre genre);
    List<Description> findByDeveloper(String developer);
}