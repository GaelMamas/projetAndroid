package com.example.myapplication.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.Produit;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Produit> {

    public boolean[] checkTab;

    public CustomAdapter(Context applicationContext, List<Produit> lesProduits) {
        super(applicationContext, 0,lesProduits);
        checkTab = new boolean[lesProduits.size()];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Produit produit = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.produit_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        CheckBox checkBox = convertView.findViewById(R.id.checkprod);

        textView.setText(produit.getNom() + "  " + produit.getPrix());

        checkBox.setSelected(checkTab[position]);
        checkBox.setOnClickListener(view -> {
            checkTab[position] = checkBox.isChecked();
            Toast.makeText(getContext(), "Element", Toast.LENGTH_SHORT).show();

        });

        return convertView;
    }

}
