package tn.socotu.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Assurance;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.entities.Type_social;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.AssuranceRepository;
import tn.socotu.spring.service.AssuranceService;

@RestController
public class AssuranceController {
	
	@Autowired
	AssuranceRepository assuranceRepository;
	@Autowired
	AssuranceService assuranceService;
	
	@Autowired
	AdherentRepository  adherentRepository;
	
	//  http://localhost:8080/ajouterassurance
	@PostMapping("/ajouterassurance")
	public void ajouterassurance(@RequestBody Assurance assurance)
	{
		
		assuranceRepository.save(assurance);
	}
	
	//  http://localhost:8080/selectassurance
	@GetMapping("/selectassurance")
	public List<Assurance> selectassurance()
	{
		
		return assuranceRepository.findAll();
	}
	
	//  http://localhost:8080/details_assurance
	@GetMapping("/details_assurance/{id_assurance}")
	public Assurance details_assurance(@PathVariable("id_assurance") int id_assurance)
	{
		
		return assuranceRepository.findById(id_assurance).get();
	} 
	
	
	//  http://localhost:8080/modifier_assurance/{id_assurance}
	@PutMapping("/modifier_assurance/{id_assurance}")
	public void modifier_assurance(@PathVariable("id_assurance") int id_assurance,@RequestBody Assurance assurance)
	{
		  assuranceService.modifier_assurance(id_assurance,assurance);
	}
	
//  http://localhost:8080/delete_assurance/{id_assurance}
	@DeleteMapping("/delete_assurance/{id_assurance}")
	public void delete_assurance(@PathVariable("id_assurance") int id_assurance)
	{
		
		 assuranceService.supprimer_assurance(id_assurance);
		
	}
	
	
//  http://localhost:8080/select_annee_byassurance/{id_assurance}
	@GetMapping("/select_annee_byassurance/{id_assurance}")
	public List<Integer> select_annee_byassurance(@PathVariable("id_assurance") int id_assurance)
	{
		
		return  assuranceService.select_annee_byassurance(id_assurance);
	}
	
	
	
//  http://localhost:8080/select_mois_byannee/{annee}
	@GetMapping("/select_mois_byannee/{annee}")
	public List<Integer> select_mois_byannee(@PathVariable("annee") int annee)
	{
		
		return  assuranceService.select_mois_byannee(annee);
	}
	
	
//  http://localhost:8080/select_adherent_statistique_mois_byannee/{annee}
	@GetMapping("/select_adherent_statistique_mois_byannee/{annee}")
	public List<Adherent> select_adherent_statistique_mois_byannee(@PathVariable("annee") int annee ,@RequestParam(value="moisselect", required= false) String moisselect )
	{
	
		return  assuranceService.select_adherent_statistique_mois_byannee(annee,moisselect);
	
		
	}
	
//  http://localhost:8080/select_bulletin_byadherent_byannee/{annee}
	@GetMapping("/select_bulletin_byadherent_byannee/{id}/{annee}")
	public List<Remboursement> select_bulletin_byadherent_byannee(@PathVariable("id") int id , @PathVariable("annee") int annee)
	{
		return  assuranceService.select_bulletin_byadherent_byannee(id,annee);
	}
//  http://localhost:8080/select_bulletin_byadherent_byannee_bymois/{id}/{annee}/{mois}
	@GetMapping("/select_bulletin_byadherent_byannee_bymois/{id}/{annee}/{mois}")
	public List<Remboursement> select_bulletin_byadherent_byannee_bymois(@PathVariable("id") int id , @PathVariable("annee") int annee, @PathVariable("mois") int mois)
	{
		return  assuranceService.select_bulletin_byadherent_byannee_bymois(id,annee,mois);
	}	
	
	
//  http://localhost:8080/somme_mntdep_byadherent_byannee/{annee}
	@GetMapping("/somme_mntdep_byadherent_byannee/{id}/{annee}")
	public double somme_mntdep_byadherent_byannee(@PathVariable("id") int id , @PathVariable("annee") int annee)
	{
		return  assuranceService.somme_mntdep_byadherent_byannee(id,annee);
	}
	
//  http://localhost:8080/somme_mntdep_byadherent_byannee_bymois/{annee}
	@GetMapping("/somme_mntdep_byadherent_byannee_bymois/{id}/{annee}/{mois}")
	public double somme_mntdep_byadherent_byannee_bymois(@PathVariable("id") int id , @PathVariable("annee") int annee, @PathVariable("mois") int mois)
	{
		return  assuranceService.somme_mntdep_byadherent_byannee_bymois(id,annee,mois);
	}
	
//  http://localhost:8080/somme_mntremb_byadherent_byannee/{annee}
	@GetMapping("/somme_mntremb_byadherent_byannee/{id}/{annee}")
	public double somme_mntremb_byadherent_byannee(@PathVariable("id") int id , @PathVariable("annee") int annee)
	{
		return  assuranceService.somme_mntremb_byadherent_byannee(id,annee);
	}
//  http://localhost:8080/somme_mntremb_byadherent_byannee_bymois/{annee}
	@GetMapping("/somme_mntremb_byadherent_byannee_bymois/{id}/{annee}/{mois}")
	public double somme_mntremb_byadherent_byannee_bymois(@PathVariable("id") int id , @PathVariable("annee") int annee, @PathVariable("mois") int mois)
	{
		return  assuranceService.somme_mntremb_byadherent_byannee_bymois(id,annee,mois);
	}
	
	
	
//  http://localhost:8080/select_adherent_byType_social/{key}
	@GetMapping("/select_adherent_byType_social/{key}")
	public List<Adherent> select_adherent_byType_social(@PathVariable("key") String key )
	
	
	{
		Type_social readyStatus = Type_social.valueOf(key);

		return  adherentRepository.select_adherent_byType_social(readyStatus);
	}
}
