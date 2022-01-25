package com.alkemy.disney.dto;

import com.alkemy.disney.entidades.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class GeneroDTO {

    private Long id;
    private String imagen;
    private String nombre;

    private List<Pelicula> peliculas ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }


}
