package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.ListElement;

public interface CustomListRepository extends JpaRepository<CustomList<ListElement>, Integer>{
    
}
