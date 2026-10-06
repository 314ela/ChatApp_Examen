package com.examen.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Pantalla de Login para validar al usuario con el admin y la clave
public class MainActivity extends AppCompatActivity {

    private static final String USUARIO_VALIDO = "admin";
    private static final String CLAVE_VALIDA = "123456";

    private EditText etUsuario;
    private EditText etClave;
    private ImageView ivVerClave;
    private Button btnIniciarSesion;
    private Button btnCrearCuenta;
    private TextView tvOlvidoClave;

    private boolean claveVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        ivVerClave = findViewById(R.id.ivVerClave);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        tvOlvidoClave = findViewById(R.id.tvOlvidoClave);

        // Iniciar sesión y valida usuario y clave
        btnIniciarSesion.setOnClickListener(v -> validarLogin());

        // Crear una cuenta que luego manda al registro
        btnCrearCuenta.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegistroActivity.class)));

        // Ojito para mostrar y ocultar la contraseña
        ivVerClave.setOnClickListener(v -> mostrarOcultarClave());

        tvOlvidoClave.setOnClickListener(v ->
                Toast.makeText(this, R.string.login_olvido_clave, Toast.LENGTH_SHORT).show());
    }

    private void validarLogin() {
        String usuario = etUsuario.getText().toString().trim();
        String clave = etClave.getText().toString();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, R.string.login_error_vacio, Toast.LENGTH_SHORT).show();
            return;
        }

        if (usuario.equals(USUARIO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
            Toast.makeText(this, R.string.login_correcto, Toast.LENGTH_SHORT).show();
            // Si todo es correcto, manda al listado de usuarios
            startActivity(new Intent(MainActivity.this, UsuariosActivity.class));
            finish();
        } else {
            Toast.makeText(this, R.string.login_error_credenciales, Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarOcultarClave() {
        claveVisible = !claveVisible;
        if (claveVisible) {
            etClave.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            etClave.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        etClave.setSelection(etClave.getText().length());
    }
}
