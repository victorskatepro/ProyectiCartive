package com.jeferson.proyecticartive.activities.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeferson.proyecticartive.R;

/**
 * Created by JARVIS on 13/11/2017.
 */

public class CompletadoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completado, container, false);
        return view;
    }
}
