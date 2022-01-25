package com.alkemy.disney.servicio.implementacion;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.mapper.GeneroMapper;
import com.alkemy.disney.repositorios.GeneroRepositorio;
import com.alkemy.disney.servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServicioImp implements GeneroServicio {

    /*Clase que transforma el dto en entidad*/
    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroRepositorio generoRepositorio;


    public GeneroDTO guardar(GeneroDTO dto ) {

        Genero entidad = generoMapper.generoDTO2Entidad(dto);
        /*Recibe el dpto, llama al mapper y transforma en entidad*/

        Genero entidadGuarda = generoRepositorio.save(entidad);
        /*Al utilizar el repo para guardar la entidad, nos devuelve la entidad guardad*/

        GeneroDTO resultado = generoMapper.generoEntidad2DTO(entidadGuarda);
        /*la Entidad guarda la transformamos en dto y la devolvemos con el 201*/

        return resultado;


    }


    public List<GeneroDTO> getAllGeneros() {
        List<Genero> generos =generoRepositorio.findAll();
        List<GeneroDTO> resultado = generoMapper.generoEntList2DtoList(generos);

        return resultado;
    }


}
