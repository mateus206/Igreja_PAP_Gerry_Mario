package com.example.igrejas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
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

public class ApoioSocial extends AppCompatActivity {

    EditText editLocal;
    EditText editCodigoPostal;
    EditText editTelefone;
    EditText editMembrosFamilia;
    EditText editPedido;
    Button btnEnviar;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apoio_social);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editLocal = findViewById(R.id.editName);
        editCodigoPostal = findViewById(R.id.editcontacto);
        editTelefone = findViewById(R.id.editTextContact);
        editMembrosFamilia = findViewById(R.id.editTextNumber2);
        editPedido = findViewById(R.id.editDetails);
        btnEnviar = findViewById(R.id.buttonenviar);

        btnEnviar.setOnClickListener(v -> enviarPedidoApoio());
    }

    private String getJwt() {
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        return prefs.getString("jwt", null);
    }

    private void enviarPedidoApoio() {
        String local = editLocal.getText().toString().trim();
        String codigoPostal = editCodigoPostal.getText().toString().trim();
        String telefone = editTelefone.getText().toString().trim();
        String membrosFamilia = editMembrosFamilia.getText().toString().trim();
        String pedidoAjuda = editPedido.getText().toString().trim();

        if (TextUtils.isEmpty(local)) {
            editLocal.setError("Indica o local");
            editLocal.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(codigoPostal)) {
            editCodigoPostal.setError("Indica o código postal");
            editCodigoPostal.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(telefone)) {
            editTelefone.setError("Indica o telefone");
            editTelefone.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(membrosFamilia)) {
            editMembrosFamilia.setError("Indica os membros de família");
            editMembrosFamilia.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pedidoAjuda)) {
            editPedido.setError("Escreve o pedido");
            editPedido.requestFocus();
            return;
        }

        String jsonBody = "{" +
                "\"local\":" + gson.toJson(local) + "," +
                "\"codigo_postal\":" + gson.toJson(codigoPostal) + "," +
                "\"telefone\":" + gson.toJson(telefone) + "," +
                "\"membros_de_familia\":" + gson.toJson(membrosFamilia) + "," +
                "\"pedido_ajuda\":" + gson.toJson(pedidoAjuda) +
                "}";

        RequestBody body = RequestBody.create(
                jsonBody,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(ApiConfig.APOIO_SOCIAIS_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getJwt())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(ApoioSocial.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";

                try {
                    SimpleResponse simpleResponse = gson.fromJson(responseBody, SimpleResponse.class);

                    runOnUiThread(() -> {
                        if (simpleResponse != null && simpleResponse.isSuccess()) {
                            Toast.makeText(ApoioSocial.this, simpleResponse.getMessage(), Toast.LENGTH_LONG).show();
                            editLocal.setText("");
                            editCodigoPostal.setText("");
                            editTelefone.setText("");
                            editMembrosFamilia.setText("");
                            editPedido.setText("");
                            finish();
                        } else {
                            String msg = simpleResponse != null ? simpleResponse.getMessage() : "Erro ao enviar pedido";
                            Toast.makeText(ApoioSocial.this, msg, Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(ApoioSocial.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show()
                    );
                }
            }
        });
    }
}
