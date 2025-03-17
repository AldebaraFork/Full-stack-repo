package com.dex.moneyapi.moneyapi.resource;



import com.dex.moneyapi.moneyapi.Exceptions.PessoaNotFound;
import com.dex.moneyapi.moneyapi.events.RecursoCriadoEvent;
import com.dex.moneyapi.moneyapi.model.Pessoa;
import com.dex.moneyapi.moneyapi.repository.PessoaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    //procura e lista as pessoas cadastradas
    @GetMapping
    public List<Pessoa> listar(){
       return pessoaRepository.findAll();
    }

    //cria uma nova pessoa
    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this,response, pessoaSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }
    // Busca uma pessoa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFound("Pessoa não encontrada com o ID: " + id));
        return ResponseEntity.ok(pessoa);
    }

}
