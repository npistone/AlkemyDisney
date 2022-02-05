package com.alkemy.disney.dto;

import java.util.List;

public class PersonajeDtoFilter {

    private String nombre;
    private Integer edad;
    private Long peso;
    private Long id;
    private String orden;
    private List<Long> peliculas;


    public PersonajeDtoFilter(String nombre, Integer edad, Long peso, Long id, List<Long> peliculas, String orden ) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.id = id;
        this.peliculas = peliculas;
        this.orden = orden;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public List<Long> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Long> peliculas) {
        this.peliculas = peliculas;
    }

    public boolean isASC(){
        return this.orden.compareToIgnoreCase("ASC")==0;
    }

    public boolean isDESC() {
        return this.orden.compareToIgnoreCase("DESC") == 0;
    }

    }
