package com.dex.moneyapi.moneyapi.resource;

import com.dex.moneyapi.moneyapi.model.Categoria;
import com.dex.moneyapi.moneyapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
