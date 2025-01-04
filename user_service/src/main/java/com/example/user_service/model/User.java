package com.example.user_service.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String keycloakId;
    private Double balance;

    @ManyToOne
    private Role role;

    public User() {
    }

    public User(String name, String surname, String email, String keycloakId, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keycloakId = keycloakId;
        this.balance = 0.00;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
