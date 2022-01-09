package com.example.myapplication.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.Produit;
import com.example.myapplication.models.SqlLiteDBHelper;
import com.example.myapplication.ui.vente.VenteViewModel;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    private ListView listView;
    Button buttonAfficher;
    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private DBHelper database;
    private VenteViewModel venteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =new ViewModelProvider(this).get(SlideshowViewModel.class);
        venteViewModel =new ViewModelProvider(this).get(VenteViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());
        listView = binding.listview;

        List<Produit> lesProduits = database.findAllProduits();
        List<Produit > produitSelectionner = new ArrayList<>();


        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(),lesProduits);

        listView.setAdapter(customAdapter);

        final TextView textView = binding.textSlideshow;

        buttonAfficher = binding.afficheliste;

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        binding.afficheliste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i =0; i < customAdapter.checkTab.length; i++) {
                    if (customAdapter.checkTab[i]) {
                        Log.i("*****", lesProduits.get(i).getNom());
                        produitSelectionner.add(lesProduits.get(i));
                        //VenteAdapter.produitsSelectionnes.add(lesProduits.get(i));
                        VenteViewModel.produitsSelectionnes.add(lesProduits.get(i));
                    }
                }
               CharSequence test = "";
             Toast.makeText(getContext(),test, Toast.LENGTH_SHORT).show();

              NavHostFragment.findNavController(SlideshowFragment.this).navigate(R.id.nav_vente);
            }
        });
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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