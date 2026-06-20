package com.example.igrejas;

// Comentários adicionados como aluno para explicar melhor o código.

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

import com.example.igrejas.models.AcaoSolidarias;
import com.example.igrejas.models.acaosolidariasresponse.AcoesSolidariasResponse;
import com.example.igrejas.models.utils.ApiConfig;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// esta activity mostra as ações solidárias que existem na API
public class AcaoSolidaria extends AppCompatActivity {

    ArrayList<AcaoSolidarias> lista = new ArrayList<>();
    RecyclerView recyclerView;
    AcoesSolidariasAdapter acoesSolidariasAdapter;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    // aqui começa o ecrã e faço a ligação entre o XML e o Java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // escolho o layout que vai aparecer neste ecrã
        setContentView(R.layout.activity_acao_solidaria);
        // isto ajuda o layout a não ficar por baixo das barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // vou buscar os componentes que estão no ficheiro XML
        recyclerView = findViewById(R.id.recyclerViewAcoesSolidarias);
        acoesSolidariasAdapter = new AcoesSolidariasAdapter(lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(acoesSolidariasAdapter);

        carregarAcoesSolidarias();
    }

    private String getJwt() {
        // guardo ou leio dados da sessão do utilizador
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        return prefs.getString("jwt", null);
    }

    // função que vai buscar as ações solidárias ao servidor
    private void carregarAcoesSolidarias() {
        // crio o pedido para mandar para o servidor
        Request request = new Request.Builder()
                .url(ApiConfig.ACAO_SOLIDARIAS_URL)
                .get()
                .addHeader("Authorization", "Bearer " + getJwt())
                .build();

        // faço a chamada à API em segundo plano para não bloquear a app
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // volto para a interface porque a resposta vem noutra thread
                runOnUiThread(() ->
                        Toast.makeText(AcaoSolidaria.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";

                try {
                    AcoesSolidariasResponse acoesResponse = gson.fromJson(responseBody, AcoesSolidariasResponse.class);

                    if (acoesResponse != null && acoesResponse.isSuccess()
                            && acoesResponse.getData() != null
                            && acoesResponse.getData().getAcoesSolidarias() != null) {

                        runOnUiThread(() -> {
                            lista.clear();
                            lista.addAll(acoesResponse.getData().getAcoesSolidarias());
                            acoesSolidariasAdapter.notifyDataSetChanged();
                        });
                    } else {
                        String msg = acoesResponse != null ? acoesResponse.getMessage() : "Resposta inválida";
                        runOnUiThread(() -> Toast.makeText(AcaoSolidaria.this, msg, Toast.LENGTH_LONG).show());
                    }

                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(AcaoSolidaria.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show());
                }
            }
        });
    }
}
