package com.example.api_java_flutter.controllers;

import com.example.api_java_flutter.models.*;
import com.example.api_java_flutter.models.daos.Profildao;
import com.example.api_java_flutter.models.daos.Userdao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class ProfilController {

    private final Profildao profildao;
    private final Userdao userdao;

    public ProfilController(Userdao userdao, Profildao profildao) {
        this.userdao = userdao;
        this.profildao = profildao;
    }
    Message message = new Message();
    //http://localhost:8080/profil
    @PostMapping("/profil")
    Object profil(@RequestBody Userbean user) {
        System.out.println("/profil user" + user.getLogin());

        try {
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                message.setMessage("Le nom est vide ou manquant");
                message.setCode(421);
                throw new Exception(message.getMessage());
            }
            Userbean userbean = userdao.findByLogin(user.getLogin());
            List<Profilbean> profils = profildao.findAll();
            Profilbean profil = null;
            for (Profilbean profilbean : profils) {
                if (userbean.getIdUser() == profilbean.getUser().getIdUser()) {
                    profil = profilbean;
                }
            }
            assert profil != null;
            System.out.println(profil.getIdProfil());
            return new Profilbean(profil.getIdProfil(), profil.getName(), profil.getfirstName(), profil.getAge(),
                    new Userbean(profil.getUser().getLogin(), profil.getUser().getPseudo(), profil.getUser().getMail(),
                            new Rolebean(profil.getUser().getRole().getRoleName()),
                            new Genrebean(profil.getUser().getGenrebean().getGenreName())
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    //    http://localhost:8080/rest/upProfil
    @PostMapping("/upProfil")
    Object upProfil(@RequestBody Profilbean profil) {


        try {
            if (profil.getIdProfil() == 0) {
                message.setMessage("Il n'y a pas d'utilisateur");
                message.setCode(421);
                throw new Exception(message.getMessage());
            }

            System.out.println(profil.getName());
            System.out.println(profil.getfirstName());
            System.out.println(profil.getAge());
            System.out.println(profil.getIdProfil());
            Profilbean profilUser = profildao.findByIdProfil(profil.getIdProfil());
            profilUser.setName(profil.getName());
            profilUser.setfirstName(profil.getfirstName());
            profilUser.setAge(profil.getAge());
            profildao.save(profilUser);
            message.setMessage("Profil sauvegardÃ© !");
            message.setCode(200);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
