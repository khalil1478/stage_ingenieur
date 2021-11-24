package tn.socotu.spring.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Category;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.entities.ResourceNotFoundException;
import tn.socotu.spring.entities.Type_contrat;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.CategoryRepository;
import tn.socotu.spring.repository.DocteurRepository;
import tn.socotu.spring.repository.Numero_BulletinRepository;
import tn.socotu.spring.repository.RemboursementRepository;


@Service
public class RemboursementService {

	@Autowired
	 GmailService gmailService;

	@Autowired
	RemboursementRepository remboursementRepository;
	@Autowired
	AdherentRepository adherentRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	Numero_BulletinRepository  numeroRepository;
	
	@Autowired
	DocteurRepository docteurRepository;

	  long miliseconds = System.currentTimeMillis();
      Date date = new Date(miliseconds);	
		

	
	

	
	
	
	
	
	
	
	
	
	//*********************************************methodejour_vers2*************************************************************//
	
	
	public void methodejour_vers2(Remboursement remboursement,int numero, int id_adherent, int id_category) {
		
		


		Adherent adherent_search = adherentRepository.findById(id_adherent).get();
		Category category_search = categoryRepository.findById(id_category).get();

	
		if(remboursement.getStatus_mnt_remboursement() == 1)
		{
			remboursement.setMontant_remboursement(category_search.getMax());
		remboursement.setMontant_remboursement_proximites(category_search.getMax());
		}
		else if(remboursement.getStatus_mnt_remboursement() == 2)
		{System.out.println("*************************************remboursement.getStatus_mnt_remboursement() == 2**************************");
			double somme_remboursement = remboursementRepository.somme_remboursement_byadherent(adherent_search.getId(), category_search.getId());

			remboursement.setMontant_remboursement(category_search.getMax()-somme_remboursement);
			remboursement.setMontant_remboursement_proximites(category_search.getMax()-somme_remboursement);
		}else
		{
			System.out.println("*************************************remboursement.getStatus_mnt_remboursement() == 0**************************");
		remboursement.setMontant_remboursement(remboursement.getJour() * category_search.getMontant());
		remboursement.setMontant_remboursement_proximites(remboursement.getJour() * category_search.getMontant());
		}
		remboursement.setAdherent(adherent_search);
	
			 remboursement.setNumero_bulletin(numero);

		if(adherent_search.getIdadherent()==0)
			remboursement.setId_sup_adh(adherent_search.getId());
			else
			{
			remboursement.setId_sup_adh(adherent_search.getIdadherent());	
			}	
		remboursement.setCategory(category_search);
	remboursement.setDate(date);
	remboursement.setType("-");
		remboursementRepository.save(remboursement);
		
	}
	
	//*********************************************methodejour_vers2*************************************************************//

	
	
	
	
	//*********************************************methode_vers2*************************************************************//
	
	
	public void methode_vers2(Remboursement remboursement,int numero, int id_adherent, int id_category) {
		
		


		Adherent adherent_search = adherentRepository.findById(id_adherent).get();
		Category category_search = categoryRepository.findById(id_category).get();

	
		if(remboursement.getStatus_mnt_remboursement() == 1)
		{
			remboursement.setMontant_remboursement(category_search.getMax());
			remboursement.setMontant_remboursement_proximites(category_search.getMax());

			
		}
		else if(remboursement.getStatus_mnt_remboursement() == 2)
		{System.out.println("*************************************remboursement.getStatus_mnt_remboursement() == 2**************************");
			double somme_remboursement = remboursementRepository.somme_remboursement_byadherent(adherent_search.getId(), category_search.getId());

			remboursement.setMontant_remboursement(category_search.getMax()-somme_remboursement);
			remboursement.setMontant_remboursement_proximites(category_search.getMax()-somme_remboursement);
		}else
		{
			System.out.println("*************************************remboursement.getStatus_mnt_remboursement() == 0**************************");
		remboursement.setMontant_remboursement(remboursement.getValeur() * category_search.getMontant());
		remboursement.setMontant_remboursement_proximites(remboursement.getValeur() * category_search.getMontant());
		}
		remboursement.setAdherent(adherent_search);
		
			 remboursement.setNumero_bulletin(numero);

		if(adherent_search.getIdadherent()==0)
			remboursement.setId_sup_adh(adherent_search.getId());
			else
			{
			remboursement.setId_sup_adh(adherent_search.getIdadherent());	
			}	
	//	remboursement.setCategory(category_search);
		remboursement.setCategory(category_search);
	remboursement.setDate(date);
	remboursement.setType("-");

		remboursementRepository.save(remboursement);
		
	}
	
