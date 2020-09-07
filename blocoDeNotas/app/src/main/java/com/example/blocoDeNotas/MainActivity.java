package com.example.blocoDeNotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Database db;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, AnotacaoActivity.class);
        this.db = new Database(this, "database", null, 1);
        iniciarLista();
    }

    private void iniciarLista() {
        ListView listView = (ListView) findViewById(R.id.anotacaoListView);
        final List<String> listaAnotacao = preencherLista();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAnotacao);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!listaAnotacao.get(0).equals("Lista vazia")){
                    intent.putExtra("titulo_nota", listaAnotacao.get(position));
                    startActivity(intent);
                }
            }
        });
    }

    private List<String> preencherLista() {
        List<String> dados = new ArrayList<>();
        Cursor c = db.listaTodasAnotacoes();
        if (c.getCount() == 0) {
            dados.add("Lista vazia");
            return dados;
        }
        c.moveToFirst();
        do {
            dados.add(c.getString(c.getColumnIndex("TITULO")));
        } while (c.moveToNext());
        return dados;
    }


    public void novaAnotacao(View v) {
        startActivity(intent);
    }

}
