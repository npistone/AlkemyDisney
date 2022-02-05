package com.alkemy.disney.controladores;

import com.alkemy.disney.dto.ApiErrorDto;
import com.alkemy.disney.excepciones.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
/*al extender de la clase Response le permite devolver la entidad mencionada*/
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

      /*Voy a manejar los errors y devolver na entidad
      * Se utiliza el param not found clases para recibir los msj detallados en cada error que encontremos
      * y se le da el msj indicado*/
    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request){
        ApiErrorDto errorDto = new ApiErrorDto(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Param Not Found")
        );
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
/*formato del Handler excepcion captura, formato de la entidad,*/
    }
    /*Se genera el array por si lo Valid generan un error y hay que atraparlo
    Los valid sirven para validad los metodos u procesos donde se ingresen valores
    y se atrapan si se generan errores. (Creo que puede ser utilizada para cada controlador)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {

            errors.add(error.getField()+ ":" + error.getDefaultMessage());
                
            }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getObjectName()+ ":" +error.getDefaultMessage());

        }
        ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){

        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
