package com.jeferson.proyecticartive.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.servicies.ApiService;
import com.jeferson.proyecticartive.activities.servicies.ApiServiceGenerator;
import com.jeferson.proyecticartive.activities.servicies.ResponseMessage;
import com.jeferson.proyecticartive.activities.activities.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JARVIS on 7/09/2017.
 */

public class register extends Fragment {
    private EditText correoregister, passwordregister,passwordregister2, nombreregister;
    private Button btnregistrar;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.register, container, false);
        correoregister = (EditText) rootView.findViewById(R.id.edtregistercorreo);
        passwordregister = (EditText) rootView.findViewById(R.id.edtregisterpass);
        passwordregister2 = (EditText) rootView.findViewById(R.id.edtregisterpass2);
        nombreregister = (EditText) rootView.findViewById(R.id.edtregisterusername);
        btnregistrar = (Button) rootView.findViewById(R.id.btnregistrar);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = correoregister.getText().toString();
                String password = passwordregister.getText().toString();
                String password2 = passwordregister2.getText().toString();
                String nombre = nombreregister.getText().toString();
                ApiService service = ApiServiceGenerator.createService(ApiService.class);
                Call<ResponseMessage> call = null;

                if(username.isEmpty()){
                    correoregister.setError("Opps se te olvido ingresar su correo");
                return;
                }
                if(password.isEmpty()){
                    passwordregister.setError("Opps se te olvido ingresar su password");
                return;
                }
                if(nombre.isEmpty()){
                    nombreregister.setError("Oops se te olvido ingresar su nombre");
                return;
                }
                if(!password.equals(password2)) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Ingrese contraseñas iguales")
                            .show();
                return;
                }

                call = service.registerUsuario(username,password,nombre);
                call.enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        try{
                            int statusCode = response.code();
                            Log.d(TAG, "HTTP status code: "+ statusCode);
                            if(response.isSuccessful()){
                                ResponseMessage responseMessage = response.body();
                                Log.d(TAG, "responseMessage" + responseMessage);
                                Toast.makeText(getActivity(),responseMessage.getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                ResponseMessage responseMessage = response.body();
                                Log.e(TAG, "on Error" + response.errorBody().string());

                                Toast.makeText(getActivity(), responseMessage.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Throwable t){
                            try {
                                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }catch (Throwable x){
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        Log.e(TAG, "onFailure" + t.toString());
                        Toast.makeText(getActivity(), "Error en el servicio", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        return rootView;

    }
}