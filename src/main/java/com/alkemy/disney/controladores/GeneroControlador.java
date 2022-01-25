package com.alkemy.disney.controladores;


import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.servicio.GeneroServicio;
import com.alkemy.disney.servicio.implementacion.GeneroServicioImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("generos")
public class GeneroControlador {

    /*Llamamos directamente a la interface*/
    @Autowired
    private GeneroServicio generoServicio;



    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = generoServicio.getAllGeneros();
        return ResponseEntity.ok().body(generos);


    }



    /*
    Metodo de guardado de Genero
    Recibimos un dto de clase que vamos a guardar.
    Como vamos a Devolver un Response entity, con el tipo especifico
    de Status
     */


    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){
        GeneroDTO generoGuardado = generoServicio.guardar(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);

        /*
         * Puede haber redundancia pero por lo que entiendo es para generar el dto y
         * despues devolver el status y json
         * */
    }
}
