package tn.socotu.spring.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.entities.Type_adh;
import tn.socotu.spring.entities.Type_social;
import tn.socotu.spring.entities.Ville;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.RemboursementRepository;

@Service
public class ChartService {


	@Autowired
	AdherentRepository adherentRepository;
	@Autowired
	RemboursementRepository remboursementRepository;
	
	public  List<Adherent> select_chart_byville() {
		// TODO Auto-generated method stub
		
		java.util.List<Adherent> list_Adherent = new ArrayList<>();

	for (String  ville : adherentRepository.select_chart_byville()) {

		Adherent a = new Adherent();
		Ville villeStatus = Ville.valueOf(ville);
			a.setVille(villeStatus);
			a.setNbr_ville(adherentRepository.countville(villeStatus));
			list_Adherent.add(a);
		} 
	
	return list_Adherent;
		
		
		
	}
	
	public  List<Adherent> select_chart_bytypeadh() {
		// TODO Auto-generated method stub
		
		java.util.List<Adherent> list_Adherent = new ArrayList<>();

	for (String  typeadh : adherentRepository.select_chart_bytypeadh()) {

		Adherent a = new Adherent();
		Type_adh typeadhStatus = Type_adh.valueOf(typeadh);
			a.setTypeadh(typeadhStatus);
			a.setNbr_typead(adherentRepository.countville(typeadhStatus));
			list_Adherent.add(a);
		} 
	
	return list_Adherent;
		
		
		
	}

	public List<Remboursement> select_chart_remboursement_byannee() {
		// TODO Auto-generated method stub
		java.util.List<Integer> list_annees = new ArrayList<>();
		java.util.List<Integer> list_sommes = new ArrayList<>();
		java.util.List<Remboursement> list_remboursements = new ArrayList<>();
		for (Remboursement remboursement : remboursementRepository.findAll() ) {
		int year = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(remboursement.getDate())).getYear();
		if(!list_annees.contains(year) && remboursement.getValidation() == 1)
			list_annees.add(year);
		
		}

		
		for (Integer annee : list_annees) {
			Remboursement r = new Remboursement();

			System.out.println("***********annee*********************"+ annee);
			r.setValeur(annee);
			 double somme = remboursementRepository.somme_mnt_remboursement_byyear(annee);
			 System.out.println("***************somme*****************"+somme);
			r.setMontant_remboursement(somme);
			list_remboursements.add(r);

		}


	/*	for (Remboursement remboursement : remboursementRepository.findAll() ) {
			int year = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(remboursement.getDate())).getYear();*/
			
			
			
		/*	if(year == annee && remboursement.getValidation() == 1) 
			{
				
			}*/
			
	//	}
		
				
		return list_remboursements;
	}
	
	

}
