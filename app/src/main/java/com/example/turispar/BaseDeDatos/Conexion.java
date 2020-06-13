package com.example.turispar.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion extends SQLiteOpenHelper {


    public Conexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase datos) {


        datos.execSQL(Utilidades.CREAR_TABLA_LUGAR);

        datos.execSQL(Utilidades.CREAR_TABLA_CATEGORIA);
        datos.execSQL(Utilidades.CREAR_TABLA_ACCESO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase datos, int i, int i1) {

        datos.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_ACCESO);
        datos.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_CATEGORIA);
        datos.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_LUGAR);

    }
}
