package com.example.myapplication.models;

public class Categorie extends Produit {
    private int idCategorie;
    private String nomCategorie;

    public Categorie(String nom, Double prix, Integer quantite, String nomCategorie) {
        super(nom, prix, quantite);
        this.nomCategorie = nomCategorie;
    }

}
