package com.testepratico.startrek.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.testepratico.startrek.model.Personagem;

import java.util.ArrayList;

public class PersonagemDAO {

    private SQLiteDatabase db;
    private CreateDB banco;

    public PersonagemDAO(Context context) {
        banco = new CreateDB(context);
    }

    //Insere dados
    public String inserirPersonagem(String nome, String imagem, String funcao, String fronta) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CreateDB.NOME, nome);
        valores.put(CreateDB.IMAGEM, imagem);
        valores.put(CreateDB.FUNCAO, funcao);
        valores.put(CreateDB.FROTA, fronta);

        resultado = db.insert(CreateDB.TABELA, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else
            return "Registro inserido com sucesso";
    }

    //Carrega dados
    public ArrayList<Personagem> carregarPersonagens() {

        db = banco.getWritableDatabase();

        ArrayList<Personagem> listaDePersonagens = new ArrayList<Personagem>();
        String selectQuery = "SELECT * FROM " + CreateDB.TABELA;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Personagem personagem = new Personagem();
                personagem.set_id(String.valueOf(cursor.getInt(0)));
                personagem.setNome(cursor.getString(1));
                personagem.setImagem(cursor.getString(2));
                personagem.setFrota(cursor.getString(3));
                personagem.setFuncao(cursor.getString(4));
                listaDePersonagens.add(personagem);

            } while (cursor.moveToNext());
        }
        db.close();
        return listaDePersonagens;
    }

    //Atualiza dados
    public String atualizaDados(Personagem personagem) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CreateDB.NOME, personagem.getNome());
        valores.put(CreateDB.IMAGEM, personagem.getImagem());
        valores.put(CreateDB.FROTA, personagem.getFrota());
        valores.put(CreateDB.FUNCAO, personagem.getFuncao());

        // atualiza linha
        resultado = db.update(CreateDB.TABELA, valores, CreateDB.ID + " = ?", new String[]{String.valueOf(personagem.get_id())});

        if (resultado == -1) {
            return "Erro ao atualizar cadastro";
        } else
            return "Cadastro atualizado com sucesso";

    }

    public void delete(Personagem personagem) {
        db = banco.getWritableDatabase();
        db.delete(CreateDB.TABELA, CreateDB.ID + " = ?", new String[]{String.valueOf(personagem.get_id())});
        db.close();
    }

}
