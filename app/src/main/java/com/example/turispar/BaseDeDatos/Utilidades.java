package com.example.turispar.BaseDeDatos;

public class Utilidades {



    public static final String TABLA_LUGAR ="lugar";
    public static final String CAMPO_ID_LUGAR = "id_lugar";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_DESCRIPCION_LUGAR = "descripcion_lugar";
    public static final String CAMPO_UBICACION = "ubicacion";
    public static final String CAMPO_USUARIO_LUGAR = "usuario";
    public static final String CAMPO_IMAGEN_LUGAR = "imagen";
    public static final String CREAR_TABLA_LUGAR = "CREATE TABLE " +TABLA_LUGAR+"(" +CAMPO_ID_LUGAR +" INTEGER PRIMARY KEY AUTOINCREMENT," +CAMPO_TITULO+" TEXT," +CAMPO_DESCRIPCION_LUGAR+" TEXT,"+CAMPO_UBICACION+" TEXT,"+CAMPO_USUARIO_LUGAR+" TEXT,"+ CAMPO_IMAGEN_LUGAR+" INTEGER)";





    //*********************tabla para el acceso al sistema*************************************

    public static final String TABLA_ACCESO ="acceso";
    public static final String CAMPO_ID_USUARIO = "id_usuario";
    public static final String CAMPO_USUARIO = "usuario";
    public static final String CAMPO_CONTRASEÑA = "contraseña";
    public static final String CREAR_TABLA_ACCESO = "CREATE TABLE " +TABLA_ACCESO+"(" +CAMPO_ID_USUARIO +" INTEGER PRIMARY KEY AUTOINCREMENT," +CAMPO_USUARIO+" TEXT," +CAMPO_CONTRASEÑA+" TEXT)";
 //   public static final String CREAR_TABLA_VENTA = "CREATE TABLE " +TABLA_VENTA+"(" +CAMPO_CODIGO_VENTA +" INTEGER PRIMARY KEY AUTOINCREMENT," +CAMPO_FECHA+" TEXT," +CAMPO_ESTADO+     +" REAL,"+" FOREIGN KEY ("+CAMPO_CODIGO_VENTA+") REFERENCES "+ TABLA_VENTA_DETALLE+ "("+CAMPO_CODIGO_VENTAFK+"))";


}
