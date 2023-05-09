package com.hackaton.jeffrey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "adn")
@Getter
@Setter
public class Adn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ElementCollection
    @Column(columnDefinition = "text")
    private List<String> secuencia;*/

    private String[] dna;
}
