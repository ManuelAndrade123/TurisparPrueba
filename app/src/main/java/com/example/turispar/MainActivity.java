package com.example.turispar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.turispar.BaseDeDatos.Conexion;

public class MainActivity extends AppCompatActivity {
 private    EditText usuario, contraseña;
 private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuariotxtlogin);
        contraseña = findViewById(R.id.contraseñatxtlogin);
        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },5000);
    }

    private void init() {

        this.progressDialog= new ProgressDialog(this);
    }

    private void showDialog(){

        progressDialog.setCancelable(false);
        progressDialog.setTitle("Iniciando Sesion");
        progressDialog.setMessage("Por favor, espere un momento");
        progressDialog.show();

    }



    public void registrar(View view) {

        Intent intent = new Intent(MainActivity.this, RegistroUsuario.class);
        startActivity(intent);
        finish();

    }
    public  void invitado(View view){

        Intent intent = new Intent(MainActivity.this, Bienvenida.class);
        String invitado="invitado";
        intent.putExtra("usuario",invitado);

        startActivity(intent);
        finish();

    }

    public void iniciosesion(View view) {

        Conexion admin = new Conexion(this, "datos", null, 1);
        final SQLiteDatabase acceso = admin.getWritableDatabase();

        String temp_usuario = usuario.getText().toString();
        String temp_contraseña = contraseña.getText().toString();

        if (temp_usuario.isEmpty() || temp_contraseña.isEmpty()) {

            Toast.makeText(this, "llene todos los campos", Toast.LENGTH_SHORT).show();

        } else {

        Cursor fila = acceso.rawQuery
                ("select * from acceso where usuario= '" + temp_usuario + "' and contraseña= '" + temp_contraseña+ "'", null);
        if (fila.moveToFirst()) {

            Intent intent = new Intent(MainActivity.this, Bienvenida.class);
            Toast.makeText(getApplicationContext(), " datos correctos", Toast.LENGTH_SHORT).show();

            intent.putExtra("usuario",temp_usuario);

            startActivity(intent);
            showDialog();
            finish();


        }else{

            Toast.makeText(this, "usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();


        }


    }

    }
}