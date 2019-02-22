package com.testepratico.startrek.model;

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

    @SerializedName("imagem")
    @Expose
    private String imagem;

    public Planeta() {
    }

    public Planeta(String nome, String massa, String rotacao, String imagem) {
        this.nome = nome;
        this.massa = massa;
        this.rotacao = rotacao;
        this.imagem = imagem;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
