package com.jeferson.proyecticartive.activities.servicies;

import com.jeferson.proyecticartive.activities.models.Asiento;
import com.jeferson.proyecticartive.activities.models.Viaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by JARVIS on 15/10/2017.
 */

public interface ApiService {
    String API_BASE_URL = "https://productos-api-alejovictor.c9users.io";

    @FormUrlEncoded
    @POST("/usuarioslogin")
    Call<ResponseMessage> loginUsuario(@Field("username") String correo,
                                       @Field("password")String password);

    @FormUrlEncoded
    @POST("/usuariosregister")
    Call<ResponseMessage> registerUsuario(@Field("username") String correo,
                                          @Field("password") String password,
                                          @Field("nombre") String nombre);
    @FormUrlEncoded
    @POST("/viajes")
    Call<List<Viaje>> buscarViaje(@Field("destino") String ciudad,
                                  @Field("fecha") String fecha);

    @FormUrlEncoded
    @GET("/asientos/{id}")
    Call<Asiento>showAsiento(@Path("bus_id")Integer id);
}
