package com.alkemy.disney.servicio;

import com.alkemy.disney.dto.PeliculaDtoBasic;
import com.alkemy.disney.dto.PeliculaDtoFull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PeliculaServicio {

    PeliculaDtoFull guardar(PeliculaDtoFull dtoFull);
    PeliculaDtoFull peliculaDetalleId(Long id);
    PeliculaDtoFull editar(Long id, PeliculaDtoFull dto);
    List<PeliculaDtoFull> busquedaXparametro(String nombre, String genero, String orden);
    void delete (Long id);
    List<PeliculaDtoBasic> allMovies();
    PeliculaDtoFull agregarPje(Long idPeli, Long idPj);
    PeliculaDtoFull quitarPje(Long idPeli, Long idPj);
}
