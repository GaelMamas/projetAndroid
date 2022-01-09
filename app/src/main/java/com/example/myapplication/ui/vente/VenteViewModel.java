package com.example.myapplication.ui.vente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.Produit;

import java.util.ArrayList;
import java.util.List;

public class VenteViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    /*private LiveData<List <Produit >> produitliste = new LiveData<List<Produit>>() {

    }*/
    static public List<Produit> produitsSelectionnes = new ArrayList<>();


    public VenteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Création commande");
    }

    public LiveData<String> getText() {
        return mText;
    }

}