	//*********************************************methode_vers2*************************************************************//

	//*********************************************methode_frais_vers2*************************************************************//

	public void methode_frais_vers2(Remboursement remboursement, int numero, int id_adherent, int id_category/*,int id_docteur*/) {

		Adherent adherent_search = adherentRepository.findById(id_adherent).get();
		Category category_search = categoryRepository.findById(id_category).get();
		if(remboursement.getStatus_mnt_remboursement() == 1)
		{
			remboursement.setMontant_remboursement(category_search.getMax());
			remboursement.setMontant_remboursement_proximites(category_search.getMax());

		}
		else if(remboursement.getStatus_mnt_remboursement() == 2)
		{System.out.println("*************************************remboursement.getStatus_mnt_remboursement() == 2**************************");
			double somme_remboursement1 = remboursementRepository.somme_remboursement_byadherent(adherent_search.getId(), category_search.getId());

			remboursement.setMontant_remboursement(category_search.getMax()-somme_remboursement1);
			remboursement.setMontant_remboursement_proximites(category_search.getMax()-somme_remboursement1);

		}
			else
			{
				System.out.println("*************************************remboursement.getStatus_mnt_remboursement() == 0**************************");
		remboursement.setMontant_remboursement(remboursement.getMontant_dep() * (category_search.getMontant() / 100));
		remboursement.setMontant_remboursement_proximites(remboursement.getMontant_dep() * (category_search.getMontant() / 100));

			}
		remboursement.setAdherent(adherent_search);
		
		 remboursement.setNumero_bulletin(numero);

		if(adherent_search.getIdadherent()==0)
			remboursement.setId_sup_adh(adherent_search.getId());
			else
			remboursement.setId_sup_adh(adherent_search.getIdadherent());	
		
		remboursement.setCategory(category_search);
		remboursement.setDate(date);
		remboursement.setType("-");


		remboursementRepository.save(remboursement);
	}
	//*********************************************methode_frais_vers2*************************************************************//
	
	
	//*********************************************methode_constant_vers2*************************************************************//

	
	public void methode_constant_vers2(Remboursement remboursement,  int numero,int id_adherent, int id_category/*,int id_docteur*/) {
		Adherent adherent_search = adherentRepository.findById(id_adherent).get();

		Category category_search = categoryRepository.findById(id_category).get();

	
		if(remboursement.getStatus_mnt_remboursement() == 1)
		{
			
		
			remboursement.setMontant_remboursement(category_search.getMontant());
			remboursement.setMontant_remboursement_proximites(category_search.getMontant());
		}
		else if(remboursement.getStatus_mnt_remboursement() == 2)
		{
			System.out.println("*************************************remboursement.getStatus_mnt_remboursement() == 0**************************");
		remboursement.setMontant_remboursement(category_search.getMontant());
		remboursement.setMontant_remboursement_proximites(category_search.getMontant());

		}
		remboursement.setAdherent(adherent_search);
			

		 remboursement.setNumero_bulletin(numero);
		if(adherent_search.getIdadherent()==0)
			remboursement.setId_sup_adh(adherent_search.getId());
			else
			remboursement.setId_sup_adh(adherent_search.getIdadherent());	
		remboursement.setCategory(category_search);
		remboursement.setDate(date);
		remboursement.setType("-");


		remboursementRepository.save(remboursement);
	}
	//*********************************************methode_constant_vers2*************************************************************//

	
	
	
	
	
	
	
	
		

	public void deleteRemboursement(Remboursement remboursement) {

		remboursementRepository.delete(remboursement);
	}
	

	public ResponseEntity<Remboursement> detailsRemboursement(int id_remboursement) {

		Remboursement remboursement = remboursementRepository.findById(id_remboursement)
				.orElseThrow(() -> new ResourceNotFoundException("Remboursement not exist with id :" + id_remboursement));
		return ResponseEntity.ok(remboursement);




	}

