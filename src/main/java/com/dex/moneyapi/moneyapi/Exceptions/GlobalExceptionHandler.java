package com.dex.moneyapi.moneyapi.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {


  //retorna um 404 e captura a CategoriaNotFoundException
  @ExceptionHandler(CategoriaNotFound.class)
    public ResponseEntity<String> handleCategoriaNotFound(CategoriaNotFound ex){
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  // Captura exceções genéricas e retorna um 500
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado: " + ex.getMessage());
  }


}
