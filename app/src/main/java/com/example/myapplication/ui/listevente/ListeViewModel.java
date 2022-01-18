package com.example.myapplication.ui.listevente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class ListeViewModel extends ViewModel {

        private MutableLiveData<String> mText;

        public ListeViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("");
        }

        public LiveData<String> getText() {
            return mText;
        }
    }





