package com.example.myapplication.ui.listevente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentListventeBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.Produit;
import com.example.myapplication.models.SqlLiteDBHelper;

import java.util.List;

public class ListeVenteFragment extends Fragment {

        private ListView listView;
        private ListeViewModel listeViewModel;
        private FragmentListventeBinding binding;
        private DBHelper database;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            listeViewModel =
                    new ViewModelProvider(this).get(ListeViewModel.class);

            binding = FragmentListventeBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            database = new SqlLiteDBHelper(getContext());
            listView = binding.listview;

            final TextView textView = binding.textListevente;

            Button button = binding.commande;

            List<Produit> lesproduits = database.findAllProduits();

             ListeVenteAdapter listeVenteAdapter = new ListeVenteAdapter(getActivity().getApplicationContext(),lesproduits);

            listView.setAdapter(listeVenteAdapter);


            listeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });

            binding.commande.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence test1 = "";
                    Toast.makeText(getContext(),test1, Toast.LENGTH_SHORT).show();
                    //NavHostFragment.findNavController(com.example.myapplication.ui.home.HomeFragment.this).navigate(nav_gallery);
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
    



