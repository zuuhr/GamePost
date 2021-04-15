package es.codeurjc.gamepost.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gamepost.objects.Description;
import es.codeurjc.gamepost.objects.enums.Genre;

public interface DescriptionRepository extends JpaRepository<Description, Integer>{
    
    List<Description> findByNameContainingIgnoreCase(String name);
    default List<Description> findByNameInKeywords(String[] words){
        
        HashMap<Description, Integer> result = new HashMap<Description, Integer>();
        
        //Find coincidences
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

        /* Find coincidences
        for (String word : words) {
            List<Description> tmp = findByNameContainingIgnoreCase(word);
            
            for (Description descr : tmp) {
                if(result_list.contains(descr)){
                    result_list.(descr, result.get(descr) + 1);
                }else{
                    result_treeset.put(descr, 1);
                }
            }
        }

        Sort them by number of coincidences
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
        */

        //Sort them by number of coincidences
        List<Description> result_descr = result.entrySet().stream()
            .sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue()))   //Order by int
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        return result_descr;

        /*
        List<Description> resultList = new LinkedList<>();
        for (Entry entry : list) {
            resultList.add((Description) entry.getKey());
        }

        return resultList;
        */
    }


    List<Description> findByGenre(Genre genre);
    List<Description> findByDeveloper(String developer);
}