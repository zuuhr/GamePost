package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    
}
