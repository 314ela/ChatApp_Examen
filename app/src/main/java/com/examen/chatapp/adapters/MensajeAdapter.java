package com.examen.chatapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.examen.chatapp.R;
import com.examen.chatapp.modelos.Mensaje;

import java.util.List;
public class MensajeAdapter extends BaseAdapter {

    private static final int TIPO_IZQUIERDA = 0;
    private static final int TIPO_DERECHA = 1;

    private final Context contexto;
    private final List<Mensaje> listaMensajes;

    public MensajeAdapter(Context contexto, List<Mensaje> listaMensajes) {
        this.contexto = contexto;
        this.listaMensajes = listaMensajes;
    }

    @Override
    public int getCount() { return listaMensajes.size(); }

    @Override
    public Object getItem(int posicion) { return listaMensajes.get(posicion); }

    @Override
    public long getItemId(int posicion) { return posicion; }

    @Override
    public int getViewTypeCount() { return 2; }

    @Override
    public int getItemViewType(int posicion) {
        return listaMensajes.get(posicion).esMio() ? TIPO_DERECHA : TIPO_IZQUIERDA;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        Mensaje mensaje = listaMensajes.get(posicion);

        if (vista == null) {
            int layout = mensaje.esMio() ? R.layout.item_mensaje_derecha : R.layout.item_mensaje_izquierda;
            vista = LayoutInflater.from(contexto).inflate(layout, padre, false);
        }

        TextView tvTexto = vista.findViewById(R.id.tvTextoMensaje);
        TextView tvHora = vista.findViewById(R.id.tvHoraMensaje);
        tvTexto.setText(mensaje.getTexto());
        tvHora.setText(mensaje.getHora());

        return vista;
    }
}
