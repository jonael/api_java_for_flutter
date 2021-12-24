package com.example.api_java_flutter.models;

import javax.persistence.*;

@Entity
@Table(name = "userbean")
public class Userbean {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_user") private int idUser;
    @Column(name= "login") private String login;
    @Column(name= "password") private String password;
    @Column(name= "pseudo") private String pseudo;
    @Column(name= "mail") private String mail;
    @Column(name= "test_pass") private int testPass;
    @ManyToOne() @JoinColumn(name= "id_role") private Rolebean rolebean;
    @ManyToOne() @JoinColumn(name= "id_genre") private Genrebean genrebean;

    public Userbean(String login) {
        this.login = login;
    }


    public Userbean() {
    }

    public Userbean(int idUser, String login, String password, String pseudo, String mail, int testPass, Rolebean rolebean) {
        super();
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.pseudo = pseudo;
        this.mail = mail;
        this.testPass = testPass;
        this.rolebean = rolebean;
    }

    public Userbean(String pseudo, Rolebean rolebean, String login, int testPass) {
        super();
        this.login = login;
        this.pseudo = pseudo;
        this.rolebean = rolebean;
        this.testPass = testPass;
    }

    public Userbean(String login, String passHashed, String pseudo, Rolebean rolebean, String mail) {
        super();
        this.login = login;
        this.password = passHashed;
        this.pseudo = pseudo;
        this.rolebean = rolebean;
        this.mail = mail;
    }

    public Userbean(int id) {
        super();
        this.idUser = id;
    }


    public Userbean(String login, String pseudo, Rolebean rolebean) {
        this.login = login;
        this.pseudo = pseudo;
        this.rolebean = rolebean;
    }

    public Userbean(String login, String passHashed, String pseudo, Rolebean rolebean, String mail, Genrebean genrebean) {
        super();
        this.login = login;
        this.password = passHashed;
        this.pseudo = pseudo;
        this.rolebean = rolebean;
        this.mail = mail;
        this.genrebean = genrebean;
    }

    public Userbean(String login, String pseudo, String mail, int testPass, Rolebean rolebean, Genrebean genrebean) {
        super();
        this.login = login;
        this.pseudo = pseudo;
        this.mail = mail;
        this.testPass = testPass;
        this.rolebean = rolebean;
        this.genrebean = genrebean;
    }

    public Userbean(String login, String pseudo, String mail, int testPass, Rolebean rolebean, Genrebean genrebean, Profilbean profilbean) {
        super();
        this.login = login;
        this.pseudo = pseudo;
        this.mail = mail;
        this.testPass = testPass;
        this.rolebean = rolebean;
        this.genrebean = genrebean;
    }

    public Userbean(String login, String pseudo, String mail, Rolebean rolebean, Genrebean genrebean) {
        super();
        this.login = login;
        this.pseudo = pseudo;
        this.mail = mail;
        this.rolebean = rolebean;
        this.genrebean = genrebean;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String name) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTestPass() {
        return testPass;
    }

    public void setTestPass(int testPass) {
        this.testPass = testPass;
    }

    public Rolebean getRole() {
        return rolebean;
    }

    public Genrebean getGenrebean() {return genrebean;}

    public void setGenrebean(Genrebean genrebean) {
        this.genrebean = genrebean;
    }

    public void setRolebean(Rolebean rolebean) {
        this.rolebean = rolebean;
    }

}