package com.example.turispar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.turispar.BaseDeDatos.Conexion;
import com.example.turispar.Entidades.Lugar;
import com.example.turispar.Entidades.favoritos;
import com.example.turispar.adaptadores.AdaptadorLugares;

import java.util.ArrayList;

public class Favoritos extends AppCompatActivity {
    ArrayList<Lugar> listafav;
    RecyclerView recyclerView;
    Conexion conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        conn = new Conexion(this, "datos", null, 1);
        recyclerView = findViewById(R.id.recyclerFavoritos);
            ListarFavoritos();
    }


    public void ListarFavoritos(){
        String dato=  getIntent().getStringExtra("usuario");
        SQLiteDatabase db = conn.getReadableDatabase();

        listafav=new ArrayList<Lugar>();
        Lugar Favoritos=null;
        Cursor cursor =db.rawQuery("SELECT * from lugar where usuario = '"+ dato +"'",null);
        while(cursor.moveToNext()){
            Favoritos = new Lugar();

            Favoritos.setTitulo(cursor.getString(1));
            Favoritos.setDescripcion(cursor.getString(2));
            Favoritos.setLugar(cursor.getString(3));
            Favoritos.setFoto(cursor.getInt(5));
            listafav.add(Favoritos);
            db.close();

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorLugares adapter = new AdaptadorLugares(listafav);

        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(Favoritos.this);
                String titulodialo = listafav.get(recyclerView.getChildAdapterPosition(view)).getTitulo();
                builder.setTitle(titulodialo);
                builder.setMessage("¿Esta seguro que quiere eliminar este sitio?").
                        setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String dato=  getIntent().getStringExtra("usuario");




                                    SQLiteDatabase db = conn.getReadableDatabase();

                                    String titulo = listafav.get(recyclerView.getChildAdapterPosition(view)).getTitulo();

                                    db.execSQL("DELETE FROM lugar where usuario= '" + dato + "' and titulo= '" + titulo + "'");
                                    db.close();
                                    ListarFavoritos();
                                    Toast.makeText(getApplicationContext(), "Sitio eliminado  correctamente", Toast.LENGTH_SHORT).show();


                            }
                        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });
        recyclerView.setAdapter(adapter);



    }

}
