package com.example.myapplication.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.Produit;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Produit> {



    public CustomAdapter(Context applicationContext, List<Produit> lesProduits) {
        super(applicationContext, 0,lesProduits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.preference_category, parent, false);
        }
        return super.getView(position, convertView, parent);
    }
}
