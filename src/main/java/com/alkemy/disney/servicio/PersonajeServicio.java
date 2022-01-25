package com.alkemy.disney.servicio;

import com.alkemy.disney.dto.PersonajeDtoFull;

public interface PersonajeServicio {

    PersonajeDtoFull getDetailsById(Long id);

}
