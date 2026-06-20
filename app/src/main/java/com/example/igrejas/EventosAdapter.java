package com.example.igrejas;

// Comentários adicionados como aluno para explicar melhor o código.

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.Eventos;

import java.util.ArrayList;

// este adapter liga cada evento ao RecyclerView
public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {

    ArrayList<Eventos> lista;

    public EventosAdapter(ArrayList<Eventos> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    // aqui é criado o layout de cada item da lista
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);

        return new ViewHolder(v);
    }

    @Override
    // aqui coloco os dados dentro de cada item da lista
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Eventos evento = this.lista.get(position);

        holder.tvTitulo.setText(texto(evento.getTitulo(), "Evento"));
        holder.tvTipo.setText(texto(evento.getTipo(), "Tipo não definido"));
        holder.tvLocal.setText(texto(evento.getLocal(), "Igreja"));
        holder.tvDataHora.setText("Data inicio: " + texto(evento.getDataHora(), "Sem data"));
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
        TextView tvTipo;
        TextView tvLocal;
        TextView tvDataHora;

        ViewHolder(View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvLocal = itemView.findViewById(R.id.tvLocal);
            tvDataHora = itemView.findViewById(R.id.tvDataHora);
        }
    }
}
