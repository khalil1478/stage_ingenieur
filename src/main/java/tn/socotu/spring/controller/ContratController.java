package tn.socotu.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Contrat;
import tn.socotu.spring.repository.ContratRepository;

@RestController
public class ContratController {
	
	@Autowired
	ContratRepository contratRepository;
	
	
	
	//  http://localhost:8080/ajoutercontrat
	@PostMapping("/ajoutercontrat")
	public void ajoutercontrat(@RequestBody Contrat contrat)
	{
		  contratRepository.save(contrat);
	}

}
