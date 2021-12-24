package com.example.api_java_flutter.controllers;

import com.example.api_java_flutter.models.Genrebean;
import com.example.api_java_flutter.models.Message;
import com.example.api_java_flutter.models.Userbean;
import com.example.api_java_flutter.models.daos.Genredao;
import com.example.api_java_flutter.models.daos.Userdao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest")
class GenreController {

    private final Genredao genredao;
    private final Userdao userdao;

    public GenreController(Genredao genredao, Userdao userdao) {
        this.genredao = genredao;
        this.userdao = userdao;
    }


    @GetMapping("/listgenres")
    Object genres(){
        System.out.println("coucou");return genredao.findAll();
    }

    @PostMapping("/change")
    Object update(@RequestBody Userbean user) {
        Message error = new Message();
        try {
            Genrebean genreDBB = genredao.findByGenreName(user.getGenrebean().getGenreName());
            if (genreDBB == null) {
                error.setMessage("Genre pas trouve");
                error.setCode(401);
                throw new Exception(error.getMessage());
            }
            List<Userbean> userDBB = userdao.findAllByLogin(user.getLogin());
            if (userDBB == null) {
                error.setMessage("Utilisateur pas trouve");
                error.setCode(401);
                throw new Exception(error.getMessage());
            }

            userDBB.get(0).setGenrebean(genreDBB);

            //UPDATE TABLE UserBean
            //SET id_genre = "${user.getIdGenre().getIdGenre()}"
            //WHERE login = "${user.getLogin}"
            userdao.save(userDBB.get(0));
            return userDBB;

        } catch (Exception e) {
            e.printStackTrace();
            return error;
        }
    }
}
