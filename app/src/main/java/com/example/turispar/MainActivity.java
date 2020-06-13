package com.example.turispar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.turispar.BaseDeDatos.Conexion;

public class MainActivity extends AppCompatActivity {
 private    EditText usuario, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuariotxtlogin);
        contraseña = findViewById(R.id.contraseñatxtlogin);
    }


    public void registrar(View view) {

        Intent intent = new Intent(MainActivity.this, RegistroUsuario.class);
        startActivity(intent);
        finish();

    }

    public void iniciosesion(View view) {

        Conexion admin = new Conexion(this, "datos", null, 1);
        final SQLiteDatabase acceso = admin.getWritableDatabase();
        String temp_usuario = usuario.getText().toString();
        String temp_contraseña = contraseña.getText().toString();

        Cursor fila = acceso.rawQuery
                ("select * from acceso where usuario= '" + temp_usuario + "' and contraseña= '" + temp_contraseña+ "'", null);
        if (fila.moveToFirst()) {

            Toast.makeText(this, " datos correctos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Bienvenida.class);

            intent.putExtra("usuario",temp_usuario);
            startActivity(intent);
            finish();


        }else{

            Toast.makeText(this, "usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();


        }

    }
}