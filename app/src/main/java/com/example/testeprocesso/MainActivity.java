package com.example.testeprocesso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random gerador = new Random(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void gerar(View v){
            Gerador gerador = new Gerador();
            gerador.execute(30);

    }

    class Gerador extends AsyncTask<Integer,Integer,List>{
        @Override
        protected List doInBackground(Integer... contatores) {
            int quantidade = contatores[0];
            LinkedList<Integer> nums = new LinkedList<>();
            for (int i = 0; i < quantidade; i++) {
                int numero = gerador.nextInt(60);
                nums.add(numero)
                publishProgress(numero);
                try {
                    Thread.sleep(1000);
                }catch (Throwable t) {}
            }

            return nums;
        }
        @Override
        public void onProgressUpdate(Integer... valores){
            TextView tv = (TextView) findViewById(R.id.numeros);
            tv.setText(tv.getText() + String.valueOf(valores[0])+"\n");
        }

        @Override
        public void onPostExecute(List nums){
            //processar a lista, e acessar componentes de UI.

        }
    }

}