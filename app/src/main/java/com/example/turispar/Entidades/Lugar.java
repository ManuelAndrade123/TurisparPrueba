package com.example.turispar.Entidades;

import java.io.Serializable;

public class Lugar implements Serializable {

    private String titulo;
    private String lugar;
    private String descripcion;
    private int foto;
    private boolean expanded;
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Lugar() {
    }

    public Lugar(String titulo, String lugar, String descripcion, int foto) {
        this.titulo = titulo;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.foto = foto;
        this.expanded=false;
    }





    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
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
