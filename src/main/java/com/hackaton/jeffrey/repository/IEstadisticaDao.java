package com.hackaton.jeffrey.repository;

import com.hackaton.jeffrey.entity.Estadistica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadisticaDao extends CrudRepository<Estadistica, Long> {

}
