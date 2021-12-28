package com.example.myapplication.ui.slideshow;

import static com.example.myapplication.R.id.nav_vente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.Produit;
import com.example.myapplication.models.SqlLiteDBHelper;

import java.util.List;

public class SlideshowFragment extends Fragment {
    private ListView listView;
    Button buttonAfficher;
    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private DBHelper database;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());
        listView = binding.listview;

        List<Produit> lesProduits = database.findAllProduits();

        //Produit[] lespr = lesProduits.toArray(new Produit[] {});

        //String[] menu = new String[] {"lundi","Mardi","Mercredi", "Jeudi", "Vendredi","Mercredi","jeudi","vendredi","samedi","dimanche"};

        ArrayAdapter<Produit> adapter = new CustomAdapter(getActivity().getApplicationContext(), lesProduits);

        listView.setAdapter(adapter);

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
                CharSequence test = "Coucou";
                Toast.makeText(getContext(),test, Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(SlideshowFragment.this).navigate(nav_vente);
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