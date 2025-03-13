package com.dex.moneyapi.moneyapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id;

    @NotNull(message = "o nome não pode ser nulo!")
    private String nome;

    @NotNull(message = "o status ativo não pode ser nulo! ")
    private boolean ativo;

    @Embedded
    private Endereco endereco;


    //equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é a mesma instância
        if (o == null || getClass() != o.getClass()) return false; // Verifica se é do mesmo tipo
        Pessoa pessoa = (Pessoa) o; // Faz o cast para Pessoa
        return Objects.equals(nome, pessoa.nome); // Compara apenas o campo "nome"
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);// Calcula o hashCode com base no campo "nome"
    }

    //getters & setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public boolean isAtivo() {
        return this.ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
