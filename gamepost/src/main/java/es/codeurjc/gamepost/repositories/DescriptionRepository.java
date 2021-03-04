package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Description;

public interface DescriptionRepository extends JpaRepository<Description, Integer>{
    
}
