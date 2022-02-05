package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaDtoBasic;
import com.alkemy.disney.dto.PeliculaDtoFull;
import com.alkemy.disney.dto.PersonajeDtoFull;
import com.alkemy.disney.entidades.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public PeliculaDtoBasic pelicula2DtoBasic(Pelicula pelicula){
        PeliculaDtoBasic dtoFull = new PeliculaDtoBasic();
        dtoFull.setTitulo(pelicula.getTitulo());
        dtoFull.setImagen(pelicula.getImagen());
        dtoFull.setFechaCreacion(pelicula.getFechaCreacion().toString());

        return dtoFull;

    }

    public Pelicula peliculaDtoFull2Entidad(PeliculaDtoFull dto){
        Pelicula pelicula = new Pelicula();

        pelicula.setId(dto.getId());
        pelicula.setImagen(dto.getImagen());
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setFechaCreacion(
                this.string2LocalDate(dto.getFechaCreacion()));
        pelicula.setCalificacion(dto.getCalificacion());
        return pelicula;

    }

    public PeliculaDtoFull peliculaEntidad2DtoFull( Pelicula pelicula, boolean loadPersonaje){

        PeliculaDtoFull dtoFull = new PeliculaDtoFull();
        dtoFull.setId(pelicula.getId());
        dtoFull.setImagen(pelicula.getImagen());
        dtoFull.setFechaCreacion(pelicula.getFechaCreacion().toString());
        dtoFull.setCalificacion(pelicula.getCalificacion());
        if(loadPersonaje){
            List<PersonajeDtoFull> dtos = this.personajeMapper.personajeEntList2DtoFullList(pelicula.getPersonajes(), false);
            dtoFull.setPersonajes(dtos);
        }
        return dtoFull;
        }

    private LocalDate string2LocalDate (String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dia = LocalDate.parse(fecha, formatter);
        return dia;
    }

    /*Listas*/
    /*Pelicula a DtoFull*/
    public List<PeliculaDtoFull> peliculaEnt2DtoFullList(List<Pelicula> entidades, boolean loadPersonaje){

        List<PeliculaDtoFull> dtos = new ArrayList<>();
        for (Pelicula pelicula : entidades) {
            dtos.add(this.peliculaEntidad2DtoFull(pelicula,loadPersonaje));

        }
        return dtos;
    }
    /*Pelicula a DtoBasic*/
    public List<PeliculaDtoBasic> peliculaEnt2PeliculaDtoBasic(List<Pelicula> entidades){
        List<PeliculaDtoBasic> dtos = new ArrayList<>();
        for (Pelicula peli: entidades) {

            dtos.add(this.pelicula2DtoBasic(peli));
        }
        return dtos;
    }
    /*Dto Full a Pelicula*/

    public List<Pelicula> peliculaDtoFullList2EntList(List<PeliculaDtoFull> dtos){

        List<Pelicula> entidades = new ArrayList<>();
        for (PeliculaDtoFull peliculaDtoFull : dtos) {

            entidades.add(peliculaDtoFull2Entidad(peliculaDtoFull));
        }
        return entidades;
    }
}
