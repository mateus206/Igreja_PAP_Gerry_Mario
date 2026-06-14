package com.example.igrejas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.Eventos;
import com.example.igrejas.models.eventosresponse.EventosResponse;
import com.example.igrejas.models.utils.ApiConfig;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Evento extends AppCompatActivity {

    ArrayList<Eventos> lista = new ArrayList<>();
    RecyclerView recyclerView;
    EventosAdapter eventosAdapter;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

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

        this.recyclerView = findViewById(R.id.recyclerView);
        this.eventosAdapter = new EventosAdapter(this.lista);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(eventosAdapter);

        carregarEventos();
    }

    private String getJwt() {
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        return prefs.getString("jwt", null);
    }

    private void carregarEventos() {
        Request request = new Request.Builder()
                .url(ApiConfig.EVENTOSURL)
                .get()
                .addHeader("Authorization", "Bearer " + getJwt())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(Evento.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";

                try {
                    EventosResponse eventosResponse = gson.fromJson(responseBody, EventosResponse.class);

                    if (eventosResponse != null && eventosResponse.isSuccess()
                            && eventosResponse.getData() != null
                            && eventosResponse.getData().getEventos() != null) {

                        runOnUiThread(() -> {
                            lista.clear();
                            lista.addAll(eventosResponse.getData().getEventos());
                            eventosAdapter.notifyDataSetChanged();
                        });
                    } else {
                        String msg = eventosResponse != null ? eventosResponse.getMessage() : "Resposta inválida";
                        runOnUiThread(() -> Toast.makeText(Evento.this, msg, Toast.LENGTH_LONG).show());
                    }

                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(Evento.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show());
                }
            }
        });
    }
}
