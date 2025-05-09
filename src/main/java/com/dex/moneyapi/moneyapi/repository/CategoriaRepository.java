package com.dex.moneyapi.moneyapi.repository;

import com.dex.moneyapi.moneyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends JpaRepository  <Categoria, Long> {
}
