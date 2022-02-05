package com.alkemy.disney.dto;

public class PeliculaDtoFilter {
    private String nombre;
    private String genero;
    private String orden;


    public PeliculaDtoFilter(String nombre, String genero, String orden) {
        this.nombre = nombre;
        this.genero = genero;
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public boolean isASC(){
        return this.orden.compareToIgnoreCase("ASC")==0;
    }

    public boolean isDESC() {
        return this.orden.compareToIgnoreCase("DESC") == 0;
    }
}
