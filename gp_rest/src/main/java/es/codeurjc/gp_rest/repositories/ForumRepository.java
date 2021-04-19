package es.codeurjc.gp_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.Forum;

public interface ForumRepository extends JpaRepository<Forum, Integer>{
    
}
