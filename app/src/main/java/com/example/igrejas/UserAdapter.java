package com.example.igrejas;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.User;

import java.util.ArrayList;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.ViewHolder> {

    ArrayList<User> infoUser;

    public UserAdapter(ArrayList<User> infoUser) {
        this.infoUser = infoUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = this.infoUser.get(position);

        holder.tvNomeUser.setText(user.getNome());
        holder.tvEmailUser.setText(user.getEmail());
    }

    @Override
    public int getItemCount() { return this.infoUser.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeUser;
        TextView tvEmailUser;

        ViewHolder(View itemView) {
            super(itemView);

            //tvNomeUser = itemView.findViewById(R.id.tvNomeUser);
            //tvEmailUser = itemView.findViewById(R.id.tvEmailUser);
        }

    }
}

