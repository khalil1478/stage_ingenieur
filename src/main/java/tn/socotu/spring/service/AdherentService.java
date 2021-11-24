package tn.socotu.spring.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Enregistrement;
import tn.socotu.spring.entities.Sexe;
import tn.socotu.spring.entities.Situation;
import tn.socotu.spring.entities.Type_adh;
import tn.socotu.spring.entities.Type_social;
import tn.socotu.spring.entities.Ville;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.EnregistrementRepository;

@Service
public class AdherentService {
	@Autowired
	AdherentRepository adherentRepository;
	
	@Autowired
	EnregistrementRepository enregistrementRepository;
	
	

	public void modifier_adherent(int id_adherent, Adherent adherent) {
			Adherent adherent_to_update = adherentRepository.findById(id_adherent).get();

			adherent_to_update.setAdresse(adherent.getAdresse());
			adherent_to_update.setDate(adherent.getDate());
			adherent_to_update.setEmail(adherent.getEmail());
			adherent_to_update.setMatricule(adherent.getMatricule());
			adherent_to_update.setNom(adherent.getNom());
			adherent_to_update.setPrenom(adherent.getPrenom());
			adherent_to_update.setSituationfamiliale(adherent.getSituationfamiliale());
			adherent_to_update.setTypeadh(adherent.getTypeadh());
			adherent_to_update.setTypesocial(adherent.getTypesocial());
			adherent_to_update.setVille(adherent.getVille());
			adherent_to_update.setMnt_prestataire(adherent.getMnt_prestataire());
			
			
			// ajouter_maintenant
			if(adherent.getTypesocial().equals(Type_social.ENFANT)  && adherent.getSituation().equals(Situation.NORMAL) && (adherent.getSexe().equals(Sexe.HOMME)||adherent.getSexe().equals(Sexe.FEMME) ))
			{
				int year_debut_adherent = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(adherent.getDate())).getYear()+20;
				int year_fin_adherent = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(adherent.getDate())).getYear()+24;
			//	Adherent adherent3= adherentRepository.save(adherent);

