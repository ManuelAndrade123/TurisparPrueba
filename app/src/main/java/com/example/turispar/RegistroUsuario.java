package com.example.turispar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.turispar.BaseDeDatos.Conexion;
import com.example.turispar.BaseDeDatos.Utilidades;

public class RegistroUsuario extends AppCompatActivity {

    private EditText usuario,contraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);


        usuario = findViewById(R.id.usuariotxt);
        contraseña = findViewById(R.id.contraseñatxt);

    }

    public  void Registrar(View view){

        Conexion admin = new Conexion(this, "datos", null, 1);
        final SQLiteDatabase acceso = admin.getWritableDatabase();

        final String temp_usuario = usuario.getText().toString();
        final String temp_contraseña = contraseña.getText().toString();

        ContentValues registrar = new ContentValues();

        registrar.put(Utilidades.CAMPO_USUARIO,temp_usuario);
        registrar.put(Utilidades.CAMPO_CONTRASEÑA ,temp_contraseña);

        acceso.insert(Utilidades.TABLA_ACCESO,null,registrar);
        acceso.close();

        Toast.makeText(this, "usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        Intent intent =  new Intent(RegistroUsuario.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
