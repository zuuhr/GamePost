package es.codeurjc.gamepost.repositories.enums;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.enums.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    
}
