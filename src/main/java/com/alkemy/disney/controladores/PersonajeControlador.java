package com.alkemy.disney.controladores;

import com.alkemy.disney.dto.PersonajeDtoBasic;
import com.alkemy.disney.dto.PersonajeDtoFull;
import com.alkemy.disney.servicio.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("character")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @PostMapping
    public ResponseEntity<PersonajeDtoFull> guardar(@RequestBody PersonajeDtoFull dto) {

        PersonajeDtoFull guardado = personajeServicio.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }


    @GetMapping("/characters")
    public ResponseEntity<List<PersonajeDtoBasic>> listDtobasic() {
        List<PersonajeDtoBasic> dtos = personajeServicio.personajes();
        return ResponseEntity.ok(dtos);
    }



    @GetMapping("/{id}") /*Busqueda por id*/
    public ResponseEntity<PersonajeDtoFull> personajeFullId(@PathVariable Long id) {
        PersonajeDtoFull personaje = this.personajeServicio.personajeDetalleId(id);
        return ResponseEntity.ok().body(personaje);
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDtoFull>> listPjeFiltro(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Long peso,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) List<Long> peliculas,
            @RequestParam(required = false, defaultValue = "ASC") String orden) {


        List<PersonajeDtoFull> dtos = personajeServicio.busquedaXparametro(nombre, edad, peso, id, peliculas, orden);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDtoFull> editar(@PathVariable Long id, @RequestBody PersonajeDtoFull dto) {

        PersonajeDtoFull dtoFull = personajeServicio.editar(id, dto);
        return ResponseEntity.ok().body(dtoFull);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPersonaje(@PathVariable Long id){
        personajeServicio.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

