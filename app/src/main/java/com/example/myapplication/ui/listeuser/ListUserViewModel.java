package com.example.myapplication.ui.listeuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListUserViewModel extends ViewModel {

        private MutableLiveData<String> mText;

        public ListUserViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("");
        }

        public LiveData<String> getText() {
            return mText;
        }
}


