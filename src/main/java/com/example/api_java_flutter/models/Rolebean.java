package com.example.api_java_flutter.models;

import javax.persistence.*;

@Entity
@Table(name = "rolebean")
public class Rolebean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role") private int idRole;
    @Column(name = "role_name") private String roleName;

    public Rolebean() {
    }

    public Rolebean(int idRole, String roleName) {
        super();
        this.idRole = idRole;
        this.roleName = roleName;
    }

    public Rolebean(String roleName) {
        super();
        this.roleName = roleName;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}