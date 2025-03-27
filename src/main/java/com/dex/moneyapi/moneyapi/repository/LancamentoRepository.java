package com.dex.moneyapi.moneyapi.repository;

import com.dex.moneyapi.moneyapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
