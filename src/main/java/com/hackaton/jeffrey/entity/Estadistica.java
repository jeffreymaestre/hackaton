package com.hackaton.jeffrey.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estadisticas")
@Data
@JsonIgnoreProperties({"id", "mutant"})
public class Estadistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "count_mutant_dna")
    private Long mutante;
    @Column(name = "count_human_dna")
    private Long humano;
    private Double ratio;


    public Estadistica() {
    }

    public Estadistica(Long mutante, Long humano, Double ratio) {
        this.mutante = mutante;
        this.humano = humano;
        this.ratio = ratio;
    }
}
