package com.example.igrejas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.Eventos;

import java.util.ArrayList;

public class Evento extends AppCompatActivity {

    ArrayList<Eventos> lista;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_evento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.buildEventos();

        this.recyclerView = findViewById(R.id.recyclerView);

        EventosAdapter heroAdapter = new EventosAdapter(this.lista);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.recyclerView.setAdapter(heroAdapter);
    }

    public void buildEventos(){

        Eventos e1 = new Eventos("Culto de Domingo", "Culto", "Igreja Central", "2026-03-21 10:00");
        Eventos e2 = new Eventos("Estudo Bíblico", "Estudo", "Igreja Batista", "2026-03-22 19:00");
        Eventos e3 = new Eventos("Culto Jovem", "Culto", "Igreja da Graça", "2026-03-23 18:00");
        Eventos e4 = new Eventos("Reunião de Oração", "Oração", "Igreja Evangélica", "2026-03-24 20:00");
        Eventos e5 = new Eventos("Culto da Família", "Culto", "Igreja Metodista", "2026-03-25 17:00");
        Eventos e6 = new Eventos("Louvor e Adoração", "Louvor", "Igreja Pentecostal", "2026-03-26 19:30");
        Eventos e7 = new Eventos("Aula de Escola Dominical", "Estudo", "Igreja Presbiteriana", "2026-03-27 09:00");
        Eventos e8 = new Eventos("Encontro de Jovens", "Reunião", "Igreja Adventista", "2026-03-28 16:00");
        Eventos e9 = new Eventos("Culto Especial", "Culto", "Igreja Católica", "2026-03-29 18:30");
        Eventos e10 = new Eventos("Retiro Espiritual", "Retiro", "Igreja Batista", "2026-03-30 08:00");

        this.lista = new ArrayList<>();

        this.lista.add(e1);
        this.lista.add(e2);
        this.lista.add(e3);
        this.lista.add(e4);
        this.lista.add(e5);
        this.lista.add(e6);
        this.lista.add(e7);
        this.lista.add(e8);
        this.lista.add(e9);
        this.lista.add(e10);

    }
}