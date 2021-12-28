package com.example.myapplication.ui.vente;

import static com.example.myapplication.R.id.nav_home;

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
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentVenteBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.SqlLiteDBHelper;

public class VenteFragment extends Fragment {
    private VenteViewModel venteViewModel;
    private FragmentVenteBinding binding;
    private DBHelper database;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        venteViewModel =
                new ViewModelProvider(this).get(VenteViewModel.class);

        binding = FragmentVenteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());


        final TextView textView = binding.textVente;
        EditText champNom = binding.nom;
        EditText champP = binding.prix;
        EditText champQ = binding.quantite;
        TextView somm = binding.somme;
        Button buttoncalcul= binding.calcul;



        venteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


    binding.calcul.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //String nom = champNom.getText().toString();
            int prix= Integer.parseInt(champP.getText().toString());
            int quantite= Integer.parseInt(champQ.getText().toString());
            int somme = Integer.parseInt(somm.getText().toString());
            int s = prix +quantite ;




            CharSequence test = "vente";
            Toast.makeText(getContext(),test, Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(VenteFragment.this).navigate(nav_home);

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