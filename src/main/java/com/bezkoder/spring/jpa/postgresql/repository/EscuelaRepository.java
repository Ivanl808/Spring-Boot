package com.bezkoder.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Escuela;

public interface EscuelaRepository extends JpaRepository<Escuela, Long> {

}