package com.alkemy.disney.mapper;


import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.dto.PersonajeDtoBasic;
import com.alkemy.disney.dto.PersonajeDtoFull;
import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.entidades.Personaje;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    public PersonajeDtoBasic personaje2DtoBasic(Personaje personaje){
        PersonajeDtoBasic dto = new PersonajeDtoBasic();
        dto.setId(personaje.getId());
        dto.setImagen(personaje.getImagen());
        dto.setNombre(personaje.getNombre());

        return dto;

    }

    public PersonajeDtoFull personaje2DtoFull(Personaje personaje){
        PersonajeDtoFull dto = new PersonajeDtoFull();
        dto.setId(personaje.getId());
        dto.setNombre(personaje.getNombre());
        dto.setImagen(personaje.getImagen());
        dto.setEdad(personaje.getEdad());
        dto.setHistoria(personaje.getHistoria());
        dto.setPeso(personaje.getPeso());
        dto.setPeliculas(personaje.getPeliculas());

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
        personaje.setPeliculas(dto.getPeliculas());

        return personaje;


    }



    public List<PersonajeDtoBasic> personajeEntList2DtoList(List<Personaje> entidades){
        List<PersonajeDtoBasic> dtos = new ArrayList<>();
        for (Personaje personaje : entidades) {
            dtos.add(this.personaje2DtoBasic(personaje));
        }
        return dtos;
    }



}
