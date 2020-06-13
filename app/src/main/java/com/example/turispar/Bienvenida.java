package com.example.turispar;

import androidx.appcompat.app.AppCompatActivity;

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

        usuario = findViewById(R.id.txtbienvenidausuario);

        String dato=  getIntent().getStringExtra("usuario");

        usuario.setText("Bienvenido  "+ dato);
    }

    public void lugares(View view){
        Intent intent = new Intent(Bienvenida.this, Categorias.class);
        startActivity(intent);
        finish();


    }
}
