package es.codeurjc.gamepost.objects;

import java.util.Dictionary;

public class CustomList<T> {
    int id;
    String name;
    Dictionary<Integer, T> elements;

    public boolean addElement(Integer id, T element){
        return element == elements.put(id, element);
    }

    public boolean removeElement(Integer id, T element){
        return element == elements.remove(id);
    }

    public T getElement(int index){
        return elements.get(index);
    }
}