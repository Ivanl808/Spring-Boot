package com.bezkoder.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{

}