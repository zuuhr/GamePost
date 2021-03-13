package es.codeurjc.gamepost.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Description;

public interface DescriptionRepository extends JpaRepository<Description, Integer>{
    
    List<Description> findByName(String name);
}
