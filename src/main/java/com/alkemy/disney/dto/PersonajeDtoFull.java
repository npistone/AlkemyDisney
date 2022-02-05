package com.alkemy.disney.dto;

import com.alkemy.disney.entidades.Pelicula;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

public class PersonajeDtoFull {

    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Long peso;

    private String historia;

    private List<PeliculaDtoFull> peliculas = new ArrayList<>();

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<PeliculaDtoFull> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<PeliculaDtoFull> peliculas) {
        this.peliculas = peliculas;
    }
}
