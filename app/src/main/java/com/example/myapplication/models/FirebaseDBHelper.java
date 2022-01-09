package com.example.myapplication.models;

import android.content.Context;

import java.util.List;

public class FirebaseDBHelper implements DBHelper {

    public FirebaseDBHelper(Context context) {

    }

    @Override
    public boolean insertProduit(Produit produit) {
        return false;
    }

    @Override
    public boolean insertUser(User user) {
        return false;
    }

    @Override
    public boolean updateProduit(Produit produit) {
        return false;
    }

    @Override
    public boolean deleteProduit(String id) {
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    public  boolean deleteClient(String id){return false;}

    @Override
    public Produit getProduit(String id) {
        return null;
    }

    @Override
    public List<Produit> findAllProduits() {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public List<Vente> findAllVente() {
        return null;
    }

    @Override
    public boolean insertVente(Vente vente) {
        return false;
    }

}
