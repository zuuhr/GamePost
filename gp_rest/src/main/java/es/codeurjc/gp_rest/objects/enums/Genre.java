package es.codeurjc.gp_rest.objects.enums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String text;

    public Genre(){}
    
    public Genre(String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
