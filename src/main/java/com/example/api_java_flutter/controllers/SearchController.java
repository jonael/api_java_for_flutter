package com.example.api_java_flutter.controllers;

import com.example.api_java_flutter.models.Genrebean;
import com.example.api_java_flutter.models.Message;
import com.example.api_java_flutter.models.Profilbean;
import com.example.api_java_flutter.models.Userbean;
import com.example.api_java_flutter.models.daos.Genredao;
import com.example.api_java_flutter.models.daos.Profildao;
import com.example.api_java_flutter.models.daos.Userdao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class SearchController {

    private final Userdao userdao;
    private final Genredao genredao;
    private final Profildao profildao;

    public SearchController(Userdao userdao, Genredao genredao, Profildao profildao) {
        this.userdao = userdao;
        this.genredao = genredao;
        this.profildao = profildao;
    }

    @RequestMapping("/search")
    Object search(@RequestBody Genrebean genrebean){
        System.out.println(genrebean.getGenreName());
        Message message = new Message();
        try{
            if(genrebean.getGenreName().isEmpty()){
                message.setMessage("Veuillez renseigner un genre");
                message.setCode(401);
                throw new Exception(message.getMessage());
            }
            Genrebean genreBDD = genredao.findByGenreName(genrebean.getGenreName());

            if(genreBDD == null){
                message.setMessage("Genre pas trouve");
                message.setCode(402);
                throw new Exception(message.getMessage());
            }

            System.out.println(genreBDD.getIdGenre());

            List<Userbean> userList = userdao.findByGenrebean(genreBDD);
            List<Profilbean> profils = new ArrayList<>();


            if(userList.isEmpty()){
                message.setMessage("Personne aime ca");
                message.setCode(402);
                throw new Exception(message.getMessage());
            }
            for (int i = 0; i < userList.size() ; i++) {
                profils.add(i, profildao.findByUser(userList.get(i)));
            }

            //profils.remove(0);
            return profils;
        }catch(Exception e){
            e.printStackTrace();
            return message;
        }
    }
}
