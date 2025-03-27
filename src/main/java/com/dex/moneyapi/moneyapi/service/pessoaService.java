package com.dex.moneyapi.moneyapi.service;

import com.dex.moneyapi.moneyapi.model.Pessoa;
import com.dex.moneyapi.moneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

//informa que essa classe é um componente, onde sera gerenciada automaticamente
@Service
public class pessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

     public Pessoa atualizar(Long id,Pessoa pessoa){
         Pessoa pessoaSalva = buscarPessoaPorId(id);
         BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        return pessoaRepository.save(pessoaSalva);
     }

    public void atualizarPropriedadeAtivo(Long id, boolean ativo){
        Pessoa pessoaSalva = buscarPessoaPorId(id);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
     }


    private Pessoa buscarPessoaPorId(Long id) {
        Pessoa pessoaSalva = pessoaRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return pessoaSalva;
    }
}
