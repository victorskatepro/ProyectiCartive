package com.jeferson.proyecticartive.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.servicies.ApiService;
import com.jeferson.proyecticartive.activities.servicies.ApiServiceGenerator;
import com.jeferson.proyecticartive.activities.servicies.ResponseMessage;
import com.jeferson.proyecticartive.activities.activities.HomeActivity;
import com.jeferson.proyecticartive.activities.activities.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JARVIS on 7/09/2017.
 */

public class login extends Fragment {
    private TextView txtregistro;
    private EditText edtcorreo,edtcontrasena;
    private Button btningresar;
    private static final String TAG = LoginActivity.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login, container, false);
        txtregistro = (TextView) rootView.findViewById(R.id.txtregistro);
        edtcorreo = (EditText) rootView.findViewById(R.id.correo);
        edtcontrasena = (EditText) rootView.findViewById(R.id.password);
        btningresar = (Button)rootView.findViewById(R.id.btningresar);

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtcorreo.getText().toString();
                String password = edtcontrasena.getText().toString();
                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                Call<ResponseMessage> call = null;

                if(username.isEmpty()){
                    edtcorreo.setError("Se te olvido el email :)");
                    return;
                }else if (password.isEmpty()){
                    edtcontrasena.setError("Oops se te olvido");
                    return;
                }
                call = service.loginUsuario(username, password);
                call.enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        try {

                            int statusCode = response.code();
                            Log.d(TAG, "HTTP status code: " + statusCode);

                            if (response.isSuccessful()) {

                                ResponseMessage responseMessage = response.body();
                                Log.d(TAG, "responseMessage: " + responseMessage);

                                Toast.makeText(getActivity(), responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                                   if(responseMessage.getMessage().equals("Registro completo")){
                                       Intent intent = new Intent(getActivity(), HomeActivity.class);
                                       startActivity(intent);
                                   }else {
                                       new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                               .setTitleText("Oops...")
                                               .setContentText("Usuario o password incorrecto")
                                               .show();
                                   }
                            } else {
                                ResponseMessage responseMessage = response.body();
                                Log.e(TAG, "onError: " + response.errorBody().string());
                               // Toast.makeText(getActivity(), responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        } catch (Throwable t) {
                            try {
                                Log.e(TAG, "onThrowable: " + t.toString(), t);
                                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            } catch (Throwable x) {
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.toString());
                        Toast.makeText(getActivity(), "error en el servicio", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
        return rootView;
    }
}