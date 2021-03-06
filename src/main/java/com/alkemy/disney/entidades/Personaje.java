package com.alkemy.disney.entidades;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personajes")
@SQLDelete(sql = "UPDATE personajes SET deleted = true WHERE id=?")
@Where(clause="deleted=false")

public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Long peso;

    private String historia;

    @ManyToMany(mappedBy="personajes", cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {

        if (o ==null)return false;
        if (getClass() != o.getClass()) return false;
        if (!(o instanceof Personaje)) return false;
        final Personaje personaje = (Personaje) o;
        return personaje.id==this.id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

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

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
