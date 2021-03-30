package es.codeurjc.gamepost.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
}
