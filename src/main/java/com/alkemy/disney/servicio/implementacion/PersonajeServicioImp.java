package com.alkemy.disney.servicio.implementacion;

import com.alkemy.disney.dto.PersonajeDtoBasic;
import com.alkemy.disney.dto.PersonajeDtoFilter;
import com.alkemy.disney.dto.PersonajeDtoFull;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.ParamNotFound;
import com.alkemy.disney.mapper.PeliculaMapper;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repositorios.PersonajeRepositorio;
import com.alkemy.disney.repositorios.especificaciones.PersonajeEspecificacion;
import com.alkemy.disney.servicio.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServicioImp implements PersonajeServicio {
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PersonajeEspecificacion personajeEspecificacion;

    public PersonajeDtoFull guardar (PersonajeDtoFull dtoFull){

        Personaje personaje = personajeMapper.personajeDtoFull2Personaje(dtoFull);

        Personaje personajeGuardado = personajeRepositorio.save(personaje);

        PersonajeDtoFull guardado = personajeMapper.personaje2DtoFull(personajeGuardado, false);
        return guardado;
    }



    public PersonajeDtoFull personajeDetalleId(Long id){
        Optional<Personaje> optPersonaje= personajeRepositorio.findById(id);
        if (!optPersonaje.isPresent()){

            throw new ParamNotFound("Personaje id no encontrado");
        }
        Personaje personaje = optPersonaje.get();
        PersonajeDtoFull dto =personajeMapper.personaje2DtoFull(personaje, true);

        return dto;

    }


    public PersonajeDtoFull editar(Long id, PersonajeDtoFull dtoFull) {
        Optional<Personaje> optPersonaje= personajeRepositorio.findById(id);
        if (!optPersonaje.isPresent()){

            throw new ParamNotFound("Personaje id no encontrado");
        }
        Personaje personaje = optPersonaje.get();
        personaje.setNombre(dtoFull.getNombre());
        personaje.setEdad(dtoFull.getEdad());
        personaje.setHistoria(dtoFull.getHistoria());
        personaje.setPeso(dtoFull.getPeso());
        personaje.setImagen(dtoFull.getImagen());
        personaje.setPeliculas(peliculaMapper.peliculaDtoFullList2EntList(dtoFull.getPeliculas()));


        PersonajeDtoFull dto =personajeMapper.personaje2DtoFull(personaje, false);
        return dto;


    }

    @Override
    public List<PersonajeDtoFull> busquedaXparametro(String nombre, Integer edad, Long peso,
                                                     Long id, List<Long> peliculas, String orden ) {
        PersonajeDtoFilter filtro = new PersonajeDtoFilter(nombre, edad, peso, id,peliculas, orden);
        List<Personaje> entidades = personajeRepositorio.findAll(personajeEspecificacion.getByFilters(filtro));
        List<PersonajeDtoFull> dtos = personajeMapper.personajeEntList2DtoFullList(entidades, true);

        return dtos;
    }


    public void delete(Long id) {
        personajeRepositorio.deleteById(id);

    }

    @Override
    public List<PersonajeDtoBasic> personajes() {
        List<Personaje> entidades = personajeRepositorio.findAll();
        List<PersonajeDtoBasic> dtos = new ArrayList<>();
        for (Personaje personaje : entidades) {
            dtos.add(personajeMapper.personaje2DtoBasic(personaje));

        }
        return dtos;
    }


}
