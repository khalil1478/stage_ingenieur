package tn.socotu.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Bordereau;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.BordereauRepository;
import tn.socotu.spring.repository.RemboursementRepository;
import tn.socotu.spring.service.RemboursementService;

@RestController
public class bordereauController {

	@Autowired
	BordereauRepository bordereauRepository;
	@Autowired
	AdherentRepository adherentRepository;
	@Autowired
	RemboursementService remboursementService;
	
	@Autowired
	RemboursementRepository   remboursementRepository ;
	
	
	
	@GetMapping("/select_bordereau")
	public List<Bordereau> select_bordereau() {
		List<Bordereau> list_bordereau = new ArrayList<>();	
		for (Bordereau bordereau : bordereauRepository.findAll()) {
			if(bordereau.getRemboursements().size() >0)
			{
			bordereau.setMnt_dep(bordereauRepository.somme_dep_remboursement_bybordereau(bordereau.getId()));
			bordereau.setMnt_remboursement(bordereauRepository.somme_remboursement_bybordereau(bordereau.getId()));
			list_bordereau.add(bordereau);

			}
		}
		
		return list_bordereau; 
	}

	// http://localhost:8080/select_remboursement_bybordereau/{id_bordereau}
	@GetMapping("/select_remboursement_bybordereau/{id_bordereau}")
	public List<Remboursement> select_remboursement_bybordereau(@PathVariable("id_bordereau") int id_bordereau) {

		return bordereauRepository.select_remboursement_bybordereau(id_bordereau);

	}

	
	//http://localhost:8080/select_remboursement_bybordereau/{id_bordereau}
	//@GetMapping("/change_status_bybodereau_byadh/{id_bordereau}/{id_adherent}/{num_bul}")
	public int change_status_bybodereau_byadh(@PathVariable("id_bordereau") int id_bordereau,@PathVariable("id_adherent") int id_adherent,@PathVariable("num_bul") int num_bul) {

		return bordereauRepository.change_status_bybodereau_byadh(id_bordereau,id_adherent,num_bul);

	}
	
	// http://localhost:8080/select_remboursement_bybordereau/{id_bordereau}
		@GetMapping("/recherche_bordereau/{key}")
		public List<Bordereau> recherche_bordereau(@PathVariable("key") String key) {
			
				
			List<Bordereau> list_bordereau = new ArrayList<>() ;
			for (Bordereau bordereau : bordereauRepository.recherche_bordereau(Integer.parseInt(key))) {
				
			
			bordereau.setMnt_dep(bordereauRepository.somme_dep_remboursement_bybordereau(bordereau.getId()));
			bordereau.setMnt_remboursement(bordereauRepository.somme_remboursement_bybordereau(bordereau.getId()));
			list_bordereau .add(bordereau);
			}
			return list_bordereau;

		}
		
		// http://localhost:8080/recherche_bulletin_search/{id_bordereau}
		
		
		@GetMapping("/recherche_bulletin_search/{key}/{id}")
		public List<Remboursement> recherche_bulletin_search(@PathVariable("key") String key,@PathVariable("id") int id) {
			List<Remboursement> remboursements =new ArrayList<>();
				int num =Integer.parseInt(key);
			  Remboursement remboursement = new Remboursement();
			/*  if(bordereauRepository.change_status_bybodereau_byadh(id,adherentRepository.findById(remboursementRepository.adherentbynum_bull_by_bordereau(num,id)).get().getId(),num) !=0){
				 a= 1;

			  }
			  else*/
			//	  a= 0;
			  remboursement.setNumero_bulletin(num);
			  remboursement.setAdherent(adherentRepository.findById(remboursementRepository.adherentbynum_bull_by_bordereau(num,id)).get());
			  remboursement.setChaine(remboursementService.select_tous_adherentbynum_bull_byidbordereau(num,id));
			  remboursement.setNum_matricule(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getMatricule());
			  remboursement.setMontant_remboursement(remboursementRepository.somme_remboursement_byadherent_byvalidation_byidbordereau(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId(), id));
			  remboursement.setMontant_dep(remboursementRepository.somme_dep_byadherent_byvalidation_byidbordereau(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId(), id));
			//  remboursement.setStatus(a);
			  remboursements.add(remboursement);
		return remboursements;
		}
		
		
		
		
		// http://localhost:8080/details_adherent/{id_adherent}
		@GetMapping("/details_bordereau/{id}")
		public Bordereau details_bordereau(@PathVariable("id") int id) {
			return bordereauRepository.findById(id).get();
		}
		
		
		
		// http://localhost:8080/modifier_adherent/{id_adherent}
		@PutMapping("/modifier_bordereau/{id}")
		public void modifier_bordereau(@PathVariable("id") int id, @RequestBody Bordereau bordereau) {
			
			Bordereau bordereau_to_update = bordereauRepository.findById(id).get();

			bordereau_to_update.setDate(bordereau.getDate());
			bordereau_to_update.setDate_arrivees(bordereau.getDate_arrivees());
			bordereau_to_update.setNumero_bordereau_arrivees(bordereau.getNumero_bordereau_arrivees());
			bordereauRepository.save(bordereau_to_update);
			
			
		}
		
		
		
		
		// http://localhost:8080/ajouterReclamation/{id_reboursement}
		/*@GetMapping("/recherche_remboursement_bulletins_arrivees/{key}")
		public List<Remboursement> recherche_remboursement_bulletins_arrivees(@PathVariable("key") String key) 
		{		
			int a = Integer.parseInt(key);
			List<Remboursement> list = new ArrayList<>();

		for (Rem  reclamation : reclamationRepository.recherche_remboursement_bulletins_arrivees(a)) {
			list.add(reclamation.getRemboursement());
		}
		return list; 

			}*/


}
