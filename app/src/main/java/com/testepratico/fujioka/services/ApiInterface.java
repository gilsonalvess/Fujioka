package com.testepratico.fujioka.services;

import com.testepratico.fujioka.model.Personagem;
import com.testepratico.fujioka.model.Planeta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/planetas")
    Call<ArrayList<Planeta>> getPlanetas();

    @GET("/personagens")
    Call<List<Personagem>> getPersonagens();

}
