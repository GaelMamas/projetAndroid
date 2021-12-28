package com.example.myapplication.ui.vente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VenteViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public VenteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Vente Produit");
    }

    public LiveData<String> getText() {
        return mText;
    }
}