package com.example.nac;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DataGetter extends AsyncTask<String,Void,String> {

    private TextView idTexto;
    private TextView titulo;
    private TextView complemento;

    public DataGetter(TextView idTexto, TextView titulo, TextView complemento) {
        this.idTexto = idTexto;
        this.titulo = titulo;
        this.complemento = complemento;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {

        String url = strings[0];
        String result = NetworkToolkit.doGet(url);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        try{
            JSONObject jsonResponse = new JSONObject(s);

            //JSONObject dataResponse = jsonResponse.getJSONObject("data");

            String id = jsonResponse.getString("id");
            String title = jsonResponse.getString("title");
            String completed = jsonResponse.getString("completed");

            idTexto.setText(id);
            titulo.setText(title);
            complemento.setText(completed);
        }
        catch(JSONException e){
            this.idTexto.setText("erroJSON");
        }
    }
}

