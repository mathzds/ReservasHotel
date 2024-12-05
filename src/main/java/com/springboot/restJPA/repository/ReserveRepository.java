package com.springboot.restJPA.repository;

import com.springboot.restJPA.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserva, String> {
}
