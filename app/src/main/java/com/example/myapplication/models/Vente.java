package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vente {
    private String id;
    private String client;

    List<VenteItem> venteItems = new ArrayList<>();

    public Vente() {
        this.id = id = UUID.randomUUID().toString();
    }

    public Vente(List<Produit> produitsSelectionnes) {
        for (int i = 0; i < produitsSelectionnes.size(); i++) {
            VenteItem ligne = new VenteItem(produitsSelectionnes.get(i), 1);
            ligne.setVente(this);
            this.venteItems.add(ligne);
        }

        this.id = UUID.randomUUID().toString();
    }



    public double getPrix() {
        double prix = 0;
        for (int i = 0; i < venteItems.size() ; i++) {
            prix += venteItems.get(i).getPrix();
        }
        return prix;
    }

    public List<VenteItem> getVenteItems() {
        return venteItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getContent() {
        String content = "";
        for (int i = 0; i < venteItems.size() ; i++) {
            int quantite = venteItems.get(i).getQuantite();
            String produit = venteItems.get(i).getProduit().getNom();
            content += quantite + " x " + produit + " ";
        }
        return content;
    }
}
