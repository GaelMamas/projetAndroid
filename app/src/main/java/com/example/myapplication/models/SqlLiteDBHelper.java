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
        DB.execSQL("create table produit(id text, nom text, prix float, quantite integer)");
        DB.execSQL("create table client(id text, num integer , code integer , role text)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop table if exists produit");
    }
    public boolean insertProduit(Produit produit) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", produit.getId());
        contentValues.put("nom", produit.getNom());
        contentValues.put("prix", produit.getPrix());
        contentValues.put("quantite", produit.getQuantite());

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
        contentValues.put("num",user.getNum());
        contentValues.put("code",user.getCode());
            contentValues.put("role",user.getRole());

            long result = DB.insert("client",null,contentValues);
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
        contentValues.put("quantite", produit.getQuantite());
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
        contentValues.put("num", user.getNum());
        contentValues.put("num", user.getCode());

        Cursor cursor = DB.rawQuery("select * from user where id=?", new String[] {user.getId()});
        if (cursor.getCount() > 0) {
            long result = DB.update("client", contentValues, "id=?", new String[] {user.getId()});
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
            long result = DB.delete("client", "id=?", new String[]{id});
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
            Cursor cursor = DB.rawQuery("select * from produit where name=?", null);
            //return (android.database.Cursor) Cursor;
            Produit produit = new Produit();
            //

            return produit;
        }

        public List<Produit> findAllProduits() {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("select * from produit", null);
            //return (android.database.Cursor) Cursor;

            List<Produit> lesProduits =  new ArrayList<>();

            while (cursor.moveToNext()) {
                Produit produit = new Produit();
                produit.setId(cursor.getString(0));
                produit.setNom(cursor.getString(1));
                produit.setPrix(cursor.getDouble(2));
                produit.setQuantite(cursor.getInt(3));
                lesProduits.add(produit);
            }
            //

         return lesProduits;
        }
    public List<User> findAllUser() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from user", null);
        //return (android.database.Cursor) Cursor;

        List<User> lesusers =  new ArrayList<>();

        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getString(0));
            user.setNum(cursor.getInt(1));
            user.setCode(cursor.getInt(2));
            user.setRole(cursor.getString(3));
            lesusers.add(user);
        }
        //

        return lesusers;
    }



}
