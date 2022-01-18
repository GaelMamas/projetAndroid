package com.example.myapplication.ui.listeuser;

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
import com.example.myapplication.databinding.FragmentListuserBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.SqlLiteDBHelper;
import com.example.myapplication.models.User;

import java.util.List;

public class ListeUserFragment extends Fragment {

        private ListUserViewModel listUserViewModel;
        private ListView listView;
        private FragmentListuserBinding binding;
        private DBHelper database;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            listUserViewModel =
                    new ViewModelProvider(this).get(ListUserViewModel.class);

            binding = FragmentListuserBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            database = new SqlLiteDBHelper(getContext());

            final TextView textView = binding.textListuser;

            listView = binding.listview;

            List<User> lesClients = database.findAllUsers();

            ListeUserAdapter listeUserAdapter = new ListeUserAdapter(getActivity().getApplicationContext(), lesClients);
            listView.setAdapter(listeUserAdapter);

            Button ButtonCommande = binding.commande;
            ButtonCommande.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(ListeUserFragment.this).navigate(R.id.nav_login);
                }
            });
            listUserViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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



