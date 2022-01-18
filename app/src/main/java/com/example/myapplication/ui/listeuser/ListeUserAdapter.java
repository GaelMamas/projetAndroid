package com.example.myapplication.ui.listeuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.User;

import java.util.List;


public class ListeUserAdapter extends ArrayAdapter<User> {

        public ListeUserAdapter(Context applicationContext, List<User> lesClients) {
            super(applicationContext, 0,lesClients);

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            User user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listuser_item, parent, false);
            }

            TextView textView = convertView.findViewById(R.id.text);

            textView.setText(user.getNom() + " *********" + user.getEmail() + " -----"+ user.getRole());

            return convertView;
        }

    }


