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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  // Captura exceções genéricas e retorna um 500
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado: " + ex.getMessage());
  }

  //retorna um 404 e captura a CategoriaNotFoundException
  @ExceptionHandler(CategoriaNotFound.class)
    public ResponseEntity<String> handleCategoriaNotFound(CategoriaNotFound ex){
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }



  // Caso a mensagem não possam ser lidas
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
    String mensagemDev = ex.getCause().toString();
    List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario,mensagemDev));
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  // Captura argumentos de métodos que não sao validos
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    List<Erro> erros = criarListaErros(ex.getBindingResult());
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  private List<Erro> criarListaErros(BindingResult bindingResult) {
    List<Erro> erros = new ArrayList<>();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
      String mensagemDev = fieldError.toString();
      erros.add(new Erro(mensagemUsuario, mensagemDev));
    }
    return erros;
  }




//classe para retorno de mensagens de erro
  public static class Erro{

    public Erro(String mensagemUsuario, String mensagemDev) {
      this.mensagemUsuario = mensagemUsuario;
      this.mensagemDev = mensagemDev;
    }

    public String getMensagemUsuario() {
      return mensagemUsuario;
    }

    public void setMensagemUsuario(String mensagemUsuario) {
      this.mensagemUsuario = mensagemUsuario;
    }

    public String getMensagemDev() {
      return mensagemDev;
    }

    public void setMensagemDev(String mensagemDev) {
      this.mensagemDev = mensagemDev;
    }

    private String mensagemUsuario;
     private String mensagemDev;
  }
}
