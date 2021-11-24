package tn.socotu.spring.controller;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Enregistrement;
import tn.socotu.spring.entities.Sexe;
import tn.socotu.spring.entities.Type_adh;
import tn.socotu.spring.entities.Type_social;
import tn.socotu.spring.entities.Ville;
import tn.socotu.spring.entities.situation_familiale;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.EnregistrementRepository;
import tn.socotu.spring.repository.RemboursementRepository;
import tn.socotu.spring.service.AdherentService;

@RestController
public class AdherentController {

	@Autowired
	AdherentRepository adherentRepository;
	@Autowired
	AdherentService adherentService;
	@Autowired
	EnregistrementRepository enregistrementRepository;
	
	@Autowired
	RemboursementRepository remboursementRepository;
	@Autowired
	ServletContext context;

	
	@GetMapping("/select_adherent")
	public List<Adherent> select_adherent() {

		int year_current = Calendar.getInstance().get(Calendar.YEAR);
		 
	
	
	  
	     
		List<Adherent> list_Adherent = new ArrayList<>();
		List<Adherent> list_enfant_bydatecurrent = new ArrayList<>();

		
		for (Adherent adherent : enregistrementRepository.select_enfant_bysexe()) {

			
			
			if(adherent.getSexe().equals(Sexe.HOMME) &&  (enregistrementRepository.select_max_enfant_bysexe(adherent.getId()) < year_current) )
			{
				adherent.setVisibilite(1);
			adherentRepository.save(adherent);
			}

				if(adherent.getSexe().equals(Sexe.FEMME) && !(adherent.getSituationfamiliale().equals(situation_familiale.CELEBATAIRE)))
				{
					adherent.setVisibilite(1);
				adherentRepository.save(adherent);
				}
		}
		
		
		
		
		
		for (Adherent adherent : enregistrementRepository.select_enfant_bydatecurrent(year_current)) {
			list_enfant_bydatecurrent.add(adherent);
		
		}
		
		for (Adherent adherent : adherentRepository.findAll()) {
			if(list_enfant_bydatecurrent.contains(adherent) )
			{
				adherent.setVisibilite(1);
				adherentRepository.save(adherent);
			}
			
			list_Adherent.add(adherent);
		}
	
	return list_Adherent;
	}

	
	@PostMapping("/ajout_adherent")
	public void ajouteradherent(@RequestBody Adherent adherent) {

		adherentService.ajouteradherent(adherent);

	}

	
	@PostMapping("/ajouter_sous_adherent/{id_adherent}")
	public void ajouter_sous_adherent(@PathVariable("id_adherent") int idAdherent, @RequestBody Adherent adherent) {

		adherentService.ajouter_sous_adherent(idAdherent, adherent);
	}

	
	@PutMapping("/modifier_adherent/{id_adherent}")
	public void modifier_adherent(@PathVariable("id_adherent") int idAdherent, @RequestBody Adherent adherent) {
		adherentService.modifier_adherent(idAdherent, adherent);
	}

	
	@DeleteMapping("/delete_adherent/{id_adherent}")
	public void delete_adherent(@PathVariable("id_adherent") int idAdherent) {
		adherentService.delete_adherent(idAdherent);
	}

	
	@GetMapping("/select_sous_adherent_byadherentid/{id_adherent}")
	public List<Adherent> select_sous_adherent_byadherentid(@PathVariable("id_adherent") int idAdherent) {

		return adherentService.select_sous_adherent_byadherentid(idAdherent);
	}
	
	
	
	
	
	@GetMapping("/select_adherent_and_sous_adherent_byadherentid/{id_adherent}")
	public List<Adherent> select_adherent_and_sous_adherent_byadherentid(@PathVariable("id_adherent") int idadherent) {

		return adherentService.select_adherent_and_sous_adherent_byadherentid(idadherent);
	}

	
	
	
	@GetMapping("/details_adherent/{adhrentid}")
	public Adherent details_adherent(@PathVariable("adhrentid") int adhrentid) {
		return adherentService.details_adherent(adhrentid);
	}

	
	@GetMapping("/recherche_adherent/{search}")
	public List<Adherent> recherche_adherent(@PathVariable("search") String search) {
		return adherentService.recherche_adherent(search);
	}
	

