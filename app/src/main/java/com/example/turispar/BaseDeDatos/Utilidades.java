package com.example.turispar.BaseDeDatos;

public class Utilidades {



    public static final String TABLA_LUGAR ="lugar";
    public static final String CAMPO_ID_LUGAR = "id_lugar";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_DESCRIPCION_LUGAR = "descripcion_lugar";
    public static final String CAMPO_UBICACION = "ubicacion";
    public static final String CAMPO_CATEGORIAFK = "id_categoria";
    public static final String CAMPO_ID_USUARIOFK = "id_usuario";
    public static final String CAMPO_IMAGEN_LUGAR = "imagen";
    public static final String CREAR_TABLA_LUGAR = "CREATE TABLE " +TABLA_LUGAR+"(" +CAMPO_ID_LUGAR +" INTEGER PRIMARY KEY AUTOINCREMENT," +CAMPO_TITULO+" TEXT," +CAMPO_DESCRIPCION_LUGAR+" TEXT,"+CAMPO_UBICACION+" TEXT,"+CAMPO_CATEGORIAFK+" INTEGER,"+CAMPO_ID_USUARIOFK+" INTEGER,"+ CAMPO_IMAGEN_LUGAR+" BLOB)";

//*************************tabla categoria lugares**********************************

    public static final String TABLA_CATEGORIA ="categoria";
    public static final String CAMPO_ID_CATEGORIA = "id_categoria";
    public static final String CAMPO_IMAGEN_CATEGORIA = "imagen";
    public static final String CAMPO_DESCRIPCION_CATEGORIA = "descripcion_categoria";
    public static final String CREAR_TABLA_CATEGORIA = "CREATE TABLE " +TABLA_CATEGORIA+"(" +CAMPO_ID_CATEGORIA +" INTEGER PRIMARY KEY AUTOINCREMENT," +CAMPO_DESCRIPCION_CATEGORIA +" TEXT,"+CAMPO_IMAGEN_CATEGORIA+" BLOB,"+" FOREIGN KEY ("+CAMPO_ID_CATEGORIA+") REFERENCES " + TABLA_LUGAR + "("+CAMPO_CATEGORIAFK+"))";



    //*********************tabla para el acceso al sistema*************************************

    public static final String TABLA_ACCESO ="acceso";
    public static final String CAMPO_ID_USUARIO = "id_usuario";
    public static final String CAMPO_USUARIO = "usuario";
    public static final String CAMPO_CONTRASEÑA = "contraseña";
    public static final String CREAR_TABLA_ACCESO = "CREATE TABLE " +TABLA_ACCESO+"(" +CAMPO_ID_USUARIO +" INTEGER PRIMARY KEY AUTOINCREMENT," +CAMPO_USUARIO+" TEXT," +CAMPO_CONTRASEÑA+" TEXT,"+" FOREIGN KEY ("+CAMPO_ID_USUARIO+ ") REFERENCES " + TABLA_LUGAR + "("+CAMPO_ID_USUARIOFK+"))";
 //   public static final String CREAR_TABLA_VENTA = "CREATE TABLE " +TABLA_VENTA+"(" +CAMPO_CODIGO_VENTA +" INTEGER PRIMARY KEY AUTOINCREMENT," +CAMPO_FECHA+" TEXT," +CAMPO_ESTADO+     +" REAL,"+" FOREIGN KEY ("+CAMPO_CODIGO_VENTA+") REFERENCES "+ TABLA_VENTA_DETALLE+ "("+CAMPO_CODIGO_VENTAFK+"))";


}
