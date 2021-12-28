package com.example.myapplication.ui.commande;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class CommandViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CommandViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bonjour la famille");
    }

    public LiveData<String> getText() {
        return mText;
    }
}