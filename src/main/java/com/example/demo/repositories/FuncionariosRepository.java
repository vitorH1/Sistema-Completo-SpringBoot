package com.example.demo.repositories;

import com.example.demo.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionariosRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByNomeAndSenha(String nome, String senha); // O método deve seguir este formato
}
