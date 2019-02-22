package com.testepratico.startrek.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDB extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";

    public static final String TABELA = "personagens";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String IMAGEM = "imagem";
    public static final String FROTA = "frota";
    public static final String FUNCAO = "funcao";
    public static final int VERSAO = 1;

    public CreateDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOME + " TEXT,"
                + IMAGEM + " TEXT,"
                + FROTA + " TEXT,"
                + FUNCAO + " TEXT" + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
