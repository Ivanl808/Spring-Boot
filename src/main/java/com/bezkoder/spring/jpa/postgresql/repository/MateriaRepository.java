package com.bezkoder.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jpa.postgresql.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

}