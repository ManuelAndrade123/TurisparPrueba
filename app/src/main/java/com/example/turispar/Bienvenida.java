package com.example.turispar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Bienvenida extends AppCompatActivity {

    private TextView usuario;
    private int []image = new int[]{R.drawable.a,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,
            R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9};








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);


        CarouselView carouselView = findViewById(R.id.carouselView);


        carouselView.setPageCount(image.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(image[position]);
            }
        });
        String dato=  getIntent().getStringExtra("usuario");
        usuario = findViewById(R.id.txtbienvenidausuario);
        usuario.setText("Bienvenido  "+ dato);
    }

    public void salir(View view){
        AlertDialog.Builder  builder = new AlertDialog.Builder(Bienvenida.this);

        builder.setTitle("Titulo");
        builder.setMessage("¿Esta seguro que quieres salir?").
                setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Bienvenida.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();


    }

    public void lugares(View view){
        Intent intent = new Intent(Bienvenida.this, Categorias.class);


        String dato=  getIntent().getStringExtra("usuario");
        intent.putExtra("usuario",dato);
        startActivity(intent);



    }
    public void favoritos(View view){
        Intent intent = new Intent(Bienvenida.this, Favoritos.class);

        String dato=  getIntent().getStringExtra("usuario");
         intent.putExtra("usuario",dato);
        startActivity(intent);

    }
}
