package com.example.igrejas;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Formulario_pedido_oracaoActivity extends AppCompatActivity {

    EditText editName, editContacto, editDetails;
    Spinner spinnerType;
    CheckBox checkAnonymous;
    Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pedido_oracao);

        editName = findViewById(R.id.editName);
        editContacto = findViewById(R.id.editcontacto);
        editDetails = findViewById(R.id.editDetails);
        buttonEnviar = findViewById(R.id.buttonenviar);

        // Configurar Spinner
        configurarSpinner();

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editName.getText().toString().trim();
                String contacto = editContacto.getText().toString().trim();
                String tipo = spinnerType.getSelectedItem().toString();
                String detalhes = editDetails.getText().toString().trim();
                boolean anonimo = checkAnonymous.isChecked();


                if (detalhes.isEmpty()) {
                    Toast.makeText(Formulario_pedido_oracaoActivity.this,
                            "Por favor, escreva o pedido",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (anonimo) {
                    nome = "Anônimo";
                }

                // Mensagem final
                String mensagem = "Nome: " + nome +
                        "\nContacto: " + contacto +
                        "\nTipo: " + tipo +
                        "\nPedido: " + detalhes;

                // Exibir confirmação
                Toast.makeText(Formulario_pedido_oracaoActivity.this,
                        "Pedido enviado com sucesso!",
                        Toast.LENGTH_LONG).show();



                limparFormulario();
            }
        });
    }

    private void configurarSpinner() {
        String[] tipos = {
                "Saúde",
                "Família",
                "Financeiro",
                "Espiritual",
                "Outros"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                tipos
        );

        spinnerType.setAdapter(adapter);
    }

    private void limparFormulario() {
        editName.setText("");
        editContacto.setText("");
        editDetails.setText("");
        checkAnonymous.setChecked(false);
        spinnerType.setSelection(0);
    }
}