	public List<Remboursement> afficher_remboursement_byadherent(int id_adherent) {

		Adherent adherent_search = adherentRepository.findById(id_adherent).get();

		if (adherent_search.getIdadherent() == 0) {
			return remboursementRepository.select_reboursement_byadherentsup(adherent_search.getId());
		}

		else {
			return remboursementRepository.select_reboursement_bysousadh(adherent_search.getId());
		}

	}

	
	
	public List<Remboursement> recherche_bulletin(String search) {
		
		
		return remboursementRepository.recherche_bulletin(search);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void ajouterRemboursement_ver2(Remboursement remboursement, int numero, int id_adherent, int id_category
			) {

		Adherent adherent_search = adherentRepository.findById(id_adherent).get();
		Category category_search = categoryRepository.findById(id_category).get();
	

		// ****************************************************UNITE**************************************************************//

		if (category_search.getType().equals(Type_contrat.UNITE)) {
			if (category_search.getMax() != 0) {

				

if(adherent_search.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(adherent_search.getId(),category_search.getId()) == 0 )
					{
					
						double mnt = remboursement.getValeur() * category_search.getMontant();
						
						

						if (category_search.getMax() < mnt )
						{	
						remboursement.setStatus_mnt_remboursement(1);
						methode_vers2(remboursement,numero, id_adherent, id_category);
						}

						else
							methode_vers2(remboursement,numero, id_adherent, id_category);
					}
					
					
				
else
{
				

					double mnt = remboursement.getValeur() * category_search.getMontant();
					double somme_remboursement = remboursementRepository.somme_remboursement_byadherent(adherent_search.getId(), category_search.getId());
					if (category_search.getMax() > (mnt + somme_remboursement))
						methode_vers2(remboursement,numero, id_adherent, id_category);
					else
					{
						remboursement.setStatus_mnt_remboursement(2);
						methode_vers2(remboursement,numero, id_adherent, id_category);
					}

}						
			}
			
			else
				methode_vers2(remboursement, numero,id_adherent, id_category);

					
	
		}
		
		// ****************************************************UNITE**************************************************************//

		
		// ****************************************************FRAIS**************************************************************//

		if (category_search.getType().equals(Type_contrat.FRAIS)) {
			
			
			
			
			// ****************************************************category_search dispose un maximum**************************************************************//

			if (category_search.getMax() != 0) 
			{
	if(adherent_search.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(adherent_search.getId(),category_search.getId()) == 0  )
						{
							double mnt = remboursement.getMontant_dep() * (category_search.getMontant() / 100);
							
							// ****************************************************adherent_search dépasse max**************************************************************//

							if (category_search.getMax() < mnt )
							{
								remboursement.setStatus_mnt_remboursement(1);
								methode_frais_vers2(remboursement,numero, id_adherent, id_category);
							}
						else
								methode_frais_vers2(remboursement,numero, id_adherent, id_category);
							
							// ****************************************************adherent_search ne dépasse pas max**************************************************************//
						}
	else
								{
									double mnt1 = remboursement.getMontant_dep() * (category_search.getMontant() / 100);
									
									double somme_remboursement = remboursementRepository.somme_remboursement_byadherent(adherent_search.getId(), category_search.getId());
									if (category_search.getMax() > (mnt1 + somme_remboursement))
										methode_frais_vers2(remboursement, numero,id_adherent, id_category);
									else
									{
										remboursement.setStatus_mnt_remboursement(2);
										methode_frais_vers2(remboursement,numero, id_adherent, id_category);
										
									}
								}
			

			}
			else
				methode_frais_vers2(remboursement,numero, id_adherent, id_category);
			
								
								
								
			
		
		
		// ****************************************************FRAIS**************************************************************//

	}
		
		
		// ****************************************************CONSTANT**************************************************************//

		if (category_search.getType().equals(Type_contrat.CONSTANT)) {
			

	if(adherent_search.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(adherent_search.getId(),category_search.getId()) == 0 )
						{
						
							double mnt = category_search.getMontant();
							
							
							remboursement.setStatus_mnt_remboursement(1);
							methode_constant_vers2(remboursement,numero, id_adherent, id_category);	
						
						}
						
						
					
	else
	{
					

							remboursement.setStatus_mnt_remboursement(2);
							methode_constant_vers2(remboursement,numero, id_adherent, id_category);	

	}						
				

						
		
			}
			
			
			
			
			
			
			
			
			

			
			
			
			
	

		// ****************************************************CONSTANT**************************************************************//
	
		
		
		
		
		
		
		
		// ****************************************************Jour**************************************************************//

		if (category_search.getType().equals(Type_contrat.JOUR)) {
			if (category_search.getMax() != 0) {

				

if(adherent_search.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(adherent_search.getId(),category_search.getId()) == 0 )
					{
					
						double mnt = remboursement.getJour() * category_search.getMontant();
						
						

						if (category_search.getMax() < mnt )
						{	
						remboursement.setStatus_mnt_remboursement(1);
						methodejour_vers2(remboursement,numero, id_adherent, id_category);
						}

						else
							methodejour_vers2(remboursement,numero, id_adherent, id_category);
					}
					
					
				
else
{
				

					double mnt = remboursement.getJour() * category_search.getMontant();
					double somme_remboursement = remboursementRepository.somme_remboursement_byadherent(adherent_search.getId(), category_search.getId());
					if (category_search.getMax() > (mnt + somme_remboursement))
						methodejour_vers2(remboursement,numero, id_adherent, id_category);
					else
					{
						remboursement.setStatus_mnt_remboursement(2);
						methodejour_vers2(remboursement,numero, id_adherent, id_category);
					}

}						
			}
			
			else
				methodejour_vers2(remboursement, numero,id_adherent, id_category);

					
	
		}
		
		// ****************************************************JOUR**************************************************************//	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	// ****************************************************calculer_nouvelle_mnt_dep**************************************************************//

	public double calculer_nouvelle_mnt_dep(int id_adherent, int id_category, double valeur) {
		double a = 0 ;
		Adherent adherent_search_nouvelle = adherentRepository.findById(id_adherent).get();
		Category category_search_nouvelle = categoryRepository.findById(id_category).get();
		
			if (category_search_nouvelle.getType().equals(Type_contrat.CONSTANT)) {
				if(adherent_search_nouvelle.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(category_search_nouvelle.getId(),category_search_nouvelle.getId()) == 0  )
				{
					a = category_search_nouvelle.getMontant();
				}
				else
				{
                        
				a =category_search_nouvelle.getMontant();
				}
				
			}
			if(category_search_nouvelle.getType().equals(Type_contrat.FRAIS))
			{
				
				if(adherent_search_nouvelle.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(category_search_nouvelle.getId(),category_search_nouvelle.getId()) == 0  )
				{
					a = valeur * (category_search_nouvelle.getMontant() / 100)  ;	
					
					}
			
			else
			{
				a =  (valeur * (category_search_nouvelle.getMontant() / 100)) /*+ remboursementRepository.somme_remboursement_byadherent(adherent_search_nouvelle.getId(), category_search_nouvelle.getId()) */;
				 
			}
		
		}
			
			if(category_search_nouvelle.getType().equals(Type_contrat.UNITE))
			{
				
				if(adherent_search_nouvelle.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(category_search_nouvelle.getId(),category_search_nouvelle.getId()) == 0  )
				{
					a = valeur * (category_search_nouvelle.getMontant() )  ;	
					
					}
			
			else
			{
				a =  (valeur * category_search_nouvelle.getMontant() ) + remboursementRepository.somme_remboursement_byadherent(adherent_search_nouvelle.getId(), category_search_nouvelle.getId()) ;
				 
			}
		
		}
			//***************************************************//
			if(category_search_nouvelle.getType().equals(Type_contrat.JOUR))
			{
				
				if(adherent_search_nouvelle.getRemboursements().size() ==0 || remboursementRepository.count_categoryby_adherent(category_search_nouvelle.getId(),category_search_nouvelle.getId()) == 0  )
				{
					a = valeur * (category_search_nouvelle.getMontant() )  ;	
					
					}
			
			else
			{
				a =  (valeur * category_search_nouvelle.getMontant() ) + remboursementRepository.somme_remboursement_byadherent(adherent_search_nouvelle.getId(), category_search_nouvelle.getId()) ;
				 
			}
		
		}
			//****************************************************//
			
		return a;
	}

	// ****************************************************calculer_nouvelle_mnt_dep**************************************************************//

	
	
	
	
	
	
	
	
	
	
	
	// ****************************************************afficher_remboursement_byadherent_byvalidation**************************************************************//


	public List<Remboursement> afficher_remboursement_byadherent_byvalidation(int id_adherent) {
		
		 return remboursementRepository.afficher_remboursement_byadherent_byvalidation(id_adherent);
		

	}


	
	// ****************************************************afficher_remboursement_byadherent_byvalidation**************************************************************//

	public void deleteRemboursement_byid(int id_remboursement) {
	remboursementRepository.deleteById(id_remboursement);
		
		
		
	}

	public void valider_remboursement_by_id(int id_remboursement) {
		Remboursement rembour =remboursementRepository.findById(id_remboursement).get();
		rembour.setValidation(1);
		remboursementRepository.save(rembour);
	}

	
	
	
	
	
	public String select_tous_nom_category_byadherent(int id_adherent) {
		String chaine = "";
		
		for (Remboursement remboursement : remboursementRepository.selectremboursement_byadherent_byvalidation_de1(id_adherent)) {
			
			if(remboursement.getCategory().getType().equals(Type_contrat.UNITE))
				chaine = chaine + remboursement.getCategory().getLibelle() + remboursement.getValeur() +" = "+ remboursement.getMontant_dep() +"  + ";
			else
			chaine = chaine + remboursement.getCategory().getLibelle() +" = "+ remboursement.getMontant_dep() +" + ";
			
		}
		
		chaine = chaine.substring(0, chaine.length()-1);
		return chaine;
	}
	
	
	
	
	

	public String select_tous_adherentbynum_bull(int num_bull) {
		String chaine = "";

for (Remboursement remboursement : remboursementRepository.select_tous_adherentbynum_bull(num_bull)) {
			
			if(remboursement.getCategory().getType().equals(Type_contrat.UNITE))
				chaine = chaine + remboursement.getCategory().getLibelle() + remboursement.getValeur() +" = "+ remboursement.getMontant_dep() +"  + ";
			else
			chaine = chaine + remboursement.getCategory().getLibelle() +" = "+ remboursement.getMontant_dep() +"  + ";
			
		}

chaine = chaine.substring(0, chaine.length()-2);

		return chaine;
	
		
	}

	public List<Remboursement> selectremboursement_byadherent_bynumerobulletin(int id_adherent, int num_bull) {
		return remboursementRepository.selectremboursement_byadherent_bynumerobulletin(id_adherent,num_bull);
	}
	
	//ajouter le 13/08/2021

	public List<Remboursement> selectremboursement_byadherent_bynumerobulletin_bordereau_not_null(int id_adherent, int num_bull) {
		return remboursementRepository.selectremboursement_byadherent_bynumerobulletin_bordereau_not_null(id_adherent,num_bull);
	}
	
	

	public String select_tous_adherentbynum_bull_byidbordereau(int num_bull,int id_bordereau) {
		String chaine = "";

for (Remboursement remboursement : remboursementRepository.select_tous_adherentbynum_bull_byidbordereau(num_bull,id_bordereau)) {
			
			if(remboursement.getCategory().getType().equals(Type_contrat.UNITE))
				chaine = chaine + remboursement.getCategory().getLibelle() + remboursement.getValeur() +" = "+ remboursement.getMontant_dep() +"  + ";
			else
			chaine = chaine + remboursement.getCategory().getLibelle() +" = "+ remboursement.getMontant_dep() +"  + ";
			
		}

chaine = chaine.substring(0, chaine.length()-2);

		return chaine;
	
		
	}

	public void modifier_remboursement_bycategory(int id_reboursement, int id_category, Remboursement remboursement  ) {
		Remboursement remboursement_to_update = remboursementRepository.findById(id_reboursement).get();
		Category category_search_remboursement = categoryRepository.findById(id_category).get();
		
		
		remboursement_to_update.setDate(remboursement.getDate());
		remboursement_to_update.setMontant_dep(remboursement.getMontant_dep());
		remboursement_to_update.setMontant_remboursement(remboursement.getMontant_remboursement());
		remboursement_to_update.setNum_matricule(remboursement.getNum_matricule());
		remboursement_to_update.setValeur(remboursement.getValeur());
		double a =remboursement.getMontant_remboursement_proximites()-remboursement.getMontant_remboursement();
		
		remboursement_to_update.setEcart(a);
		remboursement_to_update.setCategory(remboursement.getCategory());
		remboursement_to_update.setCategory(category_search_remboursement);
		remboursement_to_update.setMontant_remboursement_proximites(remboursement.getMontant_remboursement_proximites());
		remboursement_to_update.setType(remboursement.getType());
		remboursement_to_update.setJour(remboursement.getJour());

		remboursementRepository.save(remboursement_to_update);
	}
	
	public void modifier_remboursement(int id_reboursement, Remboursement remboursement) {
		Remboursement remboursement_to_update = remboursementRepository.findById(id_reboursement).get();

		remboursement_to_update.setDate(remboursement.getDate());
		remboursement_to_update.setMontant_dep(remboursement.getMontant_dep());
		remboursement_to_update.setMontant_remboursement(remboursement.getMontant_remboursement());
		remboursement_to_update.setNum_matricule(remboursement.getNum_matricule());
		remboursement_to_update.setValeur(remboursement.getValeur());
		double a =remboursement.getMontant_remboursement_proximites()-remboursement.getMontant_remboursement();
		System.out.println("*************************a"+a);

		remboursement_to_update.setEcart(a);
		remboursement_to_update.setCategory(remboursement.getCategory());
		remboursement_to_update.setMontant_remboursement_proximites(remboursement.getMontant_remboursement_proximites());
		remboursement_to_update.setType(remboursement.getType());
		remboursement_to_update.setJour(remboursement.getJour());

		remboursementRepository.save(remboursement_to_update);
	}

	
	
	public Remboursement modifier_ecart_byremboursement(int id_reboursement, String ecart,String  type) {
		Remboursement remboursement_trouver = remboursementRepository.findById(id_reboursement).get();
		
		
		double ecart_convert = Double.parseDouble(ecart);
		remboursement_trouver.setType(type);
		double a = remboursement_trouver.getMontant_remboursement_proximites()- ecart_convert  ;
		remboursement_trouver.setEcart(a);
		remboursement_trouver.setMontant_remboursement(ecart_convert);
		Remboursement r =remboursementRepository.save(remboursement_trouver);
		return r;

		
		
		
		

		
	}

	public Boolean status_duree_remboursement_byadherent(int id_adherent, int id_category) {
		int year_current = Calendar.getInstance().get(Calendar.YEAR);
int annee = 0;
List<Integer> dates = new ArrayList<>();

		Category category_search = categoryRepository.findById(id_category).get();

		if(category_search.getDuree() != 0)
			
		{
		
	   int  ab = remboursementRepository.recuperer_annee_selon_duree_remboursement_byadherent(id_adherent,id_category);
	
		
		annee=  ab;

	   for (int i = 0; i < category_search.getDuree(); i++) 

{
	dates.add(annee);
	annee++;

}

for (Integer integer : dates) {
		
}
		}

if(dates.contains(year_current))
	return true;
else
		
		
		return false;
	
		
	}

	
	
	//ajouter le  20 /08/2021

	public String select_tous_adherentbynum_bull_pour_bulletins_arrivees(int num_bull) {
		String chaine = "";

for (Remboursement remboursement : remboursementRepository.select_tous_adherentbynum_bull_pour_bulletins_arrivess(num_bull)) {
			
			if(remboursement.getCategory().getType().equals(Type_contrat.UNITE))
				chaine = chaine + remboursement.getCategory().getLibelle() + remboursement.getValeur() +" = "+ remboursement.getMontant_dep() +"  + ";
			else
			chaine = chaine + remboursement.getCategory().getLibelle() +" = "+ remboursement.getMontant_dep() +"  + ";
			
		}

chaine = chaine.substring(0, chaine.length()-2);

		return chaine;
	
		
	}



	

	
}
