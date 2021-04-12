package es.codeurjc.gamepost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
}
