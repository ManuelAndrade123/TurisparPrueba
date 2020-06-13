package com.example.turispar.Entidades;

public class Lugar {

    private String titulo;
    private String lugar;
    private String descripcion;
    private int foto;
    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Lugar(String titulo, String lugar, String descripcion, int foto) {
        this.titulo = titulo;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.foto = foto;
        this.expanded=false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
