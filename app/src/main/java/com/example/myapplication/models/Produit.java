package com.example.myapplication.models;

import java.util.UUID;

public class Produit {
    private String id;
    private String nom;
    private Double prix;
    private Integer stock;

    public Produit() {
        this.id = id = UUID.randomUUID().toString();
    }
    public Produit(String nom, Double prix, Integer quantite) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.prix = prix;
        this.stock = quantite;
    }
    @Override
    public String toString() {
        return nom + "\t"+ prix + "€";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
