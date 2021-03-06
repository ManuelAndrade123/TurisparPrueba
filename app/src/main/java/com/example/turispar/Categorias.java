package com.example.turispar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.turispar.Entidades.Categoria;
import com.example.turispar.adaptadores.AdaptadorCategoria;

import java.util.ArrayList;

public class Categorias extends AppCompatActivity {

    ArrayList<Categoria> listacategoria;
    RecyclerView recyclerView;
    String prueba;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        String datos=  getIntent().getStringExtra("usuario");
        prueba=datos;
        listacategoria= new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerCategoria);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        llenarCategoria();
        AdaptadorCategoria adaptadorCategoria = new AdaptadorCategoria(listacategoria);
        adaptadorCategoria.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String titulo =  listacategoria.get(recyclerView.getChildAdapterPosition(view)).getTitulo();
                Intent intent = new Intent(getApplicationContext(),Lugares.class);
                intent.putExtra("titulo",titulo);


                intent.putExtra("usuario",prueba);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adaptadorCategoria);

    }

    private void llenarCategoria() {

        listacategoria.add(new Categoria("MONUMENTOS",R.drawable.poporos2));
        listacategoria.add(new Categoria("SITIOS INSIGNES",R.drawable.cultura));
        listacategoria.add(new Categoria("BALNEARIOS",R.drawable.balneario));
        listacategoria.add(new Categoria("PUEBLOS",R.drawable.manaure));
        listacategoria.add(new Categoria("PARQUES",R.drawable.parques));
        listacategoria.add(new Categoria("GLORIETAS",R.drawable.pilonera));
    }
}