				for (int i = year_debut_adherent; i < year_fin_adherent; i++) {
				//list_Adherent.add(i);
					for (Enregistrement enregistrement : adherent_to_update.getEnregistrements()) {
					//	Enregistrement enregistrement = new Enregistrement();
						enregistrement.setAdherent(adherent_to_update);
						enregistrement.setDate(i);
						enregistrementRepository.save(enregistrement);
						System.out.println(i);
					}
					
					
				}	
			}
			// ajouter_maintenant
			adherentRepository.save(adherent_to_update);
			
		}


	public void delete_adherent(int id_adherent) {
			Adherent adherent_delete = adherentRepository.findById(id_adherent).get();

			if (adherent_delete.getIdadherent() == 0) {
				for (Adherent adherent : adherentRepository.FindByIdAdherent(adherent_delete.getId())) {
					adherentRepository.deleteById(adherent.getId());
				}
				adherentRepository.deleteById(adherent_delete.getId());
			}

			else {
				adherentRepository.deleteById(adherent_delete.getId());
			}
		}
	
	





	public List<Adherent> select_sous_adherent_byadherentid(int id_adherent) {

			int id_adherent2 = adherentRepository.findById(id_adherent).get().getId();
			java.util.List<Adherent> list_Adherent = new ArrayList<>();
			for (Adherent Adherent : adherentRepository.FindByIdAdherent(id_adherent2)) {
				list_Adherent.add(Adherent);
			}
			return list_Adherent;
		}


	public Adherent details_adherent(int id_adherent) {

			return adherentRepository.findById(id_adherent).get();

		}


	public List<Adherent> recherche_adherent(String search) {
		return adherentRepository.recherche_adherent(search);
	}


	public void ajouter_sous_adherent(int id_adherent, Adherent adherent) {
		
		Adherent adherent2 = adherentRepository.findById(id_adherent).get();
		adherent.setIdadherent(adherent2.getId());
		adherent.setMatricule(adherent2.getMatricule());
		adherent.setSituation(Situation.NORMAL);
		adherent.setMnt_prestataire(2500);
		if(adherent.getTypesocial().equals(Type_social.ENFANT)  && adherent.getSituation().equals(Situation.NORMAL) && (adherent.getSexe().equals(Sexe.HOMME)||adherent.getSexe().equals(Sexe.FEMME) ))
		{
			int year_debut_adherent = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(adherent.getDate())).getYear()+20;
			int year_fin_adherent = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(adherent.getDate())).getYear()+24;
			Adherent adherent3= adherentRepository.save(adherent);

			for (int i = year_debut_adherent; i < year_fin_adherent; i++) {
			//list_Adherent.add(i);
				
				Enregistrement enregistrement = new Enregistrement();
				enregistrement.setAdherent(adherent3);
				enregistrement.setDate(i);
				enregistrementRepository.save(enregistrement);
				System.out.println(i);
				
			}	
		}
		else
		adherentRepository.save(adherent);
	}


	public List<Adherent> select_adherent_parTypeadh( Type_adh type) {
		
		return   adherentRepository.select_adherent_parTypeadh(type);
	}


	public List<Adherent> select_adherent_parVille(Ville ville) {
		return   adherentRepository.select_adherent_parVille(ville);
	}


	public List<Adherent> select_enfants( ) {
		java.util.List<Adherent> list_Adherent = new ArrayList<>();

		for (Adherent adherent : adherentRepository.findAll()) {
			if(adherent.getTypesocial().equals(Type_social.ENFANT))
			{
				
			}
		}
		
		return list_Adherent;
	}
	
	
	public List<Enregistrement> select_enfants_byid( int id)  {
		Adherent adherent = adherentRepository.findById(id).get();
		java.util.List<Enregistrement> list_Enregistrement = new ArrayList<>();
		//int year_current = Calendar.getInstance().get(Calendar.YEAR);

		
		//int year_adherent = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(adherent.getDate())).getYear();
		//int year_debut_adherent = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(adherent.getDate())).getYear()+20;
		//int year_fin_adherent = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(adherent.getDate())).getYear()+24;

		
	for (Enregistrement    enregistrement: adherent.getEnregistrements()) {
		if(enregistrement.getFile()==null)
		list_Enregistrement.add(enregistrement);
	}
		return list_Enregistrement;
	}


	public void ajouteradherent(Adherent adherent) {
		if(!adherentRepository.existsByMatricule(adherent.getMatricule()))
		
			adherent.setTypesocial(Type_social.ADHERENT);
		adherent.setSituation(Situation.NORMAL);
		
		
			adherent.setMnt_prestataire(2500);
		
		
			Adherent adherent2 = adherentRepository.save(adherent);
			System.out.println("****adherent2*****"+adherent2.toString());

		}

	 private final Path fileStorageLocation = Paths.get("C:\\Users\\khalil\\Documents\\uploads");	

	public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(((AbstractFileResolvingResource) resource).exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }


	public List<Enregistrement> select_enregistrement_byidadh(int idadh) {
		java.util.List<Enregistrement> list_Enregistrement = new ArrayList<>();

		Adherent adherent = adherentRepository.findById(idadh).get();
		
		for (Enregistrement enregistrement : adherent.getEnregistrements()) {
		  if(enregistrement.getFile() !=  null )
			list_Enregistrement.add(enregistrement);
		}

		return list_Enregistrement;
	}


	public boolean visibilite_addbuletin_enfant(int idadh) {

		
		Boolean result = null;
		Adherent adherent = adherentRepository.findById(idadh).get();
		List<Integer> enregistrements = new ArrayList<>();
		int year_current = Calendar.getInstance().get(Calendar.YEAR);				

		if(adherent.getSexe().equals(Sexe.HOMME) )
				{
			
		for (Enregistrement enregistrement : adherent.getEnregistrements()) {
			
			if(year_current == enregistrement.getDate() )
				
{
				if(enregistrement.getFile()!= null)

	enregistrements.add(enregistrement.getDate());
	
}
			
			
			
		}
		
		if(enregistrements.size() > 0)
			result= true;
		else
			result = false;
				}
		else
			result= true;

		
		return result;
	}


	public List<Adherent> recherche_adherent_filtre_adherent(String search) {
		java.util.List<Adherent> list_Adherent = new ArrayList<>();
		
		//System.out.println(adherent2.toString());
		//return adherent2;
		
		for (Adherent adherent : adherentRepository.findAll()) {
			if(adherent.getMatricule() == Integer.parseInt(search) && adherent.getIdadherent() == 0 && adherent.getTypesocial().equals(Type_social.ADHERENT))
{
				//list_Adherent.add(adherent);
				list_Adherent.add(adherent);

		}
		}
		return list_Adherent;
	
		
	}


	public List<Adherent> select_adherent_byidadherent_byType_social(int id, Type_social readyStatus) {
		java.util.List<Adherent> list_Adherent = new ArrayList<>();

		 Adherent adherent =adherentRepository.findById(id).get();
		if(readyStatus.equals(Type_social.ADHERENT))
		{
			list_Adherent.add(adherent);
					
			return list_Adherent;
		}
		
		else
			
		return  adherentRepository.select_adherent_byidadherent_byType_social(id,readyStatus);
	}


	public List<Adherent> select_adherent_and_sous_adherent_byadherentid(int id_adherent) {
		Adherent adherent2 = adherentRepository.findById(id_adherent).get();
		java.util.List<Adherent> list_Adherent = new ArrayList<>();
		if(adherent2.getIdadherent() == 0)
		{
			
			
		for (Adherent Adherent : adherentRepository.FindByIdAdherent(adherent2.getId())) {
			list_Adherent.add(Adherent);
		}
		list_Adherent.add(adherent2);
		}
		else
		{
			

			for (Adherent Adherent : adherentRepository.FindByIdAdherent(adherent2.getIdadherent())) {
				list_Adherent.add(Adherent);
			}
			Adherent adherent2_search = adherentRepository.findById(adherent2.getIdadherent()).get();
			list_Adherent.add(adherent2_search);
		}
		return list_Adherent;
		
	
	}





	
	
	
	
	
	
	
	
	
	
	
	
	





	
}
	
	

		
	
		
	



