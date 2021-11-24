package tn.socotu.spring.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Assurance;
import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.AssuranceRepository;
import tn.socotu.spring.repository.RemboursementRepository;

@Service
public class AssuranceService {
	
	@Autowired	
	AssuranceRepository assuranceRepository;
	
	@Autowired	
	RemboursementRepository remboursementRepository;
	
	@Autowired
	AdherentRepository adherentRepository;
	
	public void modifier_assurance(int id_assurance, Assurance assurance) {
		Assurance assurance_to_update = assuranceRepository.findById(id_assurance).get();

		assurance_to_update.setMat_assurance(assurance.getMat_assurance());
		assurance_to_update.setName(assurance.getName());
		assurance_to_update.setDate_deb(assurance.getDate_deb());
		assurance_to_update.setDate_fin(assurance.getDate_fin());
		
		assuranceRepository.save(assurance_to_update);
	}



	public List<Integer> select_annee_byassurance(int id_assurance) {
		Assurance assurance = assuranceRepository.findById(id_assurance).get();
		
		 List<Integer> dates = new ArrayList<>();
	for (int i = assurance.getDate_deb(); i < assurance.getDate_fin(); i++) {
		dates.add(i);
		
	}
	return dates;
	}


	public	 List<Integer> select_mois_byannee(int annee) {
		int year_current = Calendar.getInstance().get(Calendar.YEAR);
		 List<Integer> dates_mois = new ArrayList<>();
		 	System.out.println("year_current    ** "+year_current);
		for (Remboursement remboursement : remboursementRepository.findAll()) {

			Date date = remboursement.getDate(); // the date instance
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
				if( annee  ==  calendar.get(Calendar.YEAR))
			{
				if(!dates_mois.contains(calendar.get(Calendar.MONTH)+1))
				dates_mois.add(calendar.get(Calendar.MONTH)+1);

				}
					
		}
		return dates_mois;
	}



	public List<Adherent> select_adherent_statistique_mois_byannee(int annee, String moisselect) {
		 List<Adherent> list_adherent = new ArrayList<>();

		
		if(moisselect== null)
			
		{
			for (Remboursement remboursement : remboursementRepository.findAll()) {
				Date date = remboursement.getDate(); // the date instance
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				if(calendar.get(Calendar.YEAR) ==annee)
				{
					if(!list_adherent.contains(remboursement.getAdherent()))
					list_adherent.add(remboursement.getAdherent());
				}
			}
			
		
		}
	
		else
		{
			for (Remboursement remboursement : remboursementRepository.findAll()) {
				Date date = remboursement.getDate(); // the date instance
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				if(calendar.get(Calendar.YEAR) ==annee)
				{
					if(calendar.get(Calendar.MONTH)+1 ==  Integer.parseInt(moisselect))
					{
						
					
					if(!list_adherent.contains(remboursement.getAdherent()))
					list_adherent.add(remboursement.getAdherent());
					}
				}
			}
			
		}
		
		return list_adherent;

	}



