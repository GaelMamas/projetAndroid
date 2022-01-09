package com.example.myapplication.ui.commande;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class VenteListModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VenteListModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}