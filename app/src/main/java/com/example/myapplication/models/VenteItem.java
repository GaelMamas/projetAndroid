package com.example.myapplication.models;

import java.util.UUID;

public class VenteItem {
    private String id;
    private Produit produit;
    private int quantite = 1;
    private Vente vente;

    public VenteItem() {
        this.quantite = 1;
        this.id = UUID.randomUUID().toString();
    }

    public VenteItem(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        this.id = UUID.randomUUID().toString();
    }

    public double getPrix() {
        return quantite*produit.getPrix();
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
