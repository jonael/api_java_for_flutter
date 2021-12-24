package com.example.api_java_flutter.models.daos;

import com.example.api_java_flutter.models.Genrebean;
import com.example.api_java_flutter.models.Userbean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Userdao extends JpaRepository<Userbean, Long> {

    List<Userbean> findAllByLogin(String login);
    Userbean findByLogin(String login);
    List<Userbean> findByGenrebean(Genrebean genrebean);
}
