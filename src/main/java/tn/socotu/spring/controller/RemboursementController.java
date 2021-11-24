package tn.socotu.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Bordereau;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.BordereauRepository;
import tn.socotu.spring.repository.RemboursementRepository;
import tn.socotu.spring.service.RemboursementService;




@RestController
public class RemboursementController {
	
	@Autowired
	RemboursementRepository   remboursementRepository ;
	
	@Autowired
	RemboursementService remboursementService;
	
	@Autowired
	AdherentRepository adherentRepository;
	@Autowired
	BordereauRepository bordereauRepository;
	
	
	
	
	
	
	// http://localhost:8080/ajouterRemboursement_ver2/{id_adherent}/{id_category}
		@PostMapping("/ajouterRemboursement_ver2/{numero}/{id_adherent}/{id_category}")
		public void ajouterRemboursement_ver2(@RequestBody Remboursement  remboursement , @PathVariable("numero") int numero, @PathVariable("id_adherent") int id_adherent,@PathVariable("id_category") int id_category) 
		{
			   remboursementService.ajouterRemboursement_ver2(remboursement,numero,id_adherent,id_category);
		}
	
	
//  http://localhost:8080/select_remboursement
	@GetMapping("/select_remboursement")
	public List<Remboursement> select_remboursement()
	{  List<Remboursement> remboursements = new ArrayList<>();
	
	for (Remboursement remboursement : remboursementRepository.liste_remboursement()) {
		
		remboursements.add(remboursement);
	}
	return remboursements;		
			
	}
	
	

	
	
	
	
	
	// http://localhost:8080/afficher_remboursement_byadherent/{id_adherent}
		@GetMapping("/afficher_remboursement_byadherent/{id_adherent}")
		public List<Remboursement> afficher_remboursement_byadherent(@PathVariable("id_adherent") int id_adherent) 
		{
			 return remboursementService.afficher_remboursement_byadherent(id_adherent);
		}
	
		// http://localhost:8080/modifier_remboursement/{id_reboursement}
		@PutMapping("/modifier_remboursement_bycategory/{id_reboursement}/{id_category}")
		public void modifier_remboursement_bycategory(@PathVariable("id_reboursement") int id_reboursement,@PathVariable("id_category") int id_category, @RequestBody Remboursement remboursement) {
			remboursementService.modifier_remboursement_bycategory(id_reboursement, id_category,remboursement);
		}

		// http://localhost:8080/modifier_remboursement/{id_reboursement}
				@PutMapping("/modifier_remboursement/{id_reboursement}")
				public void modifier_remboursement(@PathVariable("id_reboursement") int id_reboursement, @RequestBody Remboursement remboursement) {
					remboursementService.modifier_remboursement(id_reboursement,remboursement);
				}
	
				
	
	// http://localhost:8080/deleteRemboursement/{id_remboursement}
/*@DeleteMapping("/deleteRemboursement/{id_remboursement}")
	public void deleteRemboursement(@PathVariable("id_remboursement") int id_remboursement ) 
	{
		remboursementService.deleteRemboursement(id_remboursement);
	}*/
		
		// http://localhost:8080/deleteRemboursement/{id_remboursement}
		@PostMapping("/deleteRemboursement")
		public void deleteRemboursement(@RequestBody Remboursement remboursement ) 
		{
			remboursementService.deleteRemboursement(remboursement);
		}
	
		
		
		// http://localhost:8080/deleteRemboursement_byid/{id_remboursement}
		@DeleteMapping("/deleteRemboursement_byid/{id_remboursement}")
		public void deleteRemboursement_byid(@PathVariable("id_remboursement") int id_remboursement) 
		{
			remboursementService.deleteRemboursement_byid(id_remboursement);
		}
		
		// http://localhost:8080/valider_remboursement_by_id/{id_remboursement}
				@PutMapping("/valider_remboursement_by_id/{id_remboursement}")
				public void valider_remboursement_by_id(@PathVariable("id_remboursement") int id_remboursement) 
				{
					remboursementService.valider_remboursement_by_id(id_remboursement);

				}
				
		
		
		
	// http://localhost:8080/detailsRemboursement/{id_remboursement}
		@GetMapping("/detailsRemboursement/{id_remboursement}")
		public ResponseEntity<Remboursement> detailsRemboursement(@PathVariable("id_remboursement") int id_remboursement ) 
		{
			return remboursementService.detailsRemboursement(id_remboursement);
		}
		
		
		
	//  http://localhost:8080/recherche_bulletin/{search}
		@GetMapping("/recherche_bulletin/{search}")
		public List<Remboursement> recherche_bulletin(@PathVariable("search") String search)
		{
			  return  remboursementService.recherche_bulletin(search);
		}
		
