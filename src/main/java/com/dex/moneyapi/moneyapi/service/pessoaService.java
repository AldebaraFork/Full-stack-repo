package com.dex.moneyapi.moneyapi.service;

import com.dex.moneyapi.moneyapi.Exceptions.ResourceNotFoundException;
import com.dex.moneyapi.moneyapi.model.Pessoa;
import com.dex.moneyapi.moneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//informa que essa classe é um componente, onde sera gerenciada automaticamente
@Service
public class pessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

     public Pessoa atualizar(Long id,Pessoa pessoa){
        Pessoa pessoaSalva = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o id: " + id));

        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        return pessoaRepository.save(pessoaSalva);
     }
}
