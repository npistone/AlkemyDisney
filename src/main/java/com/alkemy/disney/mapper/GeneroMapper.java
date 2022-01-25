package com.alkemy.disney.mapper;


import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entidades.Genero;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component /*Enu*/
public class GeneroMapper {

    public Genero generoDTO2Entidad(GeneroDTO dto){

        Genero genero = new Genero();
        genero.setImagen(dto.getImagen());
        genero.setNombre(dto.getNombre());
        genero.setPeliculas(dto.getPeliculas());


        return genero;
        /*Devolvemos la entidad creada*/
    }

    public GeneroDTO generoEntidad2DTO(Genero genero){

        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(genero.getId());
        generoDTO.setNombre(genero.getNombre()) ;
        generoDTO.setImagen(genero.getImagen()) ;
        generoDTO.setPeliculas(genero.getPeliculas());


        return generoDTO;
        /*Devolvemos el dto*/
    }

    /*Recibo list Entidades y las transformo dentro del Foreach*/
    public List<GeneroDTO> generoEntList2DtoList(List<Genero> entidades){
        List<GeneroDTO> dtos = new ArrayList<>();
        for (Genero genero : entidades) {
            dtos.add(this.generoEntidad2DTO(genero));
        }
        return dtos;
    }
}
