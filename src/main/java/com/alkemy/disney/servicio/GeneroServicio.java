package com.alkemy.disney.servicio;

import com.alkemy.disney.dto.GeneroDTO;

import java.util.List;

public interface GeneroServicio {
    /*
    Establecemos el metodo sin definir para que se aplique
    en la implementacion
     */

     GeneroDTO guardar(GeneroDTO dto );
     /*Recibe y devuelve un dto*/

     List<GeneroDTO> getAllGeneros();
}
