package com.dex.moneyapi.moneyapi.repository;

import com.dex.moneyapi.moneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


//repositório de pessoas
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
