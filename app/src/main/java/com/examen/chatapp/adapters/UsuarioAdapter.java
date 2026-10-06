package com.examen.chatapp.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.examen.chatapp.R;
import com.examen.chatapp.modelos.Usuario;

import java.util.List;

public class UsuarioAdapter extends BaseAdapter {

    private final Context contexto;
    private final List<Usuario> listaUsuarios;

    public UsuarioAdapter(Context contexto, List<Usuario> listaUsuarios) {
        this.contexto = contexto;
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public int getCount() { return listaUsuarios.size(); }

    @Override
    public Object getItem(int posicion) { return listaUsuarios.get(posicion); }

    @Override
    public long getItemId(int posicion) { return posicion; }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        if (vista == null) {
            vista = LayoutInflater.from(contexto).inflate(R.layout.item_usuario, padre, false);
        }

        Usuario usuario = listaUsuarios.get(posicion);

        TextView tvInicial = vista.findViewById(R.id.tvInicial);
        TextView tvNombre = vista.findViewById(R.id.tvNombreUsuario);
        TextView tvAlias = vista.findViewById(R.id.tvAliasUsuario);

        tvInicial.setText(usuario.getNombre().substring(0, 1));
        // Se pinta el círculo con el color del usuario
        GradientDrawable circulo = (GradientDrawable) tvInicial.getBackground().mutate();
        circulo.setColor(usuario.getColor());

        tvNombre.setText(usuario.getNombre());
        tvAlias.setText(usuario.getAlias());

        return vista;
    }
}
