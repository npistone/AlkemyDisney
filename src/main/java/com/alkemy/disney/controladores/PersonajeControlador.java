package com.alkemy.disney.controladores;

import com.alkemy.disney.dto.PersonajeDtoFull;
import com.alkemy.disney.servicio.PersonajeServicio;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @GetMapping("/{id}") /*Busqueda por id*/
    public ResponseEntity<PersonajeDtoFull> personajeFullId(@PathVariable Long id){
        PersonajeDtoFull personaje = this.personajeServicio.getDetailsById(id);
    }
}
