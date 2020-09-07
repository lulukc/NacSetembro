package com.example.nac02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public final String preferenceKey = "key";


    public void recuperarPreferencia(View v) {
        EditText chave = findViewById(R.id.chaveEditText);
        TextView textoRecuperado = findViewById(R.id.textoRecuperadoView);

        textoRecuperado.setText(getPreference(chave.getText().toString()));

        String toast = "texto recuperado, chave: " + chave.getText().toString();
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();

        chave.setText("");
    }

    public void gravarPreferencia(View v) {
        EditText chave = findViewById(R.id.tituloEditText);
        EditText texto = findViewById(R.id.conteudoEditText);

        addPreference(chave.getText().toString(), texto.getText().toString());

        String toast = "Nota salva, chave: " + chave;
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();

        texto.setText("");
        chave.setText("");
    }


    public void addPreference(String chave, String valor) {
        SharedPreferences sh = getSharedPreferences(preferenceKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putString(chave, valor);
        ed.apply();
    }

    public String getPreference(String chave) {
        SharedPreferences sh = getSharedPreferences(preferenceKey, Context.MODE_PRIVATE);
        return sh.getString(chave, "");
    }
}