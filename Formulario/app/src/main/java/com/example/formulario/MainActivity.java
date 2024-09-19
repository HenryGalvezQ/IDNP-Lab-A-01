package com.example.formulario;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.formulario.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String FILE_NAME = "registros.txt";
    private static final String TAG = "ConferenceRegistration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnRegistrar.setOnClickListener(v -> registrarDatos());
        binding.btnMostrar.setOnClickListener(v -> mostrarDatos());
    }

    private void registrarDatos() {
        String nombres = binding.edtNombres.getText().toString();
        String apellidos = binding.edtApellidos.getText().toString();
        String correo = binding.edtCorreo.getText().toString();
        String telefono = binding.edtTelefono.getText().toString();
        String grupoSanguineo = binding.edtGrupoSanguineo.getText().toString();
        String dni = binding.edtDni.getText().toString();
        String edad = binding.edtEdad.getText().toString();
        String direccion = binding.edtDireccion.getText().toString();

        String datos = nombres + "," + apellidos + "," + correo + "," + telefono + "," +
                grupoSanguineo + "," + dni + "," + edad + "," + direccion + "\n";

        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(datos);
            osw.close();

            Toast.makeText(this, "Datos registrados con éxito", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al registrar datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDatos() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String linea;

            while ((linea = br.readLine()) != null) {
                Log.d(TAG, "Registro: " + linea);
            }

            br.close();
            Toast.makeText(this, "Datos mostrados en la consola", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al leer datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        binding.edtNombres.setText("");
        binding.edtApellidos.setText("");
        binding.edtCorreo.setText("");
        binding.edtTelefono.setText("");
        binding.edtGrupoSanguineo.setText("");
        binding.edtDni.setText("");
        binding.edtEdad.setText("");
        binding.edtDireccion.setText("");
    }
}