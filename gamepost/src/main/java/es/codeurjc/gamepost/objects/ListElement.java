package es.codeurjc.gamepost.objects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance( strategy = InheritanceType.JOINED)
public abstract class ListElement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
}
