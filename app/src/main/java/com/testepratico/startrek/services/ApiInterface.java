package com.testepratico.startrek.services;

import com.testepratico.startrek.model.Personagem;
import com.testepratico.startrek.model.Planeta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/planetas")
    Call<ArrayList<Planeta>> getPlanetas();

    @GET("/personagens")
    Call<ArrayList<Personagem>> getPersonagens();

}
