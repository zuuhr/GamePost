package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Forum;

public interface ForumRepository extends JpaRepository<Forum, Integer>{
    
}
