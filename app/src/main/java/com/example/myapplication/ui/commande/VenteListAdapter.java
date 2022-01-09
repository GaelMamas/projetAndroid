package com.example.myapplication.ui.commande;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.Vente;

import java.util.List;

public class VenteListAdapter extends ArrayAdapter<Vente> {

    public VenteListAdapter(Context applicationContext, List<Vente> ventes) {
        super(applicationContext, 0, ventes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Vente vente = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vente_list_item, parent, false);
        }

        // champ 1
        TextView venteId = convertView.findViewById(R.id.vente_id);
        venteId.setText(vente.getId());

        // champ 2
        TextView client = convertView.findViewById(R.id.client);
        venteId.setText(vente.getClient());

        // champ 3
        TextView content = convertView.findViewById(R.id.content);
        venteId.setText(vente.getContent());

        return convertView;
    }

}
