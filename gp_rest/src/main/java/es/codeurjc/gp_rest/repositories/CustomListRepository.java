package es.codeurjc.gp_rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.CustomList;
import es.codeurjc.gp_rest.objects.ListElement;
import es.codeurjc.gp_rest.objects.User;

public interface CustomListRepository extends JpaRepository<CustomList<ListElement>, Integer>{
    
    List<CustomList<ListElement>> findByUser(User user);

}
