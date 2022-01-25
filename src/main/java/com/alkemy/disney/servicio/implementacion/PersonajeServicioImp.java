package com.alkemy.disney.servicio.implementacion;

import com.alkemy.disney.dto.PersonajeDtoFull;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.ParamNotFound;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repositorios.PersonajeRepositorio;
import com.alkemy.disney.servicio.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonajeServicioImp implements PersonajeServicio {
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    public PersonajeDtoFull guardar (PersonajeDtoFull dtoFull){

        Personaje personaje = personajeMapper.personajeDtoFull2Personaje(dtoFull);

        Personaje personajeGuardado = personajeRepositorio.save(personaje);

        PersonajeDtoFull guardado = personajeMapper.personaje2DtoFull(personajeGuardado);
        return guardado;
    }

    public PersonajeDtoFull getDetailsById(Long id){
        Optional<Personaje> optPersonaje= personajeRepositorio.findById(id);
        if (!optPersonaje.isPresent()){

            throw new ParamNotFound("Personaje id no encontrado");
        }
        Personaje personaje = optPersonaje.get();
        PersonajeDtoFull dto =personajeMapper.personaje2DtoFull(personaje);

        return dto;

    }


}
