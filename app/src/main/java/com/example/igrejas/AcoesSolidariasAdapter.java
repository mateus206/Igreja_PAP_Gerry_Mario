package com.example.igrejas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.AcaoSolidarias;

import java.util.ArrayList;

public class AcoesSolidariasAdapter extends RecyclerView.Adapter<AcoesSolidariasAdapter.ViewHolder> {

    ArrayList<AcaoSolidarias> lista;

    public AcoesSolidariasAdapter(ArrayList<AcaoSolidarias> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_acao_solidaria, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AcaoSolidarias acao = this.lista.get(position);

        holder.tvTitulo.setText(texto(acao.getNomeAcao(), "Ação solidária"));
        holder.tvDataHora.setText("Data inicio: " + texto(acao.getDataHoraInicio(), "Sem data"));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalheAcaoIrao.class);
            intent.putExtra("acao_id", acao.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
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
