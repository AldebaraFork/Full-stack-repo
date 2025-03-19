package com.dex.moneyapi.moneyapi.resource;



import com.dex.moneyapi.moneyapi.Exceptions.PessoaNotFound;
import com.dex.moneyapi.moneyapi.Exceptions.ResourceNotFoundException;
import com.dex.moneyapi.moneyapi.events.RecursoCriadoEvent;
import com.dex.moneyapi.moneyapi.model.Pessoa;
import com.dex.moneyapi.moneyapi.repository.PessoaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

//controlador REST

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
        //publica o evento com a instancia do publisher
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        //remove uma pessoa do banco de dados  com um determinado ID
        pessoaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa){
        Pessoa pessoaSalva = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o id: " + id));

        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        pessoaRepository.save(pessoaSalva);
        return ResponseEntity.ok(pessoaSalva);

    }

}
