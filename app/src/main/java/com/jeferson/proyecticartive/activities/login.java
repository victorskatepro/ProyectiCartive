package com.jeferson.proyecticartive.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeferson.proyecticartive.R;

/**
 * Created by JARVIS on 7/09/2017.
 */

public class login extends Fragment {
    private TextView txtregistro;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login, container, false);
        txtregistro = (TextView) rootView.findViewById(R.id.txtregistro);

        txtregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return rootView;
    }

}