package es.codeurjc.gamepost.repositories;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Description;

public interface DescriptionRepository extends JpaRepository<Description, Integer>{
    
    List<Description> findByNameContainingIgnoreCase(String name);
    default List<Description> findByNameInKeywords(String[] words){
        
        HashMap<Description, Integer> result = new HashMap<Description, Integer>();
        
        for (String word : words) {
            List<Description> tmp = findByNameContainingIgnoreCase(word);
            
            for (Description descr : tmp) {
                if(result.containsKey(descr)){
                    result.replace(descr, result.get(descr) + 1);
                }else{
                    result.put(descr, 1);
                }
            }
        }

        List<Entry<Description, Integer>> list = new LinkedList<>(result.entrySet());
        list.sort(
            (o1, o2) -> {
                if(o1.getValue() == o2.getValue()){
                    return 0;
                }
                else if(o1.getValue() > o2.getValue()){
                    return -1;
                }else{
                    return 1;
                }
            }
        );

        List<Description> resultList = new LinkedList<>();
        for (Entry entry : list) {
            resultList.add((Description) entry.getKey());
        }

        return resultList;
    }
}