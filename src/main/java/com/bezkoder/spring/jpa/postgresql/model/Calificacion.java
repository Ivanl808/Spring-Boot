package com.bezkoder.spring.jpa.postgresql.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Long idCalificacion;

    @Column(nullable = false)
    private Double calificacion;

    @Column(nullable = false)
    private String parcial;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    public Calificacion() {
    }

    public Calificacion(Long idCalificacion, Double calificacion,
                         String parcial, Alumno alumno,
                         Materia materia) {

        this.idCalificacion = idCalificacion;
        this.calificacion = calificacion;
        this.parcial = parcial;
        this.alumno = alumno;
        this.materia = materia;
    }

    public Long getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Long idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getParcial() {
        return parcial;
    }

    public void setParcial(String parcial) {
        this.parcial = parcial;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}