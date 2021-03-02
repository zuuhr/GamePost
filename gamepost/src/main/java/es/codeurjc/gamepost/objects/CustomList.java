package es.codeurjc.gamepost.objects;

import java.util.List;

public class CustomList<T> {
    String name;
    List<T> elements;

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