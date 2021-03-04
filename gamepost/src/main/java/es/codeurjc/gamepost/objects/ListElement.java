package es.codeurjc.gamepost.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class ListElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
}
