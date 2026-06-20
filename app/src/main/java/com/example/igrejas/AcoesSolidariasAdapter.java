package com.example.igrejas;

// Comentários adicionados como aluno para explicar melhor o código.

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.AcaoSolidarias;

import java.util.ArrayList;

// este adapter liga cada ação solidária ao RecyclerView
public class AcoesSolidariasAdapter extends RecyclerView.Adapter<AcoesSolidariasAdapter.ViewHolder> {

    ArrayList<AcaoSolidarias> lista;

    public AcoesSolidariasAdapter(ArrayList<AcaoSolidarias> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    // aqui é criado o layout de cada item da lista
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_acao_solidaria, parent, false);

        return new ViewHolder(v);
    }

    @Override
    // aqui coloco os dados dentro de cada item da lista
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AcaoSolidarias acao = this.lista.get(position);

        holder.tvTitulo.setText(texto(acao.getNomeAcao(), "Ação solidária"));
        holder.tvDataHora.setText("Data inicio: " + texto(acao.getDataHoraInicio(), "Sem data"));

        // aqui defino o que acontece quando o utilizador carrega
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalheAcaoIrao.class);
            intent.putExtra("acao_id", acao.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    // devolve quantos elementos existem para o RecyclerView mostrar
    public int getItemCount() {
        return this.lista.size();
    }

    private String texto(String valor, String vazio) {
        if (valor == null || valor.trim().isEmpty()) {
            return vazio;
        }
        return valor;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitulo;
        TextView tvDataHora;

        ViewHolder(View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTituloAcao);
            tvDataHora = itemView.findViewById(R.id.tvDataHoraAcao);
        }
    }
}