	public List<Remboursement> select_bulletin_byadherent_byannee(int id, int annee) {
		
		Adherent adherent = adherentRepository.findById(id).get();
		 List<Remboursement> list_remboursment = new ArrayList<>();
		 List<Remboursement> list_remboursment_byannee = new ArrayList<>();

		for (Remboursement remboursement : adherent.getRemboursements()) {
			list_remboursment.add(remboursement);
		}
		
		for (Remboursement remboursement : list_remboursment) {
			Date date = remboursement.getDate(); // the date instance
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.YEAR) ==annee)
			{
				list_remboursment_byannee.add(remboursement);
				}
			}
		
		

		return list_remboursment_byannee;
	}



	public List<Remboursement> select_bulletin_byadherent_byannee_bymois(int id, int annee, int mois) {
		Adherent adherent = adherentRepository.findById(id).get();
		 List<Remboursement> list_remboursment = new ArrayList<>();
		 List<Remboursement> list_remboursment_byannee_bymois = new ArrayList<>();

		for (Remboursement remboursement : adherent.getRemboursements()) {
			list_remboursment.add(remboursement);
		}
		
		for (Remboursement remboursement : list_remboursment) {
			Date date = remboursement.getDate(); // the date instance
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.YEAR) ==annee)
			{if(calendar.get(Calendar.MONTH)+1 == mois)
			{
				list_remboursment_byannee_bymois.add(remboursement);
			}
				}
			}
		
		

		return list_remboursment_byannee_bymois;
	}



	public double somme_mntdep_byadherent_byannee(int id, int annee) {
		
		Adherent adherent = adherentRepository.findById(id).get();
		 List<Remboursement> list_remboursment = new ArrayList<>();
		 List<Remboursement> list_remboursment_byannee = new ArrayList<>();

		for (Remboursement remboursement : adherent.getRemboursements()) {
			list_remboursment.add(remboursement);
		}
		
		for (Remboursement remboursement : list_remboursment) {
			Date date = remboursement.getDate(); // the date instance
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.YEAR) ==annee)
			{
				list_remboursment_byannee.add(remboursement);
				}
			}
	 double	somme = 0;
		for (Remboursement remboursement2 : list_remboursment_byannee) {
			
			somme = somme+remboursement2.getMontant_dep();
		}
		return somme;
	}



	public double somme_mntdep_byadherent_byannee_bymois(int id, int annee, int mois) {
		Adherent adherent = adherentRepository.findById(id).get();
		 List<Remboursement> list_remboursment = new ArrayList<>();
		 List<Remboursement> list_remboursment_byannee_bymois = new ArrayList<>();

		for (Remboursement remboursement : adherent.getRemboursements()) {
			list_remboursment.add(remboursement);
		}
		
		for (Remboursement remboursement : list_remboursment) {
			Date date = remboursement.getDate(); // the date instance
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.YEAR) ==annee)
			{
				
				if(calendar.get(Calendar.MONTH)+1 == mois)
			
			{
					list_remboursment_byannee_bymois.add(remboursement);
			}
			}
		}
		
		 double	somme = 0;
			for (Remboursement remboursement2 : list_remboursment_byannee_bymois) {
				
				somme = somme+remboursement2.getMontant_dep();
			}
			return somme;
		
	}



	public double somme_mntremb_byadherent_byannee(int id, int annee) {
		Adherent adherent = adherentRepository.findById(id).get();
		 List<Remboursement> list_remboursment = new ArrayList<>();
		 List<Remboursement> list_remboursment_byannee = new ArrayList<>();

		for (Remboursement remboursement : adherent.getRemboursements()) {
			list_remboursment.add(remboursement);
		}
		
		for (Remboursement remboursement : list_remboursment) {
			Date date = remboursement.getDate(); // the date instance
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.YEAR) ==annee)
			{
				list_remboursment_byannee.add(remboursement);
				}
			}
	 double	somme = 0;
		for (Remboursement remboursement2 : list_remboursment_byannee) {
			
			somme = somme+remboursement2.getMontant_remboursement();
		}
		return somme;
	}



	public double somme_mntremb_byadherent_byannee_bymois(int id, int annee, int mois) {
		Adherent adherent = adherentRepository.findById(id).get();
		 List<Remboursement> list_remboursment = new ArrayList<>();
		 List<Remboursement> list_remboursment_byannee_bymois = new ArrayList<>();

		for (Remboursement remboursement : adherent.getRemboursements()) {
			list_remboursment.add(remboursement);
		}
		
		for (Remboursement remboursement : list_remboursment) {
			Date date = remboursement.getDate(); // the date instance
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.YEAR) ==annee)
			{
				
				if(calendar.get(Calendar.MONTH)+1 == mois)
			
			{
					list_remboursment_byannee_bymois.add(remboursement);
			}
			}
		}
		
		 double	somme = 0;
			for (Remboursement remboursement2 : list_remboursment_byannee_bymois) {
				
				somme = somme+remboursement2.getMontant_remboursement();
			}
			return somme;
	}



	public void supprimer_assurance(int id_assurance) {
		assuranceRepository.deleteById((id_assurance));
	}
		
	
	
	
		 
		

}
