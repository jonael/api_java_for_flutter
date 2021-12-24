package com.example.api_java_flutter.models;

import javax.persistence.*;

@Entity
@Table(name = "profilbean")
public class Profilbean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profil") private int idProfil;
    @Column(name = "name") private String name;
    @Column(name = "first_name") private String firstName;
    @Column(name = "age") private int age;
    @OneToOne() @JoinColumn(name = "id_user") private Userbean user;

    public Profilbean(int idProfil, String name, String firstName, int age, Userbean user) {
        super();
        this.idProfil = idProfil;
        this.name = name;
        this.firstName = firstName;
        this.age = age;
        this.user = user;
    }


    public Profilbean(Userbean user) {
        super();
        this.user = user;
    }

    public Profilbean() {}

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Userbean getUser() {
        return user;
    }

}