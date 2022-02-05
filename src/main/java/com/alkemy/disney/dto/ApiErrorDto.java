package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorDto {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
/*Esta entidad es creada para poder utilizarla en el Handler*/