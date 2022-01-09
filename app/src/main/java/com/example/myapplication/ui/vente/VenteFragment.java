package com.example.myapplication.ui.vente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentVenteBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.SqlLiteDBHelper;
import com.example.myapplication.models.User;
import com.example.myapplication.models.Vente;

import java.util.List;

public class VenteFragment extends Fragment {

    private ListView listView;

    private TextView prixTotal;
    private Spinner client;

    Button buttonAfficher;

    private VenteViewModel venteViewModel;

    private FragmentVenteBinding binding;

    private DBHelper database;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        venteViewModel =new ViewModelProvider(this).get(VenteViewModel.class);

        binding = FragmentVenteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());

        listView = binding.listview;
        prixTotal= binding.prixTotal;
        client = binding.client;

        Vente vente = new Vente(VenteViewModel.produitsSelectionnes);

        List<User> lesUtilisateurs = database.findAllUsers();
        ArrayAdapter<User> clientAdapter = new ArrayAdapter<User>(getContext(), android.R.layout.simple_spinner_dropdown_item, lesUtilisateurs);
        client.setAdapter(clientAdapter);
        client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               vente.setClient(lesUtilisateurs.get(position).toString());
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
        });


        VenteAdapter venteAdapter = new VenteAdapter(getActivity().getApplicationContext(), vente.getVenteItems(), prixTotal);
        listView.setAdapter(venteAdapter);

        final TextView textView = binding.textVente;
        prixTotal.setText(""+vente.getPrix());

        buttonAfficher = binding.calcul;

        venteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        binding.calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.insertVente(vente);
                CharSequence test = "";
                Toast.makeText(getContext(),test, Toast.LENGTH_SHORT).show();
                // NavHostFragmentent.findNavController(SlideshowFragment.this).navigate(nav_vente);
                //NavHostFragment.findNavController(SlideshowFragment.this).navigate(R.id.nav_vente);
                NavHostFragment.findNavController(VenteFragment.this).navigate(R.id.nav_commande);
            }
        });
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        return root;
    }












    /*private VenteViewModel venteViewModel;
    private FragmentVenteBinding binding;
    private DBHelper database;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        venteViewModel = new ViewModelProvider(this).get(VenteViewModel.class);

        binding = FragmentVenteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());
        listView = binding.listview1;

        List<Produit> lesProduits = database.findAllProduits();

        final TextView textView = binding.textVente;
        VenteAdapter venteAdapter = new VenteAdapter(getActivity().getApplicationContext(),lesProduits);

        listView.setAdapter(venteAdapter);

        //Button buttoncalcul= binding.calcul;

        venteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


    binding.calcul.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            CharSequence test = "";
            Toast.makeText(getContext(),test, Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(VenteFragment.this).navigate(nav_home);

        }
    });

        return root;
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}