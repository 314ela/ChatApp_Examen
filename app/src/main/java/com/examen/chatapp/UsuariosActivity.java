package com.examen.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.examen.chatapp.adapters.UsuarioAdapter;
import com.examen.chatapp.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

// Pantalla con el listado de 20 usuarios el CustomListView
public class UsuariosActivity extends AppCompatActivity {

    private ListView lvUsuarios;
    private ImageView ivMenu;
    private ImageView ivBuscar;
    private View menuUsuarios;
    private View menuChat;
    private View menuPerfil;

    private final List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        lvUsuarios = findViewById(R.id.lvUsuarios);
        ivMenu = findViewById(R.id.ivMenu);
        ivBuscar = findViewById(R.id.ivBuscar);
        menuUsuarios = findViewById(R.id.menuUsuarios);
        menuChat = findViewById(R.id.menuChat);
        menuPerfil = findViewById(R.id.menuPerfil);

        llenarUsuarios();
        lvUsuarios.setAdapter(new UsuarioAdapter(this, listaUsuarios));

        // Al dar click sobre un usuario te manda al chat
        lvUsuarios.setOnItemClickListener((padre, vista, posicion, id) -> {
            Usuario elegido = listaUsuarios.get(posicion);
            Intent intent = new Intent(UsuariosActivity.this, ChatActivity.class);
            intent.putExtra(ChatActivity.EXTRA_NOMBRE, elegido.getNombre());
            intent.putExtra(ChatActivity.EXTRA_COLOR, elegido.getColor());
            startActivity(intent);
        });

        ivMenu.setOnClickListener(v -> mostrarMensaje(getString(R.string.boton_menu)));
        ivBuscar.setOnClickListener(v -> mostrarMensaje(getString(R.string.boton_buscar)));
        menuUsuarios.setOnClickListener(v -> mostrarMensaje(getString(R.string.menu_usuarios)));
        menuChat.setOnClickListener(v -> mostrarMensaje(getString(R.string.menu_chat)));
        menuPerfil.setOnClickListener(v -> mostrarMensaje(getString(R.string.menu_perfil)));
    }

    private void mostrarMensaje(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    // 20 usuarios
    private void llenarUsuarios() {
        String[] nombres = {
                "Maria Meow", "Dani DBlover", "Genshin Fer", "Adoflo", "Lupita",
                "Juan Pablo", "Gabyy", "Rafaeeel", "Farith", "Andrés",
                "Allison", "Mateo", "Josué", "Nahomi", "Yoongi",
                "Profe Serge", "Blanca Reyes", "Octavio Gutierrez", "Lydia Martinez", "Elsa Martinez"
        };
        String[] alias = {
                "maria123", "dani_23", "feer_22", "adolfo99", "lupita_t",
                "jp_123", "gabyl", "rafa_zetsu", "fff_r", "andrew_c",
                "allison_flx", "mateo18", "josuee_1", "mimi_v", "suga93",
                "sergeofPOD", "blank27", "jangtz", "lydia_mtz", "elss4"
        };
        int[] colores = {
                getColor(R.color.azul_principal), getColor(R.color.avatar_morado),
                getColor(R.color.avatar_verde), getColor(R.color.avatar_naranja),
                getColor(R.color.avatar_rojo)
        };

        for (int i = 0; i < nombres.length; i++) {
            listaUsuarios.add(new Usuario(nombres[i], alias[i], colores[i % colores.length]));
        }
    }
}
