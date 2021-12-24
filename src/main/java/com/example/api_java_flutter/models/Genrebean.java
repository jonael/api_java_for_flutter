package com.example.api_java_flutter.models;

import javax.persistence.*;

@Entity
@Table(name = "genrebean")
public class Genrebean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_genre") private int idGenre = 0;
    @Column(name= "genre_name") private String genreName;

    public Genrebean(int idGenre, String genreName) {
        this.idGenre = idGenre;
        this.genreName = genreName;
    }

    public Genrebean(String genreName) {
        this.genreName = genreName;
    }

    public Genrebean() {}

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}