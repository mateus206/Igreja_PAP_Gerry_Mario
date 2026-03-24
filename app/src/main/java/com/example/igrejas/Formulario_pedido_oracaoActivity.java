package com.example.igrejas;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Formulario_pedido_oracaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_pedido_oracao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinnerType = findViewById(R.id.spinnerType);
        Button btnEnviar = findViewById(R.id.buttonenviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoPedido = spinnerType.getSelectedItem().toString();
                String nome = ((EditText) findViewById(R.id.editName)).getText().toString();
                String contacto = ((EditText) findViewById(R.id.editcontacto)).getText().toString();
                String detalhe = ((EditText) findViewById(R.id.editDetails)).getText().toString();

                if (tipoPedido.equals("Seleciona um tipo de pedido") || nome.isEmpty() || detalhe.isEmpty()) {
                    Toast.makeText(Formulario_pedido_oracaoActivity.this, "Por favor preenche todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Formulario_pedido_oracaoActivity.this, "Pedido enviado! 🙏", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
