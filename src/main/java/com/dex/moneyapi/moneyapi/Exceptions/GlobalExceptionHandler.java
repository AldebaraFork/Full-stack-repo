package com.dex.moneyapi.moneyapi.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
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



@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

   //Captura exceções genéricas e retorna um 500
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado: " + ex.getMessage());
  }

  //retorna um 404 e captura a CategoriaNotFoundException
  @ExceptionHandler(CategoriaNotFound.class)
    public ResponseEntity<Object> handleCategoriaNotFound(CategoriaNotFound ex, WebRequest request) {
    String mensagemUsuario = ex.getMessage();
    String mensagemDev = ex.toString();

    List<Erro> erros = Arrays.asList(new Erro(null, mensagemUsuario, mensagemDev));

    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }



  // Caso a mensagem não possam ser lidas
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
    String mensagemDev = ex.getCause().toString();
    List<Erro> erros = Arrays.asList(new Erro(null, mensagemUsuario,mensagemDev));
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }


  //verifica argumentos invalidos e retorna um 400 bad request
  @ExceptionHandler(InvalidArgumentException.class)
  public ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException ex, WebRequest request) {
    String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
    String mensagemDev = ex.getCause().toString();

    List<Erro> erros = Arrays.asList(new Erro(null, mensagemUsuario, mensagemDev));

    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }


  // Tratamento para MethodArgumentNotValidException
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    List<Erro> erros = criarListaErros(ex.getBindingResult());
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }


  // Cria lista de erros
  private List<Erro> criarListaErros(BindingResult bindingResult) {
    List<Erro> erros = new ArrayList<>();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
      String mensagemDev = fieldError.toString();
      erros.add(new Erro(fieldError.getField(), mensagemUsuario, mensagemDev));
    }
    return erros;
  }





//classe para retorno de mensagens de erro
  public static class Erro{

    private String campo; //onde ocorreu o erro
    private String mensagemUsuario;
    private String mensagemDev;

    public Erro(String campo, String mensagemUsuario, String mensagemDev) {
      this.campo = campo;
      this.mensagemUsuario = mensagemUsuario;
      this.mensagemDev = mensagemDev;
    }

    public String getCampo() {
      return this.campo;
    }
    public void setCampo(String campo) {
      this.campo = campo;
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


  }
}
