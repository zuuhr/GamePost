package es.codeurjc.gp_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.Content;

public interface ContentRepository extends JpaRepository<Content, Integer>{
    
}
