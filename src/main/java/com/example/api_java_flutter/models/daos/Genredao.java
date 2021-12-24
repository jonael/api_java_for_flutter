package com.example.api_java_flutter.models.daos;

import com.example.api_java_flutter.models.Genrebean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Genredao extends JpaRepository<Genrebean, Long> {

    Genrebean findByGenreName(String genreName);
    Genrebean findByIdGenre(int idGenre);
}
