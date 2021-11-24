package tn.socotu.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Docteur;

@Repository
public interface DocteurRepository  extends CrudRepository<Docteur, Integer>{

	Docteur findByName(String name);
	boolean existsByName(String name);

}
