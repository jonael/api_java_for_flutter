package com.example.api_java_flutter.controllers;

import com.example.api_java_flutter.models.Message;
import com.example.api_java_flutter.models.Userbean;
import com.example.api_java_flutter.models.daos.Userdao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")

public class NewPassController {

    private final Userdao userdao;

    public NewPassController(Userdao userdao) {
        this.userdao = userdao;
    }

    //http://localhost:8080/resetpass
    @PostMapping("/newpass")

    Object newPass(@RequestBody Userbean user) {
        Message message = new Message();
        System.out.println("/newpass user = " + user.toString());
        try {
            if (user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                message.setMessage("Le password est vide ou manquant");
                message.setCode(412);
                throw new Exception(message.getMessage());
            }
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                message.setMessage("Le pseudo est vide ou manquant");
                message.setCode(411);
                throw new Exception(message.getMessage());
            }
            // SELECT * FROM userbean WHERE login = "user.getLogin()";
            List<Userbean> users = userdao.findAllByLogin(user.getLogin());
            String passHashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            System.out.println(users.get(0).getPassword());
            users.get(0).setPassword(passHashed);
            users.get(0).setTestPass(0);
            System.out.println(users.get(0).getPassword());
            //UPDATE userbean SET password = "passHashed" WHERE id_user = "users.get(0).getIdUser()";
            userdao.save(users.get(0));
            return users.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return message;
        }
    }
}