package com.proyectoi.kinetia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleModel {

    public enum RoleType {
        ADMIN,
        PROVIDER,
        CONSUMER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "id=" + id +
                ", rol=" + roleType +
                '}';
    }
}