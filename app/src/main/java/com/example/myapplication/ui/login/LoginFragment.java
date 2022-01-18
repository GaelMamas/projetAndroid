package com.example.myapplication.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.SqlLiteDBHelper;
import com.example.myapplication.models.User;


public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    private DBHelper database;

    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = new SqlLiteDBHelper(getContext());

        final TextView textView = binding.textLogin;

        loginViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });

        EditText champNom = binding.nom;
       EditText champEmail = binding.email;
       EditText champMotDePass = binding.motDePass;
       EditText champRole = binding.role;

        Button boutonLogin = binding.log;
        sharedPreferences = getContext().getSharedPreferences("SP_NAME", Context.MODE_PRIVATE);

        boutonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = champNom.getText().toString().trim();
                String email = champEmail.getText().toString().trim();
                String passWord = champMotDePass.getText().toString().trim();
                String role = champRole.getText().toString().trim();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("",nom);
                editor.putString("", email);
                editor.putString("", role);

                User  user = new User(nom, email, passWord, role);
                 boolean check = database.insertUser(user);
                if (check == true){
                    CharSequence test = "Utilisateur ajouté ";
                    Toast.makeText(getContext(), test, Toast.LENGTH_SHORT).show();
                    champNom.setText("");
                    champEmail.setText("");
                    champMotDePass.setText("");
                    champRole.setText("");
                } else {
                    CharSequence test = "Utilisateur non ajouté";
                    Toast.makeText(getContext(),test,Toast.LENGTH_SHORT).show();
                }


                if (user.getRole().equals("vendeur") || user.getRole().equals("client")) {

                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.nav_home);
                }
                else if(user.getRole().equals("") || user.getRole().equals("")){

                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.nav_home );
                }
                    if(user.getRole().equals("client")){
                        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.nav_vente);
                        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.nav_commande);
                    }
                    else{
                        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.nav_login);

                    }

                 if(user.getRole().equals("client"))
                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.nav_vente);
                else {
                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.nav_login);

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
