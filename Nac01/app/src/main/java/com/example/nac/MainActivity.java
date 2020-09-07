package com.example.nac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String url = "https://jsonplaceholder.typicode.com/todos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void pesquisar(View v){
        EditText pesquisa = findViewById(R.id.idPesquisa);
        url += pesquisa.getText().toString();

        TextView idTexto = findViewById(R.id.idTexto);
        TextView titulo = findViewById(R.id.Titulo);
        TextView complemento = findViewById(R.id.Complemento);

        new DataGetter(idTexto,titulo,complemento).execute(url);



    }
}
