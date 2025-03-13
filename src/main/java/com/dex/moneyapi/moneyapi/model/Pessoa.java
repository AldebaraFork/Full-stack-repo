package com.dex.moneyapi.moneyapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity // Adiciona a anotação @Entity para que o JPA reconheça a classe como uma entidade
@Table(name = "pessoa") //define o nome da tabela no BD
public class Pessoa {



    @Id //Define o campo como chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera o ID automaticamente
    @Column(name = "codigo") // Mapeia para a coluna "codigo" no banco de dados
    private Long id;

    @NotNull(message = "o nome não pode ser nulo!") //define que o nome não pode ser null
    private String nome;

    @NotNull(message = "o status ativo não pode ser nulo! ")
    private boolean ativo;

    @Embedded
    private Endereco endereco;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é a mesma instância
        if (o == null || getClass() != o.getClass()) return false; // Verifica se é do mesmo tipo
        Pessoa pessoa = (Pessoa) o; // Faz o cast para Pessoa
        return Objects.equals(id, pessoa.id); // Compara apenas o campo "id"
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Calcula o hashCode com base no campo "id"
    }

    //getters & setters

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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
