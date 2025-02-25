package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.NumberFormat;


@Entity
@Table(name = "produto")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false)
    private String descricao;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal preco;

    private int estoque;

    private LocalDate data_cadastro; 

    public LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    // Construtor vazio (necessário para o JPA)
    public Produto() {
    }

    // Construtor com todos os parâmetros
    public Produto(String descricao, BigDecimal preco, int estoque) {
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}

/*
@Entity: Define que a classe Produto é uma entidade JPA.
@Column(nullable=false): Define que o atributo não pode ser nulo
@Table(name = "produtos"): Mapeia a entidade para a tabela produtos no banco de dados.
@Id: O campo id é a chave primária.
@GeneratedValue(strategy = GenerationType.IDENTITY): Define que o campo id será auto incrementado no banco de dados.
BigDecimal: O campo valor é do tipo BigDecimal para representar valores monetários com precisão.
Construtores: Um construtor vazio é necessário para o JPA, e um construtor com todos os campos foi adicionado para facilitar a criação de objetos.
Getters e Setters: Métodos para acessar e modificar os atributos.
 */