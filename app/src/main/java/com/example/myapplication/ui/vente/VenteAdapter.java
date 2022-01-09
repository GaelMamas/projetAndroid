package com.example.myapplication.ui.vente;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.VenteItem;
import com.example.myapplication.models.Produit;

import java.util.List;

public class VenteAdapter extends ArrayAdapter<VenteItem> {

    TextView prixTotal;

    public VenteAdapter(Context applicationContext, List<VenteItem> lignesCommande, TextView prixTotal) {
        super(applicationContext, 0,lignesCommande);
        //checkTab = new boolean[lignesCommande.size()];
        this.prixTotal = prixTotal;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        VenteItem ligneCommande = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vente_item, parent, false);
        }

        // champ 1
        Produit produit = ligneCommande.getProduit();
        TextView nomProduit = convertView.findViewById(R.id.textView1);
        nomProduit.setText(produit.getNom() + "  " + produit.getPrix());

        // champ 2
       EditText quantite = convertView.findViewById(R.id.quantite);
       quantite.setText(""+ligneCommande.getQuantite());

       // champ 3
        TextView prix = convertView.findViewById(R.id.prix);
        prix.setText(""+ligneCommande.getPrix());

        quantite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    String nouvelleValeur = s.toString();//.substring(start, start+count);
                    ligneCommande.setQuantite(Integer.parseInt(nouvelleValeur));
                    prix.setText("" + ligneCommande.getPrix());

                    //prix total
                    prixTotal.setText("" + ligneCommande.getVente().getPrix());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return convertView;
    }

}
