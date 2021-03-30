package es.codeurjc.gamepost.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.Comment;

@Service
public class SortCommentsService {
    public List<Comment> sortComments(List<Comment> comments){
        List<Comment> result = new ArrayList<Comment>();

        for (Comment comment : comments) {
            if(!result.contains(comment)){
                Comment parent = comment.getParent();
                
                //Comment raíz
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
    }
}
