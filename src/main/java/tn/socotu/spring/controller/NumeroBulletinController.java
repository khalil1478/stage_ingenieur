package tn.socotu.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Numero_Bulletin;
import tn.socotu.spring.repository.Numero_BulletinRepository;

@RestController
public class NumeroBulletinController {
	
	@Autowired
	Numero_BulletinRepository numero_BulletinRepository;
	

	// http://localhost:8080/recherche_numero_bulletin/{valeur}
	@GetMapping("/recherche_numero_bulletin/{valeur}")
	public int ajouterRemboursement( @PathVariable("valeur") int valeur ) 
	{
		 return numero_BulletinRepository.findByNumero_b(valeur);
	}

}
