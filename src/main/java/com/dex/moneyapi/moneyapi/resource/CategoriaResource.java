package com.dex.moneyapi.moneyapi.resource;


import com.dex.moneyapi.moneyapi.Exceptions.CategoriaNotFound;
import com.dex.moneyapi.moneyapi.model.Categoria;
import com.dex.moneyapi.moneyapi.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {



    @Autowired
    private CategoriaRepository categoriaRepository ;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
          Categoria categoriaSalva = categoriaRepository.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString ());

        return ResponseEntity.created(uri).body(categoriaSalva);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarCodigo(@PathVariable Long codigo) {
        Categoria categoria = categoriaRepository.findById(codigo)
                .orElseThrow(() -> new CategoriaNotFound("Categoria não encontrada com o ID: " + codigo));
        return ResponseEntity.ok(categoria);
    }



}
