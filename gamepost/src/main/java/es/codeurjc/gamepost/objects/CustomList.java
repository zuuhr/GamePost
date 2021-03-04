package es.codeurjc.gamepost.objects;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class CustomList<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String name;
    @ManyToMany List<T> elements;

    public boolean addElement(T element){
        return elements.add(element);
    }

    public boolean removeElement(T element){
        return elements.remove(element);
    }

    public T getElement(int index){
        return elements.get(index);
    }
}