package com.example.blocoDeNotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class AnotacaoActivity extends AppCompatActivity {

    private Database db;
    private Intent intent;
    private EditText titulo;
    private EditText conteudo;
    private Anotacao anotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacao);
        this.db = new Database(this, "database", null, 1);
        setAtributos();

    }

    private void setAtributos() {

        String tituloNota;
        titulo = findViewById(R.id.tituloEditView);
        conteudo = findViewById(R.id.conteudoEditTextTextMultiLine);
        intent = getIntent();
        tituloNota = intent.getStringExtra("titulo_nota");

        if (tituloNota != null) {

            Cursor c = db.buscarPeloTitulo(tituloNota);
            anotacao = new Anotacao();

            c.moveToFirst();
            anotacao.setId(c.getInt(c.getColumnIndex("ID_ANOTACAO")));
            anotacao.setConteudo(c.getString(c.getColumnIndex("CONTEUDO")));
            anotacao.setTitulo(c.getString(c.getColumnIndex("TITULO")));

            titulo.setText(anotacao.getTitulo());
            conteudo.setText(anotacao.getConteudo());
        }
    }

    public void salvarNovaAnotacao(View v) {
        if (anotacao == null){
            anotacao = new Anotacao(titulo.getText().toString(), conteudo.getText().toString());
            if (!anotacao.getTitulo().isEmpty()) {
                db.criarAnotacao(anotacao);
            }
        }else {
            anotacao.setTitulo(titulo.getText().toString());
            anotacao.setConteudo(conteudo.getText().toString());
            db.atualizaAnotecao(anotacao);
        }

        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}