package es.codeurjc.gamepost.objects.enums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publisher{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String text;

    public Publisher(){}
    
    public Publisher(String text){
        this.text = text;
    }
}
