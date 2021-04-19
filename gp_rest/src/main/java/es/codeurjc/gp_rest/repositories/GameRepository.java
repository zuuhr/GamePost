package es.codeurjc.gp_rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{
    List<Game> findTop20ByOrderByForumLastUpdatedOnDesc();
}
