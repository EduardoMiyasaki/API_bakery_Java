package com.example.springboot.repositories;

import com.example.springboot.Models.BakeryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BakeryRepository extends JpaRepository <BakeryModel , UUID> {
    // Repository é um mecanismo para interagir com o banco de dados
    // <> aqui dentro estou passando a tabela
}
