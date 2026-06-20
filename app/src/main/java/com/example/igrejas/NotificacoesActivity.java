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

import com.example.igrejas.models.Notificacao;
import com.example.igrejas.models.notificacoesresponse.NotificacoesResponse;
import com.example.igrejas.models.utils.ApiConfig;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificacoesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Notificacao> listaNotificacoes = new ArrayList<>();
    NotificacoesAdapter adapter;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notificacoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewNotificacoes);
        adapter = new NotificacoesAdapter(listaNotificacoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        carregarNotificacoes();
    }

    private String getJwt() {
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        return prefs.getString("jwt", null);
    }

    private void carregarNotificacoes() {
        Request request = new Request.Builder()
                .url(ApiConfig.NOTIFICACOES_URL)
                .get()
                .addHeader("Authorization", "Bearer " + getJwt())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(NotificacoesActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";
                try {
                    NotificacoesResponse notificacoesResponse = gson.fromJson(responseBody, NotificacoesResponse.class);
                    runOnUiThread(() -> {
                        if (notificacoesResponse != null && notificacoesResponse.isSuccess() && notificacoesResponse.getData() != null) {
                            listaNotificacoes.clear();
                            if (notificacoesResponse.getData().getNotificacoes() != null) {
                                listaNotificacoes.addAll(notificacoesResponse.getData().getNotificacoes());
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String msg = notificacoesResponse != null ? notificacoesResponse.getMessage() : "Erro ao carregar notificações";
                            Toast.makeText(NotificacoesActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(NotificacoesActivity.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show());
                }
            }
        });
    }
}
