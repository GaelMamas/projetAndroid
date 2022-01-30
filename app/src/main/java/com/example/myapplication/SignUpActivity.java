package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivitySignUpBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ClickLogin();


        //SignUp's Button for showing registration page
        binding.signUp.setOnClickListener(view -> ClickSignUp());
    }

    //This is method for doing operation of check login
    private void ClickLogin() {

        binding.login.setOnClickListener(view -> {

            final String goodUserName = "claunux";
            final String goodPwd = "happy";

            boolean userNameCheckResult = false;
            boolean userPwdCheckResult = false;

            if (binding.username.getText().toString().trim().isEmpty()) {

                Snackbar snackbar = Snackbar.make(view, "Veuillez remplir ces champs",
                    Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                snackbar.show();
                binding.username.setError("Username should not be empty");
            } else {
                //Here you can write the codes for checking username
                userNameCheckResult = goodUserName.equals(binding.username.getText().toString());
            }
            if (binding.password.getText().toString().trim().isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                    Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                snackbar.show();
                binding.txtInLayoutPassword.setError("Password should not be empty");
            } else {
                //Here you can write the codes for checking password
                userPwdCheckResult = goodPwd.equals(binding.password.getText().toString());
            }


            //Here you can write the codes if box is not checked
            if (userNameCheckResult) {
                if (userPwdCheckResult) {
                    Main2Activity.start(this);
                    finish();
                } else {
                    binding.txtInLayoutPassword.setError("Wrong Password");
                }
            } else {
                binding.username.setError("Wrong Username");
            }

        });

    }

    //The method for opening the registration page and another processes or checks for registering
    private void ClickSignUp() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_signing_up, null);
        dialog.setView(dialogView);

        final TextInputEditText reg_username = dialogView.findViewById(R.id.reg_username);
        final TextInputEditText reg_password = dialogView.findViewById(R.id.reg_password);
        final TextInputEditText reg_firstName = dialogView.findViewById(R.id.reg_firstName);
        final TextInputEditText reg_lastName = dialogView.findViewById(R.id.reg_lastName);
        final TextInputEditText reg_email = dialogView.findViewById(R.id.reg_email);
        final TextInputEditText reg_confirmemail = dialogView.findViewById(R.id.reg_confirmemail);
        final Button reg_register = dialogView.findViewById(R.id.reg_register);
        final TextInputLayout txtInLayoutRegPassword = dialogView.findViewById(R.id.txtInLayoutRegPassword);

        reg_register.setOnClickListener(view -> {

            boolean goodSignup = false;

            if (reg_username.getText().toString().trim().isEmpty()) {

                reg_username.setError("Please fill out this field");
            } else {
                //Here you can write the codes for checking username
                goodSignup = true;
            }

            if (reg_password.getText().toString().trim().isEmpty()) {
                txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(false);
                reg_password.setError("Please fill out this field");
            } else {
                txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(true);
                //Here you can write the codes for checking password
                goodSignup = true;
            }
            if (reg_firstName.getText().toString().trim().isEmpty()) {

                reg_firstName.setError("Please fill out this field");
            } else {
                //Here you can write the codes for checking firstname
                goodSignup = true;
            }

            if (reg_lastName.getText().toString().trim().isEmpty()) {

                reg_lastName.setError("Please fill out this field");
            }  else {
                //Here you can write the codes for checking lastname
                goodSignup = true;
            }

            if (reg_email.getText().toString().trim().isEmpty()) {

                reg_email.setError("Please fill out this field");
            }  else {
                //Here you can write the codes for checking email
                goodSignup = true;
            }

            if (reg_confirmemail.getText().toString().trim().isEmpty()) {

                reg_confirmemail.setError("Please fill out this field");
            }  else {
                //Here you can write the codes for checking confirmemail
                goodSignup = true;
            }

            if (goodSignup) {
                Main2Activity.start(this);
                finish();
            } else {
                reg_confirmemail.setError("Some fied is empty");
            }

        });
        
        dialog.show();
    }
}
