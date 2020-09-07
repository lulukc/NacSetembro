package com.example.blocoDeNotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.text.SimpleDateFormat;


public class Database extends SQLiteOpenHelper implements Serializable {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS ANOTACAO");

        db.execSQL("CREATE TABLE ANOTACAO(\n" +
                "\tID_ANOTACAO INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tTITULO VARCHAR(20) NOT NULL,\n" +
                "\tCONTEUDO TEXT NOT NULL\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ANOTACAO");
    }

    public void criarAnotacao(Anotacao anotacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("TITULO", anotacao.getTitulo());
        cv.put("CONTEUDO", anotacao.getConteudo());

        db.insert("ANOTACAO", "ID_ANOTACAO", cv);
    }

    public Cursor listaTodasAnotacoes() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT TITULO FROM ANOTACAO ORDER BY ID_ANOTACAO", null);
        return cur;
    }

    public Cursor buscarPeloTitulo(String titulo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  ID_ANOTACAO,TITULO,CONTEUDO  FROM ANOTACAO WHERE TITULO = ?";
        Cursor cur = db.rawQuery(selectQuery, new String[]{titulo});
        return cur;
    }

    public void atualizaAnotecao(Anotacao anotacao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("TITULO",anotacao.getTitulo());
        cv.put("CONTEUDO",anotacao.getConteudo());

        db.update("ANOTACAO",cv,"ID_ANOTACAO=?",new String[]{anotacao.getId().toString()});
    }

}
