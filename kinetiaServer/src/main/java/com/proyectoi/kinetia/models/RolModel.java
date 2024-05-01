package com.proyectoi.kinetia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RolModel {

    public enum RolType {
        ADMIN,
        PROVIDER,
        CONSUMER
    }

    public RolModel() { }

    public RolModel(String rol) {
        this.rolType = RolType.valueOf(rol);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RolType rolType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolType getRolType() {
        return rolType;
    }

    public void setRolType(RolType rolType) {
        this.rolType = rolType;
    }

    @Override
    public String toString() {
        return "RolModel{" +
                "id=" + id +
                ", rol=" + rolType +
                '}';
    }
}