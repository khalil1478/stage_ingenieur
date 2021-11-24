package tn.socotu.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Contrat;

@Repository
public interface ContratRepository extends CrudRepository<Contrat, Integer> {

}