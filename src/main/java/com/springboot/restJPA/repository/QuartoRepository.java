package com.springboot.restJPA.repository;

import com.springboot.restJPA.models.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<Quarto, String> {
    Quarto findByNumero(String numero);
}
