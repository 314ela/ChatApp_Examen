package com.examen.chatapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.examen.chatapp.adapters.MensajeAdapter;
import com.examen.chatapp.modelos.Mensaje;

import java.util.ArrayList;
import java.util.List;

// Pantalla del chat con los 20 mensajes
public class ChatActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "nombre_contacto";
    public static final String EXTRA_COLOR = "color_contacto";

    private ListView lvMensajes;
    private TextView tvNombreChat;
    private TextView tvInicialChat;
    private ImageView ivRegresarChat;
    private ImageView ivEnviar;
    private EditText etMensaje;

    private final List<Mensaje> listaMensajes = new ArrayList<>();
    private MensajeAdapter adaptadorMensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        lvMensajes = findViewById(R.id.lvMensajes);
        tvNombreChat = findViewById(R.id.tvNombreChat);
        tvInicialChat = findViewById(R.id.tvInicialChat);
        ivRegresarChat = findViewById(R.id.ivRegresarChat);
        ivEnviar = findViewById(R.id.ivEnviar);
        etMensaje = findViewById(R.id.etMensaje);

        // Datos del usuario elegido en el listado
        String nombre = getIntent().getStringExtra(EXTRA_NOMBRE);
        if (nombre == null) {
            nombre = "";
        }
        tvNombreChat.setText(nombre);
        if (!nombre.isEmpty()) {
            tvInicialChat.setText(nombre.substring(0, 1));
        }

        llenarMensajes();
        adaptadorMensajes = new MensajeAdapter(this, listaMensajes);
        lvMensajes.setAdapter(adaptadorMensajes);
        lvMensajes.setSelection(listaMensajes.size() - 1);

        ivRegresarChat.setOnClickListener(v -> finish());
        ivEnviar.setOnClickListener(v -> enviarMensaje());
    }

    private void enviarMensaje() {
        String texto = etMensaje.getText().toString().trim();
        if (texto.isEmpty()) {
            Toast.makeText(this, R.string.chat_mensaje_vacio, Toast.LENGTH_SHORT).show();
            return;
        }
        listaMensajes.add(new Mensaje(texto, "09:40", true));
        adaptadorMensajes.notifyDataSetChanged();
        lvMensajes.setSelection(listaMensajes.size() - 1);
        etMensaje.setText("");
    }

    /** Los 20 */
    private void llenarMensajes() {
        String[] textos = {
                "Hola, ¿cómo estás?",
                "Hola! Muy bien, ¿y tú?",
                "Todo bien, ¿quieres vernos más tarde para trabajar en lo de TT?",
                "Sí me parece bien. ¿A qué hora?",
                "¿Te parece a las 3:00?",
                "Perfecto, nos vemos entonces",
                "¿Dónde nos vemos?",
                "En la cafetería de siempre, en el Hola café",
                "Está bien, llevo mi laptop",
                "Yo llevo ls libreta con las anotaciones de las reuniones",
                "¿Ya terminaste la tarea de móviles?",
                "Casi, me falta el chat.",
                "Yo ya hice el login y el registro.",
                "Qué bien, luego me ayudas",
                "Sisisi",
                "Ya comiste algo? o vamos a pedir también comida",
                "Si hay que pedir comida, y yo invito el café",
                "Y yo pago las galletas",
                "Okaaay, nos vemos a las 3:00",
                "Sip, byee :)"
        };
        String[] horas = {
                "09:30", "09:31", "09:32", "09:33", "09:34",
                "09:35", "09:36", "09:37", "09:38", "09:39",
                "09:40", "09:41", "09:42", "09:43", "09:44",
                "09:45", "09:46", "09:47", "09:48", "09:49"
        };

        for (int i = 0; i < textos.length; i++) {
            // cuadno sea par el de la derecha es para yo
            // cuando sea impar el de la izquierda es el contacto
            boolean esMio = (i % 2 == 0);
            listaMensajes.add(new Mensaje(textos[i], horas[i], esMio));
        }
    }
}
