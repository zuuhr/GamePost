package es.codeurjc.gp_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    
}
