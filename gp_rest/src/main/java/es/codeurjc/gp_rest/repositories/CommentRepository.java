package es.codeurjc.gp_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gp_rest.objects.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
}
