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

import com.example.igrejas.models.User;
import com.example.igrejas.models.profileresponse.ProfileResponse;
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

public class EditProfileActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etTelefone;
    private EditText etEmail;
    private Button btnGuardarPerfil;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private String jwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        btnGuardarPerfil = findViewById(R.id.btnGuardarPerfil);

        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        jwt = prefs.getString("jwt", null);

        if (jwt == null || jwt.isEmpty()) {
            Toast.makeText(EditProfileActivity.this, "Tens de fazer login novamente", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        etNome.setText(prefs.getString("nome", ""));
        etTelefone.setText(prefs.getString("telefone", ""));
        etEmail.setText(prefs.getString("email", ""));

        carregarPerfil();

        btnGuardarPerfil.setOnClickListener(v -> guardarPerfil());
    }

    private void carregarPerfil() {
        Request request = new Request.Builder()
                .url(ApiConfig.PROFILE_URL)
                .get()
                .addHeader("Authorization", "Bearer " + jwt)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(EditProfileActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";

                try {
                    ProfileResponse profileResponse = gson.fromJson(responseBody, ProfileResponse.class);

                    if (profileResponse != null && profileResponse.isSuccess()
                            && profileResponse.getData() != null
                            && profileResponse.getData().getUser() != null) {

                        User user = profileResponse.getData().getUser();

                        runOnUiThread(() -> {
                            etNome.setText(user.getNome());
                            etTelefone.setText(user.getTelefone());
                            etEmail.setText(user.getEmail());
                        });
                    }
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(EditProfileActivity.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show());
                }
            }
        });
    }

    private void guardarPerfil() {
        String nome = etNome.getText().toString().trim();
        String telefone = etTelefone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (TextUtils.isEmpty(nome)) {
            etNome.setError("Indica o nome");
            etNome.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Indica o email");
            etEmail.requestFocus();
            return;
        }

        if (!email.contains("@")) {
            etEmail.setError("Email inválido");
            etEmail.requestFocus();
            return;
        }

        RequestBody formBody = new FormBody.Builder()
                .add("nome", nome)
                .add("telefone", telefone)
                .add("email", email)
                .build();

        Request request = new Request.Builder()
                .url(ApiConfig.UPDATE_PROFILE_URL)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + jwt)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(EditProfileActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";

                try {
                    ProfileResponse profileResponse = gson.fromJson(responseBody, ProfileResponse.class);

                    runOnUiThread(() -> {
                        if (profileResponse != null && profileResponse.isSuccess()) {
                            User user = null;
                            if (profileResponse.getData() != null) {
                                user = profileResponse.getData().getUser();
                            }

                            SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            if (user != null) {
                                editor.putInt("id", user.getId());
                                editor.putBoolean("is_admin", user.getIsAdmin());
                                editor.putString("nome", user.getNome());
                                editor.putString("telefone", user.getTelefone());
                                editor.putString("email", user.getEmail());
                                editor.putString("data_registro", user.getDataRegistro());
                                editor.putString("estado", user.getEstado());
                                editor.putBoolean("is_verified", user.isVerified());
                            } else {
                                editor.putString("nome", nome);
                                editor.putString("telefone", telefone);
                                editor.putString("email", email);
                            }

                            editor.apply();

                            String message = profileResponse.getMessage() != null ? profileResponse.getMessage() : "Perfil atualizado com sucesso";
                            Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            String message = profileResponse != null ? profileResponse.getMessage() : "Erro ao atualizar perfil";
                            Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(EditProfileActivity.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show());
                }
            }
        });
    }
}
