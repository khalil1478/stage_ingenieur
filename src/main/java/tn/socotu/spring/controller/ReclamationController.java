package tn.socotu.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;  

import tn.socotu.spring.entities.Bordereau;
import tn.socotu.spring.entities.Reclamation;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.ReclamationRepository;
import tn.socotu.spring.repository.RemboursementRepository;
import tn.socotu.spring.service.RemboursementService;

@RestController
public class ReclamationController {

	
	@Autowired
	RemboursementRepository   remboursementRepository ;
	@Autowired
	ReclamationRepository   reclamationRepository ;
	
	@Autowired
	RemboursementService remboursementService;
	
	@Autowired
	AdherentRepository adherentRepository;
	
	// http://localhost:8080/ajouterReclamation/{id_reboursement}
		@PostMapping("/ajouterReclamation/{id_reboursement}")
		public void ajouterReclamation(@PathVariable("id_reboursement") int id_reboursement) 
		{
			 Reclamation reclamation = new Reclamation();
			 reclamation.setRemboursement(remboursementRepository.findById(id_reboursement).get());
			 reclamationRepository.save(reclamation);
			 
			 
		}
		
		
		// http://localhost:8080/ajouterReclamation/{id_reboursement}
			@GetMapping("/selectReclamations")
			public List<Remboursement> selectReclamations() 
			{		List<Remboursement> list = new ArrayList<>();

				for (Reclamation reclamation : reclamationRepository.findAll()) {
					if(reclamation.getValidation() == 0)
					{
					list.add(reclamation.getRemboursement());
					}
				}
				return list;
				
				
				 
				 
			}
			
						// http://localhost:8080/delete_reclamation
						@DeleteMapping("/delete_reclamation/{id_reboursement}")
						public void delete_reclamation(@PathVariable("id_reboursement") int id_reboursement) 
						{								
							Reclamation r =	reclamationRepository.recherche_reclamation_byid_remboursement(id_reboursement);
							reclamationRepository.deleteById(r.getId());
						}
						
						
						
						
						// http://localhost:8080/valider_reclamations
						@PutMapping("/valider_reclamations")
						public void valider_reclamations(@RequestBody Remboursement[] remboursements) 
						{		
							for (Remboursement remboursement : remboursements) {
								Reclamation r =	reclamationRepository.recherche_reclamation_byid_remboursement(remboursement.getId());
								r.setValidation(1);
								reclamationRepository.save(r);
							}
							
						}
						
						
						// http://localhost:8080/valider_reclamations_withdate
						@PutMapping("/valider_reclamations_withdate/{date}")
						public void valider_reclamations(@RequestBody Remboursement[] remboursements,@PathVariable("date") String date) throws ParseException 
						{		
							SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");  
						    Date date2=formatter2.parse(date);  
							for (Remboursement remboursement : remboursements) {
								Reclamation r =	reclamationRepository.recherche_reclamation_byid_remboursement(remboursement.getId());
							remboursement.setMontant_remboursement(remboursement.getMontant_remboursement()+ r.getRemboursement().getEcart());
							remboursement.setEcart(0);
							remboursement.setType("-");
							remboursementRepository.save(remboursement);
							
							
							r.setValidation(1);
								r.setDate_arrivees(date2);
								reclamationRepository.save(r);
							}
							
						}
						
						
						
						// http://localhost:8080/non_valider_reclamations_withdate
						@PutMapping("/non_valider_reclamations_withdate/{date}")
						public void non_valider_reclamations_withdate(@RequestBody Remboursement[] remboursements,@PathVariable("date") String date) throws ParseException 
						{		
							SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");  
						    Date date2=formatter2.parse(date);  
							for (Remboursement remboursement : remboursements) {
								Reclamation r =	reclamationRepository.recherche_reclamation_byid_remboursement(remboursement.getId());												
								r.setValidation(2);
								r.setDate_arrivees(date2);
								reclamationRepository.save(r);
							}
							
						}
						
						
						
						
						// http://localhost:8080/recherche_remboursement_in_reclamations/{id_reboursement}
						@GetMapping("/recherche_remboursement_in_reclamations/{key}")
						public List<Remboursement> recherche_remboursement_in_reclamations(@PathVariable("key") String key) 
						{		
							int a = Integer.parseInt(key);
							List<Remboursement> list = new ArrayList<>();

						for (Reclamation  reclamation : reclamationRepository.recherche_remboursement_in_reclamations(a)) {
							list.add(reclamation.getRemboursement());
						}
						return list; 

							}
						
						
						
						
						// ajouter 20/08/2021

