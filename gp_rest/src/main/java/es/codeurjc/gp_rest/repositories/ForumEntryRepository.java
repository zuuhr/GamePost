package es.codeurjc.gp_rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.ForumEntry;
import es.codeurjc.gp_rest.objects.Game;

public interface ForumEntryRepository extends JpaRepository<ForumEntry, Integer> {
    public List<ForumEntry> findTop20ByOrderByLastUpdatedOnDesc();
    public List<ForumEntry> findByGame(Game game);
}
