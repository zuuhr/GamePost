package es.codeurjc.gamepost.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.ListElement;
import es.codeurjc.gamepost.objects.User;

public interface CustomListRepository extends JpaRepository<CustomList<ListElement>, Integer>{
    
    List<CustomList<ListElement>> findByUser(User user);

}
