package es.codeurjc.gp_rest.repositories.enums;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.enums.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    Optional<Publisher> findByText(String text);
}
