package com.dex.moneyapi.moneyapi.repository.Lancamento;

import java.util.List;

import com.dex.moneyapi.moneyapi.model.Lancamento;
import com.dex.moneyapi.moneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
