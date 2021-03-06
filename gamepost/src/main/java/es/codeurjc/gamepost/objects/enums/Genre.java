package es.codeurjc.gamepost.objects.enums;

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
}