		//  http://localhost:8080/calculer_nouvelle_mnt_dep/{search}
		@GetMapping("/calculer_nouvelle_mnt_dep/{id_adherent}/{id_category}/{valeur}")
		public double calculer_nouvelle_mnt_dep(@PathVariable("id_adherent") int id_adherent , @PathVariable("id_category") int id_category, @PathVariable("valeur") String v)
		{
		  double valeur =	Double.parseDouble(v);
			  return  remboursementService.calculer_nouvelle_mnt_dep(id_adherent,id_category,valeur);
		}
	
		
		
		// http://localhost:8080/afficher_remboursement_byadherent_byvalidation/{id_adherent}
		@GetMapping("/afficher_remboursement_byadherent_byvalidation/{id_adherent}")
		public List<Remboursement> afficher_remboursement_byadherent_byvalidation(@PathVariable("id_adherent") int id_adherent) 
		{
		//	int id_adherent = Integer.parseInt(id_sous_adherent);
			
			 return remboursementService.afficher_remboursement_byadherent_byvalidation(id_adherent);
		}
		
		
		
		
		
		
		
		// http://localhost:8080/select_tous_nom_category_byadherent/{id_adherent}
				@GetMapping("/select_tous_nom_category_byadherent/{id_adherent}")
				public String select_tous_nom_category_byadherent(@PathVariable("id_adherent") int id_adherent) 
				{
					
					 return remboursementService.select_tous_nom_category_byadherent(id_adherent);
				}
				
				
				
				
				
				// http://localhost:8080/select_tous_adherentbynum_bull
				@GetMapping("/select_tous_adherentbynum_bull/{num_bull}")
				public String select_tous_adherentbynum_bull(@PathVariable("num_bull") int num_bull) 
				{
					
					  return remboursementService.select_tous_adherentbynum_bull(num_bull);
					  
					
					// return remboursementService.select_tous_nom_category_byadherent();
				}
		
				
				// http://localhost:8080/select_num_bulletin_distinct
				@GetMapping("/select_num_bulletin_distinct")
				public List<Integer> select_num_bulletin_distinct() 
				{
					
					  return remboursementRepository.select_num_bulletin_distinct();
					  
					
					// return remboursementService.select_tous_nom_category_byadherent();
				}
		
				
				
				
	
				// http://localhost:8080/select_remboursementb_bynum_distinct
				@GetMapping("/select_remboursementb_bynum_distinct")
				public List<Remboursement> select_remboursementb_bynum_distinct() 
				{


					List<Remboursement> remboursements = new ArrayList<>();
					  for (int num : remboursementRepository.select_num_bulletin_distinct()) {
						  
						//  if(remboursementRepository.findbynumbulletin(num))
						//  {
						  
						  Remboursement remboursement = new Remboursement();


						  remboursement.setNumero_bulletin(num);
						  remboursement.setAdherent(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get());
						  remboursement.setChaine(remboursementService.select_tous_adherentbynum_bull(num));
						  remboursement.setNum_matricule(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getMatricule());
						  remboursement.setMontant_remboursement(remboursementRepository.somme_remboursement_byadherent_byvalidation(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId()));
						  remboursement.setMontant_dep(remboursementRepository.somme_dep_byadherent_byvalidation(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId()));
						  
						  remboursements.add(remboursement);
						//  }

					  }
					  
					  
					return remboursements;
					  
						
					} 
					  
					
					// return remboursementService.select_tous_nom_category_byadherent();
				@PostMapping("/valider_bordereau")
				public List<Integer> valider_bordereau(@RequestBody List<Remboursement> list_bordereau) 
				{
					long miliseconds = System.currentTimeMillis();
				      Date date = new Date(miliseconds);
					List<Integer> remboursements_search = new ArrayList<>();

						List<Remboursement> remboursements_list = new ArrayList<>();

					for (Remboursement remboursement_se : list_bordereau) {
							System.out.println("******************remboursement_se.getBordereau()*************"+remboursement_se.getNumero_bulletin());
							remboursements_search.add(remboursement_se.getNumero_bulletin());
						for (Remboursement element : remboursementRepository.findAll()) {
							if(element.getNumero_bulletin() == remboursement_se.getNumero_bulletin() )
							{
								if(element.getBordereau() == null)
									remboursements_list.add(element);
									
							}
							
						}
					}
						
				
					Bordereau		bordereau_save = null;
					System.out.println("*******remboursements_list.size()*******"+remboursements_list.size());
					if(remboursements_list.size() !=0)
						
					{Bordereau bordereau = new  Bordereau();
						
						bordereau.setDate(date);
				
						bordereau_save = 	bordereauRepository.save(bordereau);
					}
					//	bordereau_save.setNumero(bordereau.getId());
						//bordereauRepository.save(bordereau_save);
				List<Integer> remboursements = new ArrayList<>();
					for (Remboursement remboursement : list_bordereau) {
					
						for (Remboursement remboursement2 : remboursementRepository.findAll()) {
							if(remboursement2.getNumero_bulletin() == remboursement.getNumero_bulletin())
							{
								if(remboursement2.getBordereau()== null)
								remboursement2.setBordereau(bordereau_save);
								remboursementRepository.save(remboursement2);
							}
							
						}
					}
					return remboursements;
					//return remboursements_search;
					  
						
					} 
				
				
				// http://localhost:8080/selectremboursement_byadherent_bynumerobulletin
				@GetMapping("/selectremboursement_byadherent_bynumerobulletin/{id_adherent}/{num_bull}")
				public List<Remboursement> selectremboursement_byadherent_bynumerobulletin(@PathVariable("id_adherent") int id_adherent,@PathVariable("num_bull") int num_bull) 
				{
					
					  return remboursementService.selectremboursement_byadherent_bynumerobulletin(id_adherent,num_bull);
					  
					
					// return remboursementService.select_tous_nom_category_byadherent();
				}
				
				
				//ajouter le 13/08/2021
				// http://localhost:8080/selectremboursement_byadherent_bynumerobulletin
				@GetMapping("/selectremboursement_byadherent_bynumerobulletin_bordereau_not_null/{id_adherent}/{num_bull}")
				public List<Remboursement> selectremboursement_byadherent_bynumerobulletin_bordereau_not_null(@PathVariable("id_adherent") int id_adherent,@PathVariable("num_bull") int num_bull) 
				{
					
					  return remboursementService.selectremboursement_byadherent_bynumerobulletin_bordereau_not_null(id_adherent,num_bull);
					  
					
					// return remboursementService.select_tous_nom_category_byadherent();
				}
				
				
				
				
				
