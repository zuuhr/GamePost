package es.codeurjc.gamepost.repositories;

import es.codeurjc.gamepost.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
