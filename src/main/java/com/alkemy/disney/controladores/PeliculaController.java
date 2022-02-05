package com.alkemy.disney.controladores;

import com.alkemy.disney.dto.PeliculaDtoBasic;
import com.alkemy.disney.dto.PeliculaDtoFull;
import com.alkemy.disney.servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class PeliculaController {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @PostMapping
    public ResponseEntity<PeliculaDtoFull> guardar (@RequestBody PeliculaDtoFull dto){
        PeliculaDtoFull guardado = peliculaServicio.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("movies")
    public ResponseEntity<List<PeliculaDtoBasic>> peliDtoBasicList(){
        List<PeliculaDtoBasic> dtos = peliculaServicio.allMovies();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDtoFull> peliculaXId(@PathVariable Long id){
        PeliculaDtoFull pelicula = peliculaServicio.peliculaDetalleId(id);
        return ResponseEntity.ok().body(pelicula);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDtoFull>> listaPorFiltros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false, defaultValue = "ASC") String orden){

        List<PeliculaDtoFull> dtos = peliculaServicio.busquedaXparametro(titulo, genero, orden);
        return ResponseEntity.ok(dtos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDtoFull> editar(@PathVariable Long id, @RequestParam PeliculaDtoFull dto){
        PeliculaDtoFull dtoFull = peliculaServicio.editar(id, dto);
        return ResponseEntity.ok().body(dtoFull);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPelicula(@PathVariable Long id){
        peliculaServicio.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/personaje/{idPj}")
    public ResponseEntity<Void> agregarPj (@PathVariable Long id, @PathVariable Long idPj){

        peliculaServicio.agregarPje(id, idPj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