				// http://localhost:8080/select_remboursementb_bynum_distinct_by_bordereau/{id_borderau}
				@GetMapping("/select_remboursementb_bynum_distinct_by_bordereau/{id_bordereau}")
				public List<Remboursement> select_remboursementb_bynum_distinct_by_bordereau(@PathVariable("id_bordereau") int id_bordereau) 
				{

int a ;
					List<Remboursement> remboursements = new ArrayList<>();
					  for (int num : remboursementRepository.select_remboursementb_bynum_distinct_by_bordereau(id_bordereau)) {
						  Remboursement remboursement = new Remboursement();
						  if(bordereauRepository.change_status_bybodereau_byadh(id_bordereau,adherentRepository.findById(remboursementRepository.adherentbynum_bull_by_bordereau(num,id_bordereau)).get().getId(),num) !=0){
							 a= 1;

						  }
						  else
							  a= 0;

						  remboursement.setNumero_bulletin(num);
						  remboursement.setAdherent(adherentRepository.findById(remboursementRepository.adherentbynum_bull_by_bordereau(num,id_bordereau)).get());
						  remboursement.setChaine(remboursementService.select_tous_adherentbynum_bull_byidbordereau(num,id_bordereau));
						  remboursement.setNum_matricule(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getMatricule());
						  remboursement.setMontant_remboursement(remboursementRepository.somme_remboursement_byadherent_byvalidation_byidbordereau(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId(), id_bordereau));
						  remboursement.setMontant_dep(remboursementRepository.somme_dep_byadherent_byvalidation_byidbordereau(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId(), id_bordereau));
						  remboursement.setStatus(a);
						  remboursements.add(remboursement);

					  }
					return remboursements;
					  
						
					}
				
				
				
				
				@GetMapping("/recherche_numero_bulletin1/{valeur}")
				public int ajouterRemboursement( @PathVariable("valeur") int valeur ) 
				{
					 return remboursementRepository.findByNumero_b(valeur);
				}
				
				
				
				
				
				// http://localhost:8080/status_duree_remboursement_byadherent
				@GetMapping("/status_duree_remboursement_byadherent/{id_adherent}/{id_category}")
				public Boolean status_duree_remboursement_byadherent(@PathVariable("id_adherent") int id_adherent,@PathVariable("id_category") int id_category) 
				{
					
					  return remboursementService.status_duree_remboursement_byadherent(id_adherent,id_category);
					  
					
					// return remboursementService.select_tous_nom_category_byadherent();
				}
				
				
				// http://localhost:8080/ajouter_remboursement_reclamation/{id_remboursement}
			/*	@GetMapping("/ajouter_remboursement_cas_reclamation/{id_remboursement}")
				public void ajouter_remboursement_cas_reclamation(@PathVariable("id_remboursement") int id_remboursement) 
				{
					   remboursementService.ajouter_remboursement_cas_reclamation(id_remboursement);
				}*/
				
				
	

}
