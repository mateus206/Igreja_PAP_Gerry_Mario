package com.example.igrejas;

// Comentários adicionados como aluno para explicar melhor o código.

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.User;

import java.util.ArrayList;

// este adapter mostra a informação do utilizador na lista
public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.ViewHolder> {

    ArrayList<User> listaUser;

    public UserAdapter(ArrayList<User> listaUser) {
        this.listaUser = listaUser;
    }

    @NonNull
    @Override
    // aqui é criado o layout de cada item da lista
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_user, parent, false);

        return  new ViewHolder(v);
    }

    @Override
    // aqui coloco os dados dentro de cada item da lista
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = this.listaUser.get(position);

        holder.tvNomeUser.setText(user.getNome());
        holder.tvEmailUser.setText(user.getEmail());
    }

    @Override
    // devolve quantos elementos existem para o RecyclerView mostrar
    public int getItemCount() { return this.listaUser.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeUser;
        TextView tvEmailUser;

        ViewHolder(View itemView) {
            super(itemView);

            tvNomeUser = itemView.findViewById(R.id.tvNomeUser);
            tvEmailUser = itemView.findViewById(R.id.tvEmailUser);
        }

    }
}

