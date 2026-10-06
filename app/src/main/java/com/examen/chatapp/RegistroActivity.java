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

// Pantalla de Registro
public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etCorreo;
    private EditText etUsuarioRegistro;
    private EditText etClaveRegistro;
    private EditText etConfirmarClave;
    private ImageView ivVerClaveRegistro;
    private ImageView ivVerConfirmar;
    private ImageView ivRegresar;
    private Button btnRegistrarse;
    private TextView tvYaTienesCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etUsuarioRegistro = findViewById(R.id.etUsuarioRegistro);
        etClaveRegistro = findViewById(R.id.etClaveRegistro);
        etConfirmarClave = findViewById(R.id.etConfirmarClave);
        ivVerClaveRegistro = findViewById(R.id.ivVerClaveRegistro);
        ivVerConfirmar = findViewById(R.id.ivVerConfirmar);
        ivRegresar = findViewById(R.id.ivRegresar);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        tvYaTienesCuenta = findViewById(R.id.tvYaTienesCuenta);

        btnRegistrarse.setOnClickListener(v -> validarRegistro());

        // Sale lo de "¿Ya tienes una cuenta?" y la flecha mandan al login
        tvYaTienesCuenta.setOnClickListener(v -> irAlLogin());
        ivRegresar.setOnClickListener(v -> irAlLogin());

        ivVerClaveRegistro.setOnClickListener(v -> alternarVisibilidad(etClaveRegistro));
        ivVerConfirmar.setOnClickListener(v -> alternarVisibilidad(etConfirmarClave));
    }

    private void validarRegistro() {
        String nombre = etNombre.getText().toString().trim();
        String clave = etClaveRegistro.getText().toString();
        String confirmar = etConfirmarClave.getText().toString();

        // Nombre con mínimo tres caracteres
        if (nombre.length() < 3) {
            etNombre.setError(getString(R.string.registro_error_nombre));
            Toast.makeText(this, R.string.registro_error_nombre, Toast.LENGTH_SHORT).show();
            return;
        }

        if (clave.isEmpty()) {
            Toast.makeText(this, R.string.registro_error_clave_vacia, Toast.LENGTH_SHORT).show();
            return;
        }

        // Las contraseñas deben coincidir
        if (!clave.equals(confirmar)) {
            etConfirmarClave.setError(getString(R.string.registro_error_claves));
            Toast.makeText(this, R.string.registro_error_claves, Toast.LENGTH_SHORT).show();
            return;
        }

        //Si todo está bien manda al login
        Toast.makeText(this, R.string.registro_correcto, Toast.LENGTH_SHORT).show();
        irAlLogin();
    }

    private void irAlLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void alternarVisibilidad(EditText campo) {
        int variacion = campo.getInputType() & InputType.TYPE_MASK_VARIATION;
        boolean estabaOculta = (variacion == InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if (estabaOculta) {
            campo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            campo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        campo.setSelection(campo.getText().length());
    }
}
