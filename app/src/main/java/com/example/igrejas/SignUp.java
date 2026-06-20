package com.example.igrejas;

// Comentários adicionados como aluno para explicar melhor o código.

import android.content.Intent;
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

import com.example.igrejas.models.signupresponse.SignupResponse;
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

// esta activity é para criar uma conta nova no sistema
public class SignUp extends AppCompatActivity {

    EditText editTextUserName, editTextEmailAddress, editTextNumber, editTextPass;
    TextView txtRespostaSign;
    Button btnSignUp;
    TextView textJaTenho;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    @Override
    // aqui começa o ecrã e faço a ligação entre o XML e o Java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // escolho o layout que vai aparecer neste ecrã
        setContentView(R.layout.activity_sign_up);
        // isto ajuda o layout a não ficar por baixo das barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // vou buscar os componentes que estão no ficheiro XML
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextNumber =findViewById(R.id.editTextNumber);
        editTextPass = findViewById(R.id.editTextPass);
        btnSignUp = findViewById(R.id.buttonSignup);
        txtRespostaSign = findViewById(R.id.txtRespostaSign);
        textJaTenho = findViewById(R.id.textViewJaTenho);

        // aqui defino o que acontece quando o utilizador carrega
        btnSignUp.setOnClickListener(v -> fazerSignUp());

        textJaTenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }

    public void fazerSignUp() {
        String nome = editTextUserName.getText().toString().trim();
        String email = editTextEmailAddress.getText().toString().trim();
        String telefone = editTextNumber.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignUp.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }
        // preparo os dados que vão ser enviados no pedido
        RequestBody formBody = new FormBody.Builder()
                .add("nome", nome)
                .add("email", email)
                .add("telefone", telefone)
                .add("password", password)
                .build();

        // crio o pedido para mandar para o servidor
        Request request = new Request.Builder()
                .url(ApiConfig.SIGNUPURL)
                .post(formBody)
                .build();

        // faço a chamada à API em segundo plano para não bloquear a app
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                // volto para a interface porque a resposta vem noutra thread
                runOnUiThread(() ->
                        txtRespostaSign.setText("Erro de ligação: " + e.getMessage())
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null
                        ? response.body().string()
                        : "";
                String statusCode = String.valueOf(response.code());

                try {
                    SignupResponse signupResponse =
                            gson.fromJson(responseBody, SignupResponse.class);

                    runOnUiThread(() -> {


                        if (signupResponse == null) {
                            txtRespostaSign.setText("Erro: resposta inválida do servidor");
                            return;
                        }

                        String msg = signupResponse.getMessage() != null
                                ? signupResponse.getMessage()
                                : "Email ou Password errado.";

                        txtRespostaSign.setText("Code: " + statusCode + "\n" + msg);


                        if (response.isSuccessful()
                                && signupResponse.isSuccess()) {
                            Toast.makeText(SignUp.this,
                                    "Sign Up com sucesso", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(SignUp.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            txtRespostaSign.setText("Erro ao processar resposta:\n" + responseBody)
                    );
                }
            }
        });
    }
}
