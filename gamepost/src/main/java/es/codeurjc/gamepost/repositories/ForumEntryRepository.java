package es.codeurjc.gamepost.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.ForumEntry;
import es.codeurjc.gamepost.objects.Game;

public interface ForumEntryRepository extends JpaRepository<ForumEntry, Integer> {
    public List<ForumEntry> findTop20ByOrderByLastUpdatedOnDesc();
    public List<ForumEntry> findByGame(Game game);
}
