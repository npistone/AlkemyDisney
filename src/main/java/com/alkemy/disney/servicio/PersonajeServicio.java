package com.alkemy.disney.servicio;

import com.alkemy.disney.dto.PersonajeDtoBasic;
import com.alkemy.disney.dto.PersonajeDtoFull;

import java.util.List;

public interface PersonajeServicio {

    PersonajeDtoFull guardar(PersonajeDtoFull dtoFull);

    PersonajeDtoFull personajeDetalleId(Long id);

    PersonajeDtoFull editar (Long id, PersonajeDtoFull dtoFull);

    List<PersonajeDtoFull> busquedaXparametro(String nombre, Integer edad,Long peso, Long id,
                                              List<Long> peliculas, String orden);

    void delete(Long id);

    List<PersonajeDtoBasic> personajes();

}
