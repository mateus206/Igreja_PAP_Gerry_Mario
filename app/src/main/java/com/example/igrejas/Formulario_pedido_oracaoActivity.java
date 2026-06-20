package com.example.igrejas;

// Comentários adicionados como aluno para explicar melhor o código.

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

// esta activity envia o pedido de oração para a API
public class Formulario_pedido_oracaoActivity extends AppCompatActivity {

    Spinner spinnerType;
    EditText editContacto;
    EditText editDetails;
    Button btnEnviar;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    // aqui começa o ecrã e faço a ligação entre o XML e o Java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // escolho o layout que vai aparecer neste ecrã
        setContentView(R.layout.activity_formulario_pedido_oracao);
        // isto ajuda o layout a não ficar por baixo das barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // vou buscar os componentes que estão no ficheiro XML
        spinnerType = findViewById(R.id.spinnerType);
        editContacto = findViewById(R.id.editcontacto);
        editDetails = findViewById(R.id.editDetails);
        btnEnviar = findViewById(R.id.buttonenviar);

        // aqui defino o que acontece quando o utilizador carrega
        btnEnviar.setOnClickListener(v -> enviarPedidoOracao());
    }

    private String getJwt() {
        // guardo ou leio dados da sessão do utilizador
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        return prefs.getString("jwt", null);
    }

    private void enviarPedidoOracao() {
        String tipoPedido = spinnerType.getSelectedItem().toString();
        String email = editContacto.getText().toString().trim();
        String descricao = editDetails.getText().toString().trim();

        if (tipoPedido.equals("Seleciona um tipo de pedido")) {
            Toast.makeText(this, "Seleciona um tipo de pedido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editContacto.setError("Indica o email ou contacto");
            editContacto.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(descricao)) {
            editDetails.setError("Escreve o pedido");
            editDetails.requestFocus();
            return;
        }

        String jsonBody = "{" +
                "\"email\":" + gson.toJson(email) + "," +
                "\"tipo_pedido\":" + gson.toJson(tipoPedido) + "," +
                "\"descricao\":" + gson.toJson(descricao) +
                "}";

        // preparo os dados que vão ser enviados no pedido
        RequestBody body = RequestBody.create(
                jsonBody,
                MediaType.parse("application/json; charset=utf-8")
        );

        // crio o pedido para mandar para o servidor
        Request request = new Request.Builder()
                .url(ApiConfig.PEDIDO_ORACOES_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getJwt())
                .build();

        // faço a chamada à API em segundo plano para não bloquear a app
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // volto para a interface porque a resposta vem noutra thread
                runOnUiThread(() ->
                        Toast.makeText(Formulario_pedido_oracaoActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";

                try {
                    SimpleResponse simpleResponse = gson.fromJson(responseBody, SimpleResponse.class);

                    runOnUiThread(() -> {
                        if (simpleResponse != null && simpleResponse.isSuccess()) {
                            Toast.makeText(Formulario_pedido_oracaoActivity.this, simpleResponse.getMessage(), Toast.LENGTH_LONG).show();
                            editContacto.setText("");
                            editDetails.setText("");
                            spinnerType.setSelection(0);
                            finish();
                        } else {
                            String msg = simpleResponse != null ? simpleResponse.getMessage() : "Erro ao enviar pedido";
                            Toast.makeText(Formulario_pedido_oracaoActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(Formulario_pedido_oracaoActivity.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show()
                    );
                }
            }
        });
    }
}
