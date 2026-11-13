package com.example.ipeliculasjaime;

import java.io.Serializable;

public class Pelicula implements Serializable {
    private int id;
    private String titulo;
    private String genero;
    private double valoracion;
    private String descripcionCorta;
    private String sinopsis;
    private String director;
    private String fechaEstreno;
    private  int imageResId;

    public Pelicula(int id, String titulo, String genero, double valoracion, String descripcionCorta, String sinopsis, String director, String fechaEstreno, int imageResId) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.valoracion = valoracion;
        this.descripcionCorta = descripcionCorta;
        this.sinopsis = sinopsis;
        this.director = director;
        this.fechaEstreno = fechaEstreno;
        this.imageResId = imageResId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
