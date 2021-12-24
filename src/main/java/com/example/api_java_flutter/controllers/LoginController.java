package com.example.api_java_flutter.controllers;

import com.example.api_java_flutter.models.Message;
import com.example.api_java_flutter.models.Userbean;
import com.example.api_java_flutter.models.daos.Userdao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class LoginController {

    private final Userdao userDao;

    public LoginController(Userdao userdao) {
        this.userDao = userdao;
    }

    //http://localhost:8080/login
    @PostMapping("/login")
    Object login(@RequestBody Userbean user) {

        Message message = new Message();

        System.out.println("/login user" + user.toString());
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
            List<Userbean> users = userDao.findAllByLogin(user.getLogin());
            if (users.isEmpty()) {
                message.setMessage("Il n'y a pas d'utilisateur s'appelant "+ user.getLogin());
                message.setCode(410);
                throw new Exception(message.getMessage());
            }
            String enteredPass = user.getPassword();
            String hashPass = users.get(0).getPassword();
            if (!BCrypt.checkpw(enteredPass, hashPass)) {
                message.setMessage("le mot de passe ne correspond pas à "+ user.getLogin());
                message.setCode(413);
                throw new Exception (message.getMessage());
            }

//            String name = users.get(0).getName();
//            Rolebean role = new Rolebean(users.get(0).getRole().getIdRole(), users.get(0).getRole().getName());
//            String login = users.get(0).getLogin();
//            int testPass = users.get(0).getTestPass();
//            Userbean userUpdate = users.get(0);
//            userUpdate.setTestPass(0);
//            userDao.save(userUpdate);
            return users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return message;
        }
    }
}
