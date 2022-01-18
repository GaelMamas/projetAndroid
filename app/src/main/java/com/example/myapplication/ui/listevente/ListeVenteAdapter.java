package com.example.myapplication.ui.listevente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.Produit;

import java.util.List;


    public class ListeVenteAdapter extends ArrayAdapter<Produit> {

        public ListeVenteAdapter(Context applicationContext, List<Produit> lesproduits) {
            super(applicationContext, 0,lesproduits);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            Produit produit = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listevente_item, parent, false);
            }
            TextView textView = convertView.findViewById(R.id.textView);

            textView.setText(produit.getNom() + "  " + produit.getPrix());

            return convertView;
        }

    }


