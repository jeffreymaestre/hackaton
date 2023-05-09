package com.hackaton.jeffrey.repository;

import com.hackaton.jeffrey.entity.Adn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdnDao extends CrudRepository<Adn, Long> {
}
