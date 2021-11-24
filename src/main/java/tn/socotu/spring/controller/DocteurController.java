package tn.socotu.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Docteur;
import tn.socotu.spring.repository.CategoryRepository;
import tn.socotu.spring.repository.DocteurRepository;

@RestController
public class DocteurController {
	
	@Autowired
	DocteurRepository docteurRepository;
	@Autowired
	CategoryRepository categoryRepository;

//  http://localhost:8080/select_docteur
	@GetMapping("/select_docteur")
	public List<Docteur> select_docteur()
	{  List<Docteur> docteurs = new ArrayList<>();

	docteurRepository.findAll().forEach(docteurs::add);
	return docteurs;		
			
	}
	
	
	
	@GetMapping("/details_docteur/{id_docteur}")
	public Docteur details_docteur(@PathVariable("id_docteur") int id_docteur)
	{

	 return docteurRepository.findById(id_docteur).get();
		
			
	}
	
	
	
	//  http://localhost:8080/ajouterdocteurbycategory/{id_category}/{name}
	@PostMapping("/ajouterdocteurbycategory/{id_category}/{name}")
	public  int ajouterdocteurbycategory(@PathVariable("id_category") int id_category,@PathVariable("name") String name)
	{ 
		if(docteurRepository.existsByName(name)  == false)
		{
			System.out.println("condition if ");

		Docteur docteur = new Docteur();
		docteur.setName(name);
		docteur.setCategory_docteur(categoryRepository.findById(id_category).get());
  return docteurRepository.save(docteur).getId();	
			
			
			
		}
		
		else
		{
			System.out.println("condition else ");

			return docteurRepository.findByName(name).getId();
		}
		
	 
		
	}
	
//  http://localhost:8080/finddocteurbyname/{name}
	@GetMapping("/finddocteurbyname/{id}")
	public  int finddocteurbyname(@PathVariable("id") int id)
	{ 
		
		return docteurRepository.findById(id).get().getId();
		
	 
		
	}
	
	
	
//  http://localhost:8080/select_docteurbycategory/{id_category}
	@GetMapping("/select_docteurbycategory/{id_category}")
	public List<Docteur> select_docteurbycategory(@PathVariable("id_category") int id_category)
	{ 
		List<Docteur> docteurs = new ArrayList<>();
	
	for (Docteur docteur : categoryRepository.findById(id_category).get().getDocteurs()) {
		docteurs.add(docteur);
	}

	return docteurs;		
			
	}
	

	
}
