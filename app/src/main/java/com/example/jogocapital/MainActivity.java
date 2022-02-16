package com.example.jogocapital;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static int nestado = 1, npontuacao = 0, npergunta = 0, qcont = 1;
    public static int p1, p2, p3, p4;

    public static  String[] estados = {
            "São Paulo", "Sao Paulo",
            "Rio de Janeiro", "Rio de Janeiro",
            "Minas Gerais", "Belo Horizonte",
            "Paraná", "Curitiba",
            "Roraima", "Boa Vista",
            "Rio Grande do Sul", "Porto Alegre",
            "Mato Grosso do Sul", "Campo Grande",
            "Amazonas", "Manaus",
            "Tocantins", "Palmas",
            "Pernambuco", "Recife",
            "Rio Grande do Norte", "Natal",
            "Bahia", "Salvador",
            "Acre",  "Rio Branco",
            "Ceará", "Fortaleza",
            "Distrito Federal", "Brasilia",
    };

    public void jogo (View view){
        Button prox = findViewById(R.id.prox);
        TextView npontuacao = findViewById(R.id.npontuacao);
        TextView resultado = findViewById(R.id.resultado);
        MainActivity.qcont = 0;
        MainActivity.npontuacao = 0;
        npontuacao.setText(Integer.toString(MainActivity.npontuacao));
        MainActivity.npergunta = 0;
        prox.setClickable(false);
        resultado.setTextColor(Color.GREEN);
        criaQuestao();
    }

    public void criaQuestao() {
        TextView npergunta = findViewById(R.id.npergunta);
        TextView pergunta = findViewById(R.id.pergunta);
        TextView resposta = findViewById(R.id.resposta);
        TextView teste = findViewById(R.id.resultado);
        Button prox = findViewById(R.id.prox);
        Button confirmar = findViewById(R.id.confirmar);
        resposta.setText("");
        teste.setText("");
        Random r = new Random();
        MainActivity.nestado = r.nextInt(29);

        while (MainActivity.nestado%2 != 0 || MainActivity.p1 == MainActivity.nestado || MainActivity.p2 == MainActivity.nestado
                || MainActivity.p3 == MainActivity.nestado || MainActivity.p4 == MainActivity.nestado){
            r = new Random();
            MainActivity.nestado = r.nextInt(29);
        }

        MainActivity.npergunta = MainActivity.npergunta + 1;
        npergunta.setText(Integer.toString(MainActivity.npergunta) + "/5");
        pergunta.setText("Qual a capital de " + MainActivity.estados[nestado] + "?");
        prox.setVisibility(View.GONE);
        confirmar.setVisibility(View.VISIBLE);
        confirmar.setClickable(true);
    }

    public void enviar (View view){
        TextView resposta = findViewById(R.id.resposta);
        TextView resultado = findViewById(R.id.resultado);
        Button prox = findViewById(R.id.prox);
        Button confirmar = findViewById(R.id.confirmar);
        Button bprinc = findViewById(R.id.bprinc);
        TextView npontuacao = findViewById(R.id.npontuacao);

        if (resposta.length() == 0)
            Toast.makeText(this, "Insira uma capital.", Toast.LENGTH_SHORT).show();
        else {
            String[] rcerta = {""};
            String ruser = resposta.getText().toString();
            rcerta[0] = MainActivity.estados[nestado + 1];

            if (ruser.equalsIgnoreCase(MainActivity.estados[MainActivity.nestado + 1])) {

                bprinc.setVisibility(View.GONE);
                resultado.setVisibility(View.VISIBLE);
                resultado.setText("CORRETO!");
                prox.setClickable(true);
                confirmar.setVisibility(View.GONE);
                prox.setVisibility(View.VISIBLE);
                MainActivity.qcont = MainActivity.qcont + 1;
                MainActivity.npontuacao = MainActivity.npontuacao + 10;
                npontuacao.setText(Integer.toString(MainActivity.npontuacao));

                if (MainActivity.qcont >= 5){
                    Toast.makeText(this, "Parabéns!", Toast.LENGTH_SHORT).show();
                    bprinc.setVisibility(View.VISIBLE);
                    prox.setVisibility(View.GONE);
                    bprinc.setText("Jogar de novo");
                }
            } else {
                bprinc.setVisibility(View.VISIBLE);
                prox.setVisibility(View.GONE);
                bprinc.setText("Tentar de novo");
                resultado.setVisibility(View.VISIBLE);
                resultado.setTextColor(Color.RED);
                resultado.setText("ERRADO!");
            }
        }
    }
    public void proxima(View view){
        Button prox = findViewById(R.id.prox);
        prox.setVisibility(View.GONE);
        criaQuestao();
    }
}