		@GetMapping("/recherche_adherent_filtre_adherent/{search}")
		public  List<Adherent> recherche_adherent_filtre_adherent(@PathVariable("search") String search) {
			return adherentService.recherche_adherent_filtre_adherent(search );
		}


	
	@GetMapping("/select_adherent_parTypeadh/{type}")
	public List<Adherent> select_adherent_parTypeadh(@PathVariable("type") Type_adh type) {
		return adherentService.select_adherent_parTypeadh(type);
	}

	
	@GetMapping("/select_adherent_parVille/{ville}")
	public List<Adherent> select_adherent_parVille(@PathVariable("ville") Ville ville) {
		return adherentService.select_adherent_parVille(ville);
	}

	
	@GetMapping("/select_enfants")
	public List<Adherent> select_enfants() {
		return adherentService.select_enfants();
	}

	@GetMapping("/select_enfants_byid/{id}")
	public List<Enregistrement> select_enfants_byid(@PathVariable("id") int id) {
		return adherentService.select_enfants_byid(id);
	}

	@GetMapping("/findByDate_idadh/{id}/{idc}")
	public Enregistrement findByDate_idadh(@PathVariable("id") int id, @PathVariable("idc") int idc) {
		return adherentRepository.findByDate_idadh(id, idc);
	}

	@GetMapping("/select_enregistrement_byidadh/{idadh}")
	public List<Enregistrement> select_enregistrement_byidadh(@PathVariable("idadh") int idadh) {
		return adherentService.select_enregistrement_byidadh(idadh);
	}

	@GetMapping("/visibilite_addbuletin_enfant/{idadh}")
	public boolean visibilite_addbuletin_enfant(@PathVariable("idadh") int idadh) {
		return adherentService.visibilite_addbuletin_enfant(idadh);
	}

	public static final String DIRECTORY = System.getProperty("user.home") + "/Documents/uploads/";

	@PostMapping("/ajouter_date_byidadh/{id}/{idc}")
	public void ajouter_date_byidadh(@PathVariable("id") int id, @PathVariable("idc") int idc,
			@RequestParam("file") MultipartFile file)  {
	

		Enregistrement enregistrement = adherentRepository.findByDate_idadh(id, idc);

		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		Path newFileName = get(DIRECTORY, filename).toAbsolutePath().normalize();
		try {
			copy(file.getInputStream(), newFileName, REPLACE_EXISTING);
		} catch (IOException e) {
		
			
		}

		
	
		enregistrement.setFile(filename);
		enregistrementRepository.save(enregistrement);
		Adherent adherent = adherentRepository.findById(id).get();
		adherent.setVisibilite(0);
		adherentRepository.save(adherent);

	

	}
	


	@PostMapping("/ajouter_date_byidadh1/{id}/{idc}")
	public void ajouter_date_byidadh1(@PathVariable("id") int id, @PathVariable("idc") int idc,
			@RequestParam("file") MultipartFile file)  {
		
		Enregistrement enregistrement = adherentRepository.findByDate_idadh(id, idc);
		

		enregistrement.setFile(file.getOriginalFilename());
		enregistrementRepository.save(enregistrement);


	}

	@GetMapping("/downloadFile/{fileName:.+}")

	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request)
			throws Exception {

		// Load file as Resource
		Resource resource = adherentService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/select_adherent_byidadherent_byType_social/{id}/{type}")
	public List<Adherent> select_adherent_byidadherent_byType_social(@PathVariable("id") int id,@PathVariable("type") String type) {
		
		Type_social readyStatus = Type_social.valueOf(type);

		return adherentService.select_adherent_byidadherent_byType_social(id,readyStatus);
		
	}
	
	
	
	@GetMapping("/montant_totle_byidh_idcate/{idadh}/{idc}")
	public double montant_totle_byidh_idcate(@PathVariable("idadh") int id,@PathVariable("idc") int idc) {
		
	 int count =	remboursementRepository.select_remboursement_byadherent_byidcategory(id,idc);
	 
	 if(count ==0 )
	 return 0;
	 else
	return 	 	 remboursementRepository.somme_remboursement_byadherent(id,idc);
		
	}
	

	
	
	


}
