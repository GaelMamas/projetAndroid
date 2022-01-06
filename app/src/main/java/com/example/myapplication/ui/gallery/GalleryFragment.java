package com.example.myapplication.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentGalleryBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.Produit;
import com.example.myapplication.models.SqlLiteDBHelper;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private DBHelper database;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        {
            database = new SqlLiteDBHelper(getContext());
            //database = new FirebaseDBHelper(getContext());
        }


        final TextView textView = binding.textGallery;
        EditText champNom = binding.nom;
        EditText champPrix = binding.prix;
        EditText champQuantite = binding.quantite;
        Button boutonInsert= binding.insert;
        Button boutonUpdate= binding.update;

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = champNom.getText().toString();
                double prix = Double.parseDouble(champPrix.getText().toString());
                int quantite = Integer.parseInt(champQuantite.getText().toString());

                Produit produit = new Produit(nom, prix, quantite);

                boolean checkinserdata = database.insertProduit(produit);
                if (checkinserdata == true){
                    CharSequence test = "Ajout valable ";
                    Toast.makeText(getContext(), test, Toast.LENGTH_SHORT).show();
                    champNom.setText("");
                    champPrix.setText("");
                    champQuantite.setText("");

                } else {
                    CharSequence test = "Ajout ne fonctionne pas";
                    Toast.makeText(getContext(),test,Toast.LENGTH_SHORT).show();
                }
                //CharSequence test = "Ajout avec succes";
                //Toast.makeText(getContext(), test, Toast.LENGTH_SHORT).show();
                //NavHostFragment.findNavController(GalleryFragment.this).navigate(R.id.nav_slideshow);
            }
        });

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = champNom.getText().toString();
                double prix= Double.parseDouble(champPrix.getText().toString());
                int quantite= Integer.parseInt(champQuantite.getText().toString());

                Produit produit = new Produit(nom, prix, quantite);
                boolean checkinserdata = database.updateProduit(produit);
                if (checkinserdata == true){
                    CharSequence test = "Produit Modifier";
                    Toast.makeText(getContext(), test, Toast.LENGTH_SHORT).show();
                } else {
                    CharSequence test = "Modification ne marche pas";
                    Toast.makeText(getContext(),test,Toast.LENGTH_SHORT).show();
                }

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}