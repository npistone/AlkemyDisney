package com.alkemy.disney.dto;

import java.util.ArrayList;
import java.util.List;

public class PeliculaDtoFull {

    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private Integer calificacion;
    private List<PersonajeDtoFull> personajes = new ArrayList<>();
    private Long genero;



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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<PersonajeDtoFull> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeDtoFull> personajes) {
        this.personajes = personajes;
    }

    public Long getGenero() {
        return genero;
    }

    public void setGenero(Long genero) {
        this.genero = genero;
    }




}
