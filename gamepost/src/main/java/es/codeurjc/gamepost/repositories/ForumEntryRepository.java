package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.ForumEntry;

public interface ForumEntryRepository extends JpaRepository<ForumEntry, Integer> {
    
}
