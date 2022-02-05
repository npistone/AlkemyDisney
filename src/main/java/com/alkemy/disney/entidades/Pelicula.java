package com.alkemy.disney.entidades;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table (name="peliculas")
@SQLDelete(sql = "UPDATE peliculas SET deleted = true WHERE id=?")
@Where(clause="deleted=false")

public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;

    @Column(name="fecha_creacion")
    @DateTimeFormat(pattern ="yyyy/MM/dd")
    private LocalDate fechaCreacion;


    private Integer calificacion;


    @ManyToMany(

            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(

            name="personajes_xpelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name ="personaje_id"))
    private List<Personaje> personajes = new ArrayList<>();



    @ManyToOne(fetch = FetchType.LAZY, cascade ={
            CascadeType.PERSIST,
            CascadeType.MERGE } )
    @JoinColumn(name = "genero_id", insertable = false, updatable = false )
    private Genero genero;


    @Column(name ="genero_id", nullable = false)
    private Long generoId;

    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {

        if (o ==null)return false;
        if (getClass() != o.getClass()) return false;
        if (!(o instanceof Pelicula)) return false;
        final Pelicula pelicula = (Pelicula) o;
        return pelicula.id==this.id;
       }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImagen(), getTitulo());
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public void agregarPje(Personaje pje){
        this.personajes.add(pje);
    }
    public void quitarPje(Personaje pje){
        this.personajes.remove(pje);
    }
}