						@GetMapping("/recherche_bulletins_arrivees_search/{key}")
						public List<Remboursement> recherche_bulletins_arrivees_search(@PathVariable("key") String key) {
							List<Remboursement> remboursements =new ArrayList<>();
								int num =Integer.parseInt(key);
									  
									//  if(remboursementRepository.findbynumbulletin(num))
									//  {
									  
									  Remboursement remboursement = new Remboursement();


									  remboursement.setNumero_bulletin(num);
									  remboursement.setAdherent(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get());
									 remboursement.setChaine(remboursementService.select_tous_adherentbynum_bull_pour_bulletins_arrivees(num));
									  remboursement.setNum_matricule(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getMatricule());
									  remboursement.setMontant_remboursement(remboursementRepository.somme_remboursement_byadherent_byvalidation_pour_bulletins_arrivess(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId()));
									  remboursement.setMontant_dep(remboursementRepository.somme_dep_byadherent_byvalidation_pour_bulletins_arrivess(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId()));
									  
									  remboursements.add(remboursement);
									return remboursements;
						}
						
						
						
						// ajouter 20/08/2021
						
						// http://localhost:8080/select_remboursementb_bynum_distinct_bulletins_arrivees
						@GetMapping("/select_remboursementb_bynum_distinct_bulletins_arrivees")
						public List<Remboursement> select_remboursementb_bynum_distinct_bulletins_arrivees() 
						{


							List<Remboursement> remboursements = new ArrayList<>();
							  for (int num : remboursementRepository.select_num_bulletin_distinct_pour_bulletins_arrivess()) {
								  
								//  if(remboursementRepository.findbynumbulletin(num))
								//  {
								  
								  Remboursement remboursement = new Remboursement();




								  remboursement.setNumero_bulletin(num);
								  remboursement.setAdherent(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get());
								 remboursement.setChaine(remboursementService.select_tous_adherentbynum_bull_pour_bulletins_arrivees(num));
								  remboursement.setNum_matricule(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getMatricule());
								  remboursement.setMontant_remboursement(remboursementRepository.somme_remboursement_byadherent_byvalidation_pour_bulletins_arrivess(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId()));
								  remboursement.setMontant_dep(remboursementRepository.somme_dep_byadherent_byvalidation_pour_bulletins_arrivess(adherentRepository.findById(remboursementRepository.adherentbynum_bull(num)).get().getId()));
								  
								  remboursements.add(remboursement);
								//  }

							  }
							  
							  
							return remboursements;
							  
								
							} 
						
						// http://localhost:8080/list_remboursement_checked
						@PostMapping("/list_remboursement_checked/{date}/{numero}")
						public  void list_remboursement_checked(@PathVariable("date") String date,@PathVariable("numero") String numero,@RequestBody List<Integer> list) throws ParseException
						{
							System.out.println(date);
							System.out.println(numero);

							  SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");  
							    Date date2=formatter2.parse(date);  
							    System.out.println("*************************************");

System.out.println(date2);
							for (Integer nb : list) {
								 for ( Remboursement remboursement :remboursementRepository.select_tous_adherentbynum_bull_pour_bulletins_arrivess(nb))
										 {
									 remboursement.setDate_arrivees(date2);
									 remboursement.setNumero_bordereau_arrivees(Integer.parseInt(numero));
									 remboursementRepository.save(remboursement);
							 			System.out.println("************************************************");

									 			System.out.println(remboursement.toString());
									 			System.out.println("************************************************");

										 }

							}
							
						}
							
							
							 
							 
						
						
}
