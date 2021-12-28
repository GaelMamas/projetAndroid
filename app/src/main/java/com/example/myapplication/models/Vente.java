package com.example.myapplication.models;

public class Vente {
    private  int idVente;
    private int numeroVente;
    private double somme;

    public Vente(int idVente, int numeroVente, double somme) {
        this.idVente = idVente;
        this.numeroVente = numeroVente;
        this.somme = somme;
    }

    public double getSomme() {
        return somme;
    }



}
