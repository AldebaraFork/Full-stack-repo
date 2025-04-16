package com.dex.moneyapi.moneyapi.service;

import com.dex.moneyapi.moneyapi.Exceptions.PessoaNotFound;
import com.dex.moneyapi.moneyapi.model.Lancamento;
import com.dex.moneyapi.moneyapi.model.Pessoa;
import com.dex.moneyapi.moneyapi.repository.LancamentoRepository;
import com.dex.moneyapi.moneyapi.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {


    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;


        @Transactional
        public Lancamento salvar(Lancamento lancamento) {
            // Verifica se a pessoa foi informada
            if (lancamento.getPessoa() == null || lancamento.getPessoa().getId() == null) {
                throw new PessoaNotFound("Pessoa não informada");
            }

            // Busca a pessoa no banco
            Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getId())
                    .orElseThrow(() -> new PessoaNotFound("Pessoa não encontrada com ID: " + lancamento.getPessoa().getId()));

            // Verifica se está inativa
            if (!pessoa.isAtivo()) {
                throw new PessoaNotFound("Pessoa está inativa");
            }

            return lancamentoRepository.save(lancamento);
        }
    }


