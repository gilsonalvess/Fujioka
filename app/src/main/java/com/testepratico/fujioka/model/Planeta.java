package com.testepratico.fujioka.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Planeta implements Serializable {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("massa")
    @Expose
    private String massa;
    @SerializedName("rotacao")
    @Expose
    private String rotacao;

    public Planeta() {
    }

    public Planeta(String nome, String massa, String rotacao) {
        this.nome = nome;
        this.massa = massa;
        this.rotacao = rotacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMassa() {
        return massa;
    }

    public void setMassa(String massa) {
        this.massa = massa;
    }

    public String getRotacao() {
        return rotacao;
    }

    public void setRotacao(String rotacao) {
        this.rotacao = rotacao;
    }
}
