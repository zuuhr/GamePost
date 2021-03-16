package es.codeurjc.gamepost.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    default public List<Comment> sortComments(List<Comment> comments){
        List<Comment> result = new ArrayList<Comment>();

        for (Comment comment : comments) {
            if(!result.contains(comment)){
                Comment parent = comment.getParent();
                
                if(parent == null){
                    result.add(comment);
                    continue;
                }

                //If the parent already is in the result List, insert the child after him.
                if(result.contains(parent)){
                    if(result.size() == result.indexOf(parent) + 1)
                        result.add(comment);
                    else
                        result.add(result.indexOf(parent) + 1, comment);
                }

                //Else, make the correspondant subcalls.
                else
                {
                    List<Comment> subResult = sortComments(new ArrayList<Comment>(Arrays.asList(comment.getParent())));
                    for (Comment comment2 : subResult) {
                        result.add(comment2);
                    }
                    result.add(comment);
                }
            }
        }

        return result;

        //Sort them

        //Return 
    }
}
