package com.example.demo.repositories;

import com.example.demo.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
    
}

