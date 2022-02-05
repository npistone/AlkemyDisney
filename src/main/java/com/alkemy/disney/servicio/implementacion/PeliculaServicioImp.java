package com.alkemy.disney.servicio.implementacion;

import com.alkemy.disney.dto.PeliculaDtoBasic;
import com.alkemy.disney.dto.PeliculaDtoFilter;
import com.alkemy.disney.dto.PeliculaDtoFull;
import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.ParamNotFound;
import com.alkemy.disney.mapper.PeliculaMapper;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repositorios.PeliculaRepositorio;
import com.alkemy.disney.repositorios.PersonajeRepositorio;
import com.alkemy.disney.repositorios.especificaciones.PeliculaEspecificacion;
import com.alkemy.disney.servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServicioImp implements PeliculaServicio {

    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    @Autowired
    private PeliculaEspecificacion peliculaEspecificacion;




    public PeliculaDtoFull guardar(PeliculaDtoFull dtoFull) {
        Pelicula entidad = peliculaMapper.peliculaDtoFull2Entidad(dtoFull);

        Pelicula peliGuardada = peliculaRepositorio.save(entidad);

        PeliculaDtoFull dto = peliculaMapper.peliculaEntidad2DtoFull(peliGuardada, false);

        return dto;
    }

    @Override
    public PeliculaDtoFull peliculaDetalleId(Long id) {
        Optional<Pelicula> optPeli = peliculaRepositorio.findById(id);
        if (!optPeli.isPresent()) {

            throw new ParamNotFound("Pelicula id no encontrado");
        }
        Pelicula pelicula = optPeli.get();
        PeliculaDtoFull dtoFull = peliculaMapper.peliculaEntidad2DtoFull(pelicula, true);

        return dtoFull;
    }





    public PeliculaDtoFull editar(Long id, PeliculaDtoFull dto) {

        Optional<Pelicula> optPeli = peliculaRepositorio.findById(id);
        if (!optPeli.isPresent()) {

            throw new ParamNotFound("Pelicula id no encontrado");
        }
        Pelicula pelicula = optPeli.get();
        pelicula.setId(dto.getId());
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setGeneroId(dto.getGenero());
        pelicula.setCalificacion(dto.getCalificacion());
        pelicula.setImagen(dto.getImagen());
        pelicula.setFechaCreacion(string2LocalDate(dto.getFechaCreacion()));
        pelicula.setPersonajes(personajeMapper.personajeDtoFullList2PersonsajeEntList(dto.getPersonajes()));

        Pelicula guardada= peliculaRepositorio.save(pelicula);
        PeliculaDtoFull dtoFull=peliculaMapper.peliculaEntidad2DtoFull(guardada, true);

        return dtoFull;

    }

    @Override
    public List<PeliculaDtoFull> busquedaXparametro(String nombre, String genero, String orden) {
        PeliculaDtoFilter peliFilter = new PeliculaDtoFilter(nombre,genero, orden);
        List<Pelicula> entidades = peliculaRepositorio.findAll(peliculaEspecificacion.getByFilters(peliFilter));
        List<PeliculaDtoFull> dtos = peliculaMapper.peliculaEnt2DtoFullList(entidades, true);

        return dtos;
    }

    @Override
    public void delete(Long id) {
        peliculaRepositorio.deleteById(id);

    }

    @Override
    public List<PeliculaDtoBasic> allMovies() {

        List<Pelicula> entidades = peliculaRepositorio.findAll();
        List<PeliculaDtoBasic> dtos = new ArrayList<>();
        for (Pelicula pelicula: entidades) {

            dtos.add(peliculaMapper.pelicula2DtoBasic(pelicula));

        }
        return dtos;
    }

    @Override
    public PeliculaDtoFull agregarPje(Long idPeli, Long idPj) {
        Optional<Pelicula> busca = peliculaRepositorio.findById(idPeli);
        if (!busca.isPresent()){

            throw new ParamNotFound("Pelicula no encontrada");
        }
        Optional<Personaje> buscaPje = personajeRepositorio.findById(idPj);
        if (!buscaPje.isPresent()){

            throw new ParamNotFound("Personaje no encontrado");
        }

        Personaje pjAdd = buscaPje.get();
        Pelicula peli = busca.get();
        peli.agregarPje(pjAdd);
        PeliculaDtoFull dto = peliculaMapper.peliculaEntidad2DtoFull(peli, true);
        return dto;
    }

    @Override
    public PeliculaDtoFull quitarPje(Long idPeli, Long idPj) {
        return null;
    }

    private LocalDate string2LocalDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dia = LocalDate.parse(fecha, formatter);
        return dia;
    }
}
