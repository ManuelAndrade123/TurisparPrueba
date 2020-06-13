package com.example.turispar.Entidades;

public class Categoria {


    private String titulo;
    private int foto;

    public Categoria(String titulo, int foto) {
        this.titulo = titulo;
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
