package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Long>, JpaSpecificationExecutor<Personaje> {

    List<Personaje> findAll(Specification<Personaje> spec);
}
