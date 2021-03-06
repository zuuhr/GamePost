package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Content;

public interface ContentRepository extends JpaRepository<Content, Integer>{
    
}
