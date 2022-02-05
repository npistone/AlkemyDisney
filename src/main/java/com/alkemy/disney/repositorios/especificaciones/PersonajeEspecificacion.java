package com.alkemy.disney.repositorios.especificaciones;

import com.alkemy.disney.dto.PersonajeDtoFilter;
import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.entidades.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeEspecificacion {

    public Specification<Personaje> getByFilters(PersonajeDtoFilter filtrosDto){
        return (root, query, criteriaBuilder) ->{


            List<Predicate> predicates= new ArrayList<>();

            if(StringUtils.hasLength(filtrosDto.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" +filtrosDto.getNombre().toLowerCase() +"%"
                        )
                );
            }/*Creo el primer filtro si tiene contenido el parametro nombre*/

            if(filtrosDto.getPeso()!= null){
                predicates.add(
                        criteriaBuilder.equal( (root.get("peso")),filtrosDto.getPeso())
                        );

            }
            if(filtrosDto.getEdad()!= null){
                predicates.add(
                        criteriaBuilder.equal( (root.get("edad")),filtrosDto.getEdad())
                );

            }

            if(!CollectionUtils.isEmpty(filtrosDto.getPeliculas())){
                Join<Pelicula, Personaje> join= root.join("peliculas", JoinType.INNER);
                Expression<String> peliculasId= join.get("id");
                predicates.add(peliculasId.in(filtrosDto.getPeliculas()));
            };
            query.distinct(true);

            String orderByField="orden";
            query.orderBy(
                    filtrosDto.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
