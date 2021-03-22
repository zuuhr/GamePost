package es.codeurjc.gamepost.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import es.codeurjc.gamepost.objects.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{
    List<Game> findFirst20ByDescriptionName();
}
