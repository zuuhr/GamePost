package es.codeurjc.gamepost.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.ForumEntry;

public interface ForumEntryRepository extends JpaRepository<ForumEntry, Integer> {
    public List<ForumEntry> findAll(Sort sort);
}
