package com.example.myapplication.ui.commande;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentVenteListBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.SqlLiteDBHelper;
import com.example.myapplication.models.Vente;

import java.util.List;

public class VenteListFragment extends Fragment {

    private VenteListModel commandViewModel;
    private FragmentVenteListBinding binding;
    private DBHelper database;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        commandViewModel = new ViewModelProvider(this).get(VenteListModel.class);

        binding = FragmentVenteListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());
        listView = binding.listview;
        final TextView textView = binding.textCommand;

        List<Vente> lesVentes = database.findAllVente();


        VenteListAdapter venteListAdapter = new VenteListAdapter(getActivity().getApplicationContext(), lesVentes);
        listView.setAdapter(venteListAdapter);

        Button ButtonCommande = binding.commande;


        commandViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        binding.commande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(VenteListFragment.this).navigate(R.id.nav_home );
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
