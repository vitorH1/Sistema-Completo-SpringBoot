package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int funcionario_id;
    
    @Column(nullable = false)
    private String nome;
    
    private BigDecimal salario;
    private String cpf;
    private String senha;

    // Construtor vazio (necessário para o JPA)
    public Funcionario() {
    }

    // Construtor com todos os parâmetros
    public Funcionario(String nome, BigDecimal salario, String cpf, String senha) {
        this.nome = nome;
        this.salario = salario;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters e Setters
    public int getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(int funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
