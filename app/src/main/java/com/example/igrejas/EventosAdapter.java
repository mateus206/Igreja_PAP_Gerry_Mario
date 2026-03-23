package com.example.igrejas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.Eventos;

import java.util.ArrayList;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {

    ArrayList<Eventos> lista;

    public  EventosAdapter(ArrayList<Eventos> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Eventos evento = this.lista.get(position);

        holder.tvTitulo.setText(evento.getTitulo());
        holder.tvTipo.setText(evento.getTipo());
        holder.tvLocal.setText(evento.getLocal());
        holder.tvDataHora.setText(evento.getDataHora());
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitulo;
        TextView tvTipo;
        TextView tvLocal;
        TextView tvDataHora;

        ViewHolder(View itemView){
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvLocal = itemView.findViewById(R.id.tvLocal);
            tvDataHora = itemView.findViewById(R.id.tvDataHora);
        }
    }
}
