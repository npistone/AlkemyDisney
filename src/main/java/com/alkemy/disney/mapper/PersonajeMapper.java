package com.alkemy.disney.mapper;


import com.alkemy.disney.dto.*;

import com.alkemy.disney.entidades.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public PersonajeDtoBasic personaje2DtoBasic(Personaje personaje){
        PersonajeDtoBasic dto = new PersonajeDtoBasic();
        dto.setId(personaje.getId());
        dto.setImagen(personaje.getImagen());
        dto.setNombre(personaje.getNombre());
        return dto;

    }

    public PersonajeDtoFull personaje2DtoFull(Personaje personaje, boolean loadPeliculas){
        PersonajeDtoFull dto = new PersonajeDtoFull();
        dto.setId(personaje.getId());
        dto.setNombre(personaje.getNombre());
        dto.setImagen(personaje.getImagen());
        dto.setEdad(personaje.getEdad());
        dto.setHistoria(personaje.getHistoria());
        dto.setPeso(personaje.getPeso());
        if (loadPeliculas){
            List<PeliculaDtoFull> lista= this.peliculaMapper.peliculaEnt2DtoFullList(personaje.getPeliculas(), false);
            dto.setPeliculas(lista);
        }


        return dto;


    }

    public Personaje personajeDtoFull2Personaje (PersonajeDtoFull dto){
        Personaje personaje = new Personaje();
        personaje.setId(dto.getId());
        personaje.setNombre(dto.getNombre());
        personaje.setImagen(dto.getImagen());
        personaje.setEdad(dto.getEdad());
        personaje.setHistoria(dto.getHistoria());
        personaje.setPeso(dto.getPeso());


        return personaje;


    }





    public List<PersonajeDtoFull> personajeEntList2DtoFullList(List<Personaje> entidades, boolean loadPeliculas){
        List<PersonajeDtoFull> dtos = new ArrayList<>();
        for (Personaje personaje : entidades) {
            dtos.add(this.personaje2DtoFull(personaje, loadPeliculas));
        }
        return dtos;
    }
    /*Personaje en DtoBasic*/
    public List<PersonajeDtoBasic> personajeEntList2DtoBasList(List<Personaje> entidades){
        List<PersonajeDtoBasic> dtos = new ArrayList<>();
        PersonajeDtoBasic basic;
        for (Personaje personaje : entidades) {
            basic = new PersonajeDtoBasic();
            basic.setId(personaje.getId());
            basic.setNombre(personaje.getNombre());
            basic.setImagen(personaje.getImagen());
            dtos.add(basic);
        }
        return dtos;
    }

    /*DtoFull a Personaje*/

    public List<Personaje> personajeDtoFullList2PersonsajeEntList(List<PersonajeDtoFull> dtos){
        List<Personaje> personajes = new ArrayList<>();

        for (PersonajeDtoFull dtoFull : dtos) {
            personajes.add(this.personajeDtoFull2Personaje(dtoFull));
        }
        return personajes;
    }


}
