package com.dex.moneyapi.moneyapi.resource;


import com.dex.moneyapi.moneyapi.Exceptions.CategoriaNotFound;
import com.dex.moneyapi.moneyapi.events.RecursoCriadoEvent;
import com.dex.moneyapi.moneyapi.model.Categoria;
import com.dex.moneyapi.moneyapi.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

//onde se recebe e ocorre a validação

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {



    @Autowired
    private CategoriaRepository categoriaRepository ;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
          Categoria categoriaSalva = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this,response, categoriaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarCodigo(@PathVariable Long codigo) {
        Categoria categoria = categoriaRepository.findById(codigo)
                .orElseThrow(() -> new CategoriaNotFound("Categoria não encontrada com o ID: " + codigo));
        return ResponseEntity.ok(categoria);
    }



}
