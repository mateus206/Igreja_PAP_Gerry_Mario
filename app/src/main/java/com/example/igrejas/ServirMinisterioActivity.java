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

public class ServirMinisterioActivity extends AppCompatActivity {

    Spinner spinnerMinisterio;
    EditText editExperiencia, editDisponibilidade, editMensagem;
    Button btnEnviar;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_servir_ministerio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerMinisterio = findViewById(R.id.spinnerMinisterio);
        editExperiencia = findViewById(R.id.editExperienciaMinisterio);
        editDisponibilidade = findViewById(R.id.editDisponibilidadeMinisterio);
        editMensagem = findViewById(R.id.editMensagemMinisterio);
        btnEnviar = findViewById(R.id.buttonEnviarMinisterio);

        btnEnviar.setOnClickListener(v -> enviarInscricao());
    }

    private String getJwt() {
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        return prefs.getString("jwt", null);
    }

    private void enviarInscricao() {
        String ministerio = spinnerMinisterio.getSelectedItem().toString();
        String experiencia = editExperiencia.getText().toString().trim();
        String disponibilidade = editDisponibilidade.getText().toString().trim();
        String mensagem = editMensagem.getText().toString().trim();

        if (ministerio.equals("Seleciona o ministério")) {
            Toast.makeText(this, "Seleciona o ministério", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(disponibilidade)) {
            editDisponibilidade.setError("Indica a tua disponibilidade");
            editDisponibilidade.requestFocus();
            return;
        }

        String jsonBody = "{" +
                "\"ministerio\":" + gson.toJson(ministerio) + "," +
                "\"experiencia\":" + gson.toJson(experiencia) + "," +
                "\"disponibilidade\":" + gson.toJson(disponibilidade) + "," +
                "\"mensagem\":" + gson.toJson(mensagem) +
                "}";

        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(ApiConfig.MINISTERIOS_INSCRICAO_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getJwt())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(ServirMinisterioActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";
                try {
                    SimpleResponse simpleResponse = gson.fromJson(responseBody, SimpleResponse.class);
                    runOnUiThread(() -> {
                        if (simpleResponse != null && simpleResponse.isSuccess()) {
                            Toast.makeText(ServirMinisterioActivity.this, simpleResponse.getMessage(), Toast.LENGTH_LONG).show();
                            editExperiencia.setText("");
                            editDisponibilidade.setText("");
                            editMensagem.setText("");
                            spinnerMinisterio.setSelection(0);
                            finish();
                        } else {
                            String msg = simpleResponse != null ? simpleResponse.getMessage() : "Erro ao enviar inscrição";
                            Toast.makeText(ServirMinisterioActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(ServirMinisterioActivity.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show());
                }
            }
        });
    }
}
