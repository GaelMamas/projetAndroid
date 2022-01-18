package com.example.myapplication.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SqlLiteDBHelper extends SQLiteOpenHelper  implements DBHelper {
    private Object Cursor;

    private static final String DATABASE_NAME = "mabase.db";
    private static final String TABLE_POSTS = "produit";
    private static final String TABLE_USERS = "user";
    private  static final String TABLE_VENTE ="vente";
    private static  final  String TABLE_COMMANDE = "commande";
    private static final int DATABASE_VERSION = 1;

    public SqlLiteDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onConfigure(SQLiteDatabase DB) {
        super.onConfigure(DB);
        DB.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table produit(id text, nom text, prix float, stock integer)");
        DB.execSQL("create table user(id text, nom text ,  email text , motDePasse text, role text)");
        DB.execSQL("create table vente(id text, client_id text)");
        DB.execSQL("create table vente_item(id text, vente_id text, produit_id text, quantite integer)");



    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop table if exists vente_item");
        DB.execSQL("drop table if exists vente");
        DB.execSQL("drop table if exists produit");
        DB.execSQL("drop table if exists user");
    }
    public boolean insertProduit(Produit produit) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", produit.getId());
        contentValues.put("nom", produit.getNom());
        contentValues.put("prix", produit.getPrix());
        contentValues.put("stock", produit.getStock());

        long resultat = DB.insert("produit", null, contentValues);
        if (resultat == -1) {
            return false;
        }
        else {
            return true;
        }

        }


    public  boolean insertUser(User user) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",user.getId());
        contentValues.put("nom",user.getNom());
        contentValues.put("email", user.getEmail());
        contentValues.put("motDePasse", user.getMotDePasse());
        contentValues.put("role", user.getRole());

        long result = DB.insert("user",null,contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }

    }

    public boolean updateProduit(Produit produit) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", produit.getNom());
        contentValues.put("prix", produit.getPrix());
        contentValues.put("quantite", produit.getStock());
        Cursor cursor = DB.rawQuery("select * from produit where id=?", new String[] {produit.getId()});
        if (cursor.getCount() > 0) {
            long resultat = DB.update("produit", contentValues, "id=?", new String[] {produit.getId()});
            if (resultat == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean updateUser(User user) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",user.getId());
        contentValues.put("nom",user.getNom());
        contentValues.put("email", user.getEmail());
        contentValues.put("motDePasse", user.getMotDePasse());
        contentValues.put("role", user.getRole());

        Cursor cursor = DB.rawQuery("select * from user where id=?", new String[] {user.getId()});
        if (cursor.getCount() > 0) {
            long result = DB.update("user", contentValues, "id=?", new String[] {user.getId()});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean deleteProduit(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from produit where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long resultat = DB.delete("produit", "id=?", new String[]{id});
            if (resultat == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean deleteUser(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from user where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("user", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Produit getProduit(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from produit where id=?", new String[]{id});
        //return (android.database.Cursor) Cursor;
        Produit produit = null;
        if (cursor.moveToNext()) {
            produit = getProduit(cursor);
        }
        return produit;
    }

    public List<Produit> findAllProduits() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from produit", null);
        //return (android.database.Cursor) Cursor;

        List<Produit> lesProduits =  new ArrayList<>();

        while (cursor.moveToNext()) {
            Produit produit = getProduit(cursor);
            lesProduits.add(produit);
        }
        //

        return lesProduits;
    }

    public List<User> findAllUsers() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from user", null);
        //return (android.database.Cursor) Cursor;

        List<User> lesusers =  new ArrayList<>();

        while (cursor.moveToNext()) {
            User user = getUser(cursor);
            lesusers.add(user);
        }
        return lesusers;
    }

    @Override
    public boolean insertVente(Vente vente) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", vente.getId());
        contentValues.put("client_id",vente.getClient().getId());

        long result = DB.insert("vente",null,contentValues);
        if (result == -1) {
            return false;
        } else {
            for (int i = 0; i < vente.getVenteItems().size(); i++) {
                VenteItem venteItem = vente.getVenteItems().get(i);
                contentValues = new ContentValues();
                contentValues.put("id", venteItem.getId());
                contentValues.put("vente_id", vente.getId());
                contentValues.put("produit_id", venteItem.getProduit().getId());
                contentValues.put("quantite", venteItem.getQuantite());
                result = DB.insert("vente_item",null,contentValues);

                if (result == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    public List<Vente> findAllVente() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from vente", null);

        List<Vente> lesventes =  new ArrayList<>();

        while (cursor.moveToNext()) {
            Vente vente = getVente(cursor);
            lesventes.add(vente);
        }
        return lesventes;
    }


    Produit getProduit (Cursor cursorProduit) {
        Produit produit = new Produit();
        produit.setId(cursorProduit.getString(0));
        produit.setNom(cursorProduit.getString(1));
        produit.setPrix(cursorProduit.getDouble(2));
        produit.setStock(cursorProduit.getInt(3));

        return produit;
    }

    VenteItem getVenteItem(Cursor cursorVenteItem, Vente vente) {
        VenteItem venteItem = new VenteItem();
        venteItem.setId(cursorVenteItem.getString(0));

        String produitId = cursorVenteItem.getString(2);
        venteItem.setQuantite(cursorVenteItem.getInt(3));

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursorProduit = DB.rawQuery("select * from produit where id=?", new String[] {produitId});
        while (cursorProduit.moveToNext()) {
            Produit produit = getProduit (cursorProduit);
            venteItem.setProduit(produit);
        }

        venteItem.setVente(vente);

        return venteItem;
    }

    User getUser (Cursor cursorUser) {

        User user = new User();
        user.setId(cursorUser.getString(0));
        user.setNom(cursorUser.getString(1));
        user.setEmail(cursorUser.getString(2));
        //user.setMotDePasse(cursorUser.getString(3));
        user.setRole(cursorUser.getString(4));

        return user;
    }

    Vente getVente (Cursor cursorVente) {
        Vente vente = new Vente();
        vente.setId(cursorVente.getString(0));

        SQLiteDatabase DB = this.getWritableDatabase();

        String clientId = cursorVente.getString(1);

        Cursor cursorClient =  DB.rawQuery("select * from user where id=?", new String[] {clientId});
        while (cursorClient.moveToNext()) {
            User client = getUser (cursorClient);
            vente.setClient(client);
        }

        Cursor cursorVenteItem = DB.rawQuery("select * from vente_item where vente_id=?", new String[] {vente.getId()});
        while (cursorVenteItem.moveToNext()) {
            VenteItem venteItem = getVenteItem(cursorVenteItem, vente);

            vente.getVenteItems().add(venteItem);
        }

        return vente;
    }
}
