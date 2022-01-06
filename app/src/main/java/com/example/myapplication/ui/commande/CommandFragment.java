package com.example.myapplication.ui.commande;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentCommandBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.SqlLiteDBHelper;

public class CommandFragment extends Fragment {

    private CommandViewModel commandViewModel;
    private FragmentCommandBinding binding;
    private DBHelper database;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        commandViewModel = new ViewModelProvider(this).get(CommandViewModel.class);

        binding = FragmentCommandBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());
        listView = binding.listCommande;
        final TextView textView = binding.textCommande;
        commandViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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
