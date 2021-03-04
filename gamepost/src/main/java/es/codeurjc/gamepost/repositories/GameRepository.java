package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{
    
}
