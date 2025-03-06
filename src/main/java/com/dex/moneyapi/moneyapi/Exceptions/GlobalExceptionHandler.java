package com.dex.moneyapi.moneyapi.Exceptions;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;


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

  // Caso a mensagem não possam ser lidas
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
    return handleExceptionInternal(ex, mensagemUsuario, headers, HttpStatus.BAD_REQUEST, request);
  }
}
