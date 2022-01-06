package com.example.myapplication.ui.vente;

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

public class VenteAdapter extends ArrayAdapter<Produit> {

    //public static List<Produit> produitsSelectionnes = new ArrayList<>();

    //public boolean[] checkTab;

    public VenteAdapter(Context applicationContext, List<Produit> lesProduits) {
        super(applicationContext, 0,lesProduits);
        //checkTab = new boolean[lesProduits.size()];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Produit produit = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vente_item, parent, false);
        }

        // champ 1
        TextView textView = convertView.findViewById(R.id.textView1);
        textView.setText(produit.getNom() + "  " + produit.getPrix());

        // champ 2
       // EditText editText = convertView.findViewById(R.id.editprod);
        /*editText.setSelected(checkTab[position]);
        editText.setOnClickListener(view -> {
            checkTab[position] = editText.isActivated();
        });*/

        return convertView;
    }

}
