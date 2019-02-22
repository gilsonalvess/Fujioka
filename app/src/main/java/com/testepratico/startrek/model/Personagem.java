package com.testepratico.startrek.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Personagem implements Serializable {

    private String _id;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("imagem")
    @Expose
    private String imagem;

    @SerializedName("frota")
    @Expose
    private String frota;

    @SerializedName("funcao")
    @Expose

    private String funcao;

    public Personagem() {
    }

    public Personagem(String _id, String nome, String imagem, String frota, String funcao) {
        this._id = _id;
        this.nome = nome;
        this.imagem = imagem;
        this.frota = frota;
        this.funcao = funcao;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getFrota() {
        return frota;
    }

    public void setFrota(String frota) {
        this.frota = frota;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}

