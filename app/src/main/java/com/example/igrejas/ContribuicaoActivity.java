package com.example.igrejas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.igrejas.models.defaultresponse.SimpleResponse;
import com.example.igrejas.models.utils.ApiConfig;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ContribuicaoActivity extends AppCompatActivity {

    Spinner spinnerTipo, spinnerMetodo;
    EditText editValor, editObservacao;
    Button btnEnviar;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contribuicao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerTipo = findViewById(R.id.spinnerTipoContribuicao);
        spinnerMetodo = findViewById(R.id.spinnerMetodoPagamento);
        editValor = findViewById(R.id.editValorContribuicao);
        editObservacao = findViewById(R.id.editObservacaoContribuicao);
        btnEnviar = findViewById(R.id.buttonEnviarContribuicao);

        btnEnviar.setOnClickListener(v -> enviarContribuicao());
    }

    private String getJwt() {
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        return prefs.getString("jwt", null);
    }

    private void enviarContribuicao() {
        String tipo = spinnerTipo.getSelectedItem().toString();
        String metodo = spinnerMetodo.getSelectedItem().toString();
        String valor = editValor.getText().toString().trim();
        String observacao = editObservacao.getText().toString().trim();

        if (tipo.equals("Seleciona o tipo")) {
            Toast.makeText(this, "Seleciona o tipo de contribuição", Toast.LENGTH_SHORT).show();
            return;
        }
        if (metodo.equals("Seleciona o método")) {
            Toast.makeText(this, "Seleciona o método de pagamento", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(valor)) {
            editValor.setError("Indica o valor");
            editValor.requestFocus();
            return;
        }

        String jsonBody = "{" +
                "\"tipo\":" + gson.toJson(tipo) + "," +
                "\"valor\":" + gson.toJson(valor) + "," +
                "\"metodo_pagamento\":" + gson.toJson(metodo) + "," +
                "\"observacao\":" + gson.toJson(observacao) +
                "}";

        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(ApiConfig.CONTRIBUICOES_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getJwt())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(ContribuicaoActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";
                try {
                    SimpleResponse simpleResponse = gson.fromJson(responseBody, SimpleResponse.class);
                    runOnUiThread(() -> {
                        if (simpleResponse != null && simpleResponse.isSuccess()) {
                            Toast.makeText(ContribuicaoActivity.this, simpleResponse.getMessage(), Toast.LENGTH_LONG).show();
                            editValor.setText("");
                            editObservacao.setText("");
                            spinnerTipo.setSelection(0);
                            spinnerMetodo.setSelection(0);
                            finish();
                        } else {
                            String msg = simpleResponse != null ? simpleResponse.getMessage() : "Erro ao enviar contribuição";
                            Toast.makeText(ContribuicaoActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(ContribuicaoActivity.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show());
                }
            }
        });
    }
}
