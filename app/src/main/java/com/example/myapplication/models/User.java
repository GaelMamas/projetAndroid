package com.example.myapplication.models;

import java.util.UUID;

public class User {
    private String id;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;
    //private String adresse;
    //private String telephone;

    public User() {
        this.id = UUID.randomUUID().toString();
    }
    public User(String nom, String email, String motDePasse, String role) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return nom + " - "+ email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom(String string) {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }
}
