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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText etCurrentPassword;
    private EditText etNewPassword;
    private Button btnUpdatePassword;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCurrentPassword = findViewById(R.id.etCurrentPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);

        btnUpdatePassword.setOnClickListener(v -> atualizarPassword());
    }

    private void atualizarPassword() {
        String currentPassword = etCurrentPassword.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();

        if (TextUtils.isEmpty(currentPassword)) {
            etCurrentPassword.setError("Indica a password atual");
            etCurrentPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            etNewPassword.setError("Indica a nova password");
            etNewPassword.requestFocus();
            return;
        }

        if (newPassword.length() < 6) {
            etNewPassword.setError("A nova password deve ter pelo menos 6 caracteres");
            etNewPassword.requestFocus();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
        String jwt = prefs.getString("jwt", null);

        if (jwt == null || jwt.isEmpty()) {
            Toast.makeText(UpdatePasswordActivity.this, "Tens de fazer login novamente", Toast.LENGTH_LONG).show();
            return;
        }

        RequestBody formBody = new FormBody.Builder()
                .add("current_password", currentPassword)
                .add("new_password", newPassword)
                .build();

        Request request = new Request.Builder()
                .url(ApiConfig.UPDATE_PASSWORD_URL)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + jwt)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(UpdatePasswordActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";

                try {
                    SimpleResponse simpleResponse = gson.fromJson(responseBody, SimpleResponse.class);

                    runOnUiThread(() -> {
                        if (simpleResponse != null && simpleResponse.isSuccess()) {
                            Toast.makeText(UpdatePasswordActivity.this, simpleResponse.getMessage(), Toast.LENGTH_LONG).show();

                            etCurrentPassword.setText("");
                            etNewPassword.setText("");

                            finish();
                        } else {
                            String message = simpleResponse != null ? simpleResponse.getMessage() : "Erro ao atualizar password";
                            Toast.makeText(UpdatePasswordActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(UpdatePasswordActivity.this, "Erro no parse da resposta", Toast.LENGTH_LONG).show()
                    );
                }
            }
        });
    }
}
