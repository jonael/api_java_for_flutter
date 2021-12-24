package com.example.api_java_flutter.controllers;

import com.example.api_java_flutter.models.*;
import com.example.api_java_flutter.models.daos.Genredao;
import com.example.api_java_flutter.models.daos.Profildao;
import com.example.api_java_flutter.models.daos.Userdao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class RegisterController {

    private final Userdao userdao;
    private final Profildao profildao;
    private final Genredao genredao;

    public RegisterController(Userdao userdao, Profildao profildao, Genredao genredao) {
        this.userdao = userdao;
        this.profildao = profildao;
        this.genredao = genredao;
    }

    //http://localhost:8080/register
    @PostMapping("/register")
    Object register(@RequestBody Userbean user) {
        Message message = new Message();
        System.out.println("/register user = " + user.getLogin() + user.getPseudo() + user.getMail() + user.getGenrebean().getGenreName());
        try {
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                message.setMessage("Le pseudo est vide ou manquant");
                message.setCode(411);
                throw new Exception(message.getMessage());
            }
            if (user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                message.setMessage("Le password est vide ou manquant");
                message.setCode(412);
                throw new Exception(message.getMessage());
            }
            if (user.getGenrebean().getGenreName().isEmpty() || user.getGenrebean().getGenreName().isBlank()) {
                message.setMessage("Le genre est vide ou manquant");
                message.setCode(416);
                throw new Exception(message.getMessage());
            }
            if (user.getMail().isEmpty() || user.getMail().isBlank()) {
                message.setMessage("Le mail est vide ou manquant");
                message.setCode(415);
                throw new Exception(message.getMessage());
            }
            Userbean userToCreate = userdao.findByLogin(user.getLogin());
            if (!(userToCreate == null)) {
                message.setMessage("Il y a déjà un utilisateur s'appelant " + user.getLogin());
                message.setCode(417);
                throw new Exception(message.getMessage());
            } else {
                String passHashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
                System.out.println(passHashed);
                Rolebean role = new Rolebean();
                role.setIdRole(3);
                Genrebean genrebean = genredao.findByGenreName(user.getGenrebean().getGenreName());
                System.out.println(genrebean.getIdGenre());
                Userbean userToSave = new Userbean(user.getLogin(), passHashed, user.getPseudo() , role, user.getMail(), genrebean);
                userdao.save(userToSave);

                Userbean util = userdao.findByLogin(user.getLogin());
                int id = util.getIdUser();
                System.out.println(id);
                Profilbean profilbean = new Profilbean(new Userbean(id));
                profildao.save(profilbean);
                Profilbean profil = profildao.findByUser(util);
                System.out.println(userToSave.getLogin());
                return new Userbean(
                        userToSave.getLogin(), userToSave.getPseudo(), userToSave.getMail(), userToSave.getTestPass(),
                        new Rolebean(userToSave.getRole().getRoleName()),
                        new Genrebean(userToSave.getGenrebean().getGenreName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return message;
        }
    }
}
