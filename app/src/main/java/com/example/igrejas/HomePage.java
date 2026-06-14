package com.example.igrejas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.igrejas.models.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    CardView cardEventos;
    CardView card_pedido_oracao;
    CardView cardAcaoSoild;
    CardView cardApoio;
    CardView cardUpdatePassword;
    CardView cardEditProfile;
    ImageButton menuBtn;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ArrayList<User> listaUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cardEventos = findViewById(R.id.cardEventos);
        card_pedido_oracao = findViewById(R.id.card_pedido_oracao);
        cardAcaoSoild = findViewById(R.id.cardAcoaSolid);
        cardApoio = findViewById(R.id.cardApoio);
        cardUpdatePassword = findViewById(R.id.cardUpdatePassword);
        cardEditProfile = findViewById(R.id.cardEditProfile);
        menuBtn = findViewById(R.id.menuBtn);
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navigationView);

        View headerView = this.navigationView.getHeaderView(0);
        RecyclerView recyclerView = headerView.findViewById(R.id.recyclerViewUser);

        this.buildUser();

        UserAdapter userAdapter = new UserAdapter(listaUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        cardEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Evento.class);
                startActivity(intent);
            }
        });

        card_pedido_oracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Formulario_pedido_oracaoActivity.class);
                startActivity(intent);
            }
        });

        cardAcaoSoild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, AcaoSolidaria.class);
                startActivity(intent);
            }
        });

        cardApoio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ApoioSocial.class);
                startActivity(intent);
            }
        });


        cardEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        cardUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, UpdatePasswordActivity.class);
                startActivity(intent);
            }
        });

        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_eventos) {
                    startActivity(new Intent(HomePage.this, Evento.class));
                } else if (id == R.id.nav_pedir_oracao) {
                    startActivity(new Intent(HomePage.this, Formulario_pedido_oracaoActivity.class));
                } else if (id == R.id.nav_acao_solidaria) {
                    startActivity(new Intent(HomePage.this, AcaoSolidaria.class));
                } else if (id == R.id.nav_apoio_social) {
                    startActivity(new Intent(HomePage.this, ApoioSocial.class));
                } else if (id == R.id.nav_edit_profile) {
                    startActivity(new Intent(HomePage.this, EditProfileActivity.class));
                } else if (id == R.id.nav_update_password) {
                    startActivity(new Intent(HomePage.this, UpdatePasswordActivity.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void buildUser() {
        SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);

        int id = prefs.getInt("id", 0);
        boolean isAdmin = prefs.getBoolean("is_admin", false);
        String nome = prefs.getString("nome", "Utilizador");
        String telefone = prefs.getString("telefone", "");
        String email = prefs.getString("email", "");
        String dataRegistro = prefs.getString("data_registro", "");
        String estado = prefs.getString("estado", "");
        String password = prefs.getString("password", "");
        boolean isVerified = prefs.getBoolean("is_verified", false);

        User u1 = new User(id, isAdmin, nome, telefone, email, dataRegistro, estado, password, isVerified);

        this.listaUser = new ArrayList<>();
        this.listaUser.add(u1);
    }
}
