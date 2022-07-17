package org.omdb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

//@Entity
public class FilmItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    String name;

}
