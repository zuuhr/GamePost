package es.codeurjc.gamepost.objects;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//import java.sql.Blob;

public class Game extends ListElement{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String cover;
    @OneToOne(cascade=CascadeType.ALL) Description description;
    @OneToOne(cascade=CascadeType.ALL) Forum forum;

}
