package es.codeurjc.gamepost.repositories.enums;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.enums.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    Optional<Publisher> findByText(String text);
}
