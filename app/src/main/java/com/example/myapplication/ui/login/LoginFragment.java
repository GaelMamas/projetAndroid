package com.example.myapplication.ui.login;

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

import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.models.DBHelper;
import com.example.myapplication.models.SqlLiteDBHelper;
import com.example.myapplication.models.User;


public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    private DBHelper database;


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
       EditText champNum = binding.num;
       EditText champCode = binding.code;
       EditText champRole= binding.role;
       Button boutonLogin = binding.log;
        boutonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int num = Integer.parseInt(champNum.getText().toString().trim());
            int code = Integer.parseInt(champCode.getText().toString().trim());
            String role = champRole.getText().toString().trim();

                User   user = new User(num, code , role);
                 boolean check = database.insertUser(user);
                if (check == true){
                    CharSequence test = "Ajout valable ";
                    Toast.makeText(getContext(), test, Toast.LENGTH_SHORT).show();
                    champNum.setText("");
                    champCode.setText("");

                } else {
                    CharSequence test = "Ajout ne fonctionne pas";
                    Toast.makeText(getContext(),test,Toast.LENGTH_SHORT).show();
                }

               /*if (!champNum.equals("") && !champCode.equals("")){
                    champNum.setError(" champs vide");
                    champCode.setError(" le champs est vide");
                    champCode.requestFocus();
                   champNum.requestFocus();
                    return;
               }*/


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
