package com.proyectoi.kinetia.models;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RolModel {

    public enum Rol {
        CONSUMER,
        PROVIDER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Rol rol;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}