package com.example.igrejas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.Notificacao;

import java.util.ArrayList;

public class NotificacoesAdapter extends RecyclerView.Adapter<NotificacoesAdapter.ViewHolder> {

    private final ArrayList<Notificacao> notificacoes;

    public NotificacoesAdapter(ArrayList<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notificacao notificacao = notificacoes.get(position);
        holder.txtTitulo.setText(notificacao.getTitulo());
        holder.txtMensagem.setText(notificacao.getMensagem());
        holder.txtTipo.setText(notificacao.getTipo() == null ? "Geral" : notificacao.getTipo());
        holder.txtEstado.setText(notificacao.getLida() == 1 ? "Lida" : "Nova");
        holder.txtData.setText(notificacao.getDataCriacao() == null ? "" : notificacao.getDataCriacao());
    }

    @Override
    public int getItemCount() {
        return notificacoes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtMensagem, txtTipo, txtEstado, txtData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloNotificacao);
            txtMensagem = itemView.findViewById(R.id.txtMensagemNotificacao);
            txtTipo = itemView.findViewById(R.id.txtTipoNotificacao);
            txtEstado = itemView.findViewById(R.id.txtEstadoNotificacao);
            txtData = itemView.findViewById(R.id.txtDataNotificacao);
        }
    }
}
