package com.example.igrejas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {

    CardView cardEventos;
    CardView card_pedido_oracao;
    CardView cardAcaoSoild;
    CardView cardApoio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cardEventos = findViewById(R.id.cardEventos);
        card_pedido_oracao = findViewById(R.id.card_pedido_oracao);
        cardAcaoSoild = findViewById(R.id.cardAcoaSolid);
        cardApoio = findViewById(R.id.cardApoio); // ← adicionar aqui

         cardEventos.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(HomePage.this, Evento.class);
                 startActivity(intent);
             }
         });

        card_pedido_oracao.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomePage.this,Formulario_pedido_oracaoActivity.class);
            startActivity(intent);
        }
        });

        cardAcaoSoild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,AcaoSolidaria.class);
                startActivity(intent);
            }
        });

        cardApoio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,ApoioSocial.class);
                startActivity(intent);
            }
        });
    }
}