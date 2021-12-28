package com.example.myapplication.models;

import java.util.List;

public interface DBHelper {
    boolean insertProduit(Produit produit);
    boolean insertUser(User user);

    boolean updateProduit(Produit produit);
   // boolean updateUser(User user);

    boolean deleteProduit(String id);
    boolean deleteUser(String id);


    Produit getProduit(String id);

    List<Produit> findAllProduits();
}
