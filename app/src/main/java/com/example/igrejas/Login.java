package com.example.igrejas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.igrejas.models.User;
import com.example.igrejas.models.loginresponse.LoginResponse;
import com.example.igrejas.models.utils.ApiConfig;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    Button btnSignIn;
    TextView registar;

    EditText editTextEmailAddress;

    EditText editTextPassword;

    TextView txtResposta;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSignIn = findViewById(R.id.buttonLogin);
        registar = findViewById(R.id.textViewRegistarTe);

        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
        txtResposta = findViewById(R.id.txtResposta);

        this.btnSignIn.setOnClickListener(v -> fazerLogin());

        this.registar.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        });
    }

    private void fazerLogin() {
        String email    = editTextEmailAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmailAddress.setError("Email obrigatório");
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password obrigatória");
            return;
        }

        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(ApiConfig.LOGINURL)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() ->
                        txtResposta.setText("Erro de ligação: " + e.getMessage())
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null
                        ? response.body().string()
                        : "";
                String statusCode = String.valueOf(response.code());

                try {
                    LoginResponse loginResponse =
                            gson.fromJson(responseBody, LoginResponse.class);

                    runOnUiThread(() -> {

                        // Guard: resposta nula ou inválida
                        if (loginResponse == null) {
                            txtResposta.setText("Erro: resposta inválida do servidor");
                            return;
                        }

                        String msg = loginResponse.getMessage() != null
                                ? loginResponse.getMessage()
                                : "Email ou Password errado.";

                        txtResposta.setText("Code: " + statusCode + "\n" + msg);


                        if (response.isSuccessful()
                                && loginResponse.isSuccess()
                                && loginResponse.getData() != null
                                && loginResponse.getData().getUser() != null) {

                            SharedPreferences prefs =
                                    getSharedPreferences("app_session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            editor.putString("jwt", loginResponse.getData().getJwt());

                            User user = loginResponse.getData().getUser();

                            editor.putInt("id", user.getId());
                            editor.putBoolean("is_admin", user.getIsAdmin());
                            editor.putString("nome", user.getNome());
                            editor.putString("telefone", user.getTelefone());
                            editor.putString("email", user.getEmail());
                            editor.putString("data_registro", user.getDataRegistro());
                            editor.putString("estado", user.getEstado());
                            editor.putString("password", user.getPassword());
                            editor.putBoolean("is_verified", user.isVerified());

                            editor.apply();
                            Toast.makeText(Login.this,
                                    "Login com sucesso", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Login.this, HomePage.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            txtResposta.setText("Erro ao processar resposta:\n" + responseBody)
                    );
                }
            }
        });
    }
}