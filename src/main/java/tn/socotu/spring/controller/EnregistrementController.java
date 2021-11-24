package tn.socotu.spring.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Enregistrement;
import tn.socotu.spring.repository.EnregistrementRepository;

@RestController
public class EnregistrementController {
	@Autowired
	EnregistrementRepository enregistrementRepository;

	// http://localhost:8080/select_enfant_byyear
	@GetMapping("/select_enfant_byyear")
	public List<Adherent> select_enfant_byyear() {
		int year_current = Calendar.getInstance().get(Calendar.YEAR);
		return enregistrementRepository.select_enfant_bydatecurrent(year_current);
	}

	// http://localhost:8080/select_enfant_bydatecurrent_file_notnull
	@GetMapping("/select_enfant_bydatecurrent_file_notnull")
	public List<Adherent> select_enfant_bydatecurrent_file_notnull() {
		List<Adherent> list_adh = new ArrayList<>();
		List<Enregistrement> list = new ArrayList<>();

		int year_current = Calendar.getInstance().get(Calendar.YEAR);

		/* for(Adherent adherent: enregistrementRepository.select_enfant_bydatecurrent_file_notnull(year_current))
		 {
			 adherent.setFile_enregistrement(enregistrementRepository.select_enregistement_byadh_bydate(adherent.getId(), year_current).getFile());
			 list_adh.add(adherent);

		 }
		 
		 return list_adh;*/
		
		for(Adherent adherent: enregistrementRepository.select_enfant_bydatecurrent_file_notnull(year_current))
		 {
		//	list.add(enregistrementRepository.select_enregistement_byadh_bydatenot_nullfile(adherent.getId(), year_current));
			//adherent.setEnregistrements(list);
			adherent.setEnregistrements(enregistrementRepository.select_enregistement_byadh_bydatenot_nullfile(adherent.getId(), year_current));
			 list_adh.add(adherent);

		 }
		 
		 return list_adh;
		// return  enregistrementRepository.select_enfant_bydatecurrent_file_notnull(year_current)	 ;


	}

	// http://localhost:8080/select_enfant_bydatecurrent_file_notnull
	@PutMapping("/update_enregistrement_filenull/{id}")
	public void update_enregistrement_filenull(@PathVariable("id") int id) {
		int year_current = Calendar.getInstance().get(Calendar.YEAR);
		Enregistrement enregistrement = enregistrementRepository.select_enregistement_byadh_bydate(id, year_current);
		enregistrement.setFile(null);
		enregistrementRepository.save(enregistrement);
	}
	
	// http://localhost:8080/select_enfant_bydatecurrent_file_notnull
		@GetMapping("/select_enregistrement_bytousadh")
		public List<Enregistrement> select_enregistrement_bytousadh() {
			int year_current = Calendar.getInstance().get(Calendar.YEAR);
			List<Enregistrement> list = new ArrayList<>();
			List<Adherent> list_adh = new ArrayList<>();

			
			
			
		 // return enregistrementRepository.select_enregistement_bydate(year_current);
			for (Adherent adh : enregistrementRepository.select_enfant_bydatecurrent_file_notnull(year_current)) {
	  	enregistrementRepository.select_enregistement_byadh_bydate(adh.getId(), year_current);

			}
			return list;

		}
		
		// http://localhost:8080/getmax_status_enregistrement
				@GetMapping("/getmax_status_enregistrement")
				public int getmax_status_enregistrement() {
				
					return enregistrementRepository.getmax_status_enregistrement();

				}

}
