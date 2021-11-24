package tn.socotu.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Adherent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	
	private int matricule;
	
	
	private String prenom;
	
	private String email;
	
	private String adresse;
	
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	@Enumerated(EnumType.STRING)
	private Type_adh typeadh;
	
	@Enumerated(EnumType.STRING)
	private Type_social typesocial;
	
	@Enumerated(EnumType.STRING)
	private situation_familiale situationfamiliale;
	
	@Enumerated(EnumType.STRING)
	private Ville ville;
	
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	
	private int idadherent;
	
	@Enumerated(EnumType.STRING)
	private Situation situation;
	
	
	private int mnt_prestataire;
	
	
	private  int maladie ;
	
	private int visibilite;
	
	@Transient
	private  int nbr_ville ;
	
	@Transient
	private  int nbr_typead ;
	

	
	
	
	
	
	
	
	
	







	//********************association remboursement et Adherent********************//
	@JsonIgnore
	@OneToMany(mappedBy="adherent")
	private List<Remboursement> remboursements;
	//********************association remboursement et Adherent********************//
	

	
	
	
	//********************association Numero_Bulletin et Adherent********************//
	@JsonIgnore
	@OneToMany(mappedBy="adherent")
	private List<Numero_Bulletin> numero_bulletins;
	//********************association Numero_Bulletin et Adherent********************//
	
	
	

	//********************association remboursement et Adherent********************//
		//@JsonIgnore
		@OneToMany(mappedBy="adherent")
		private List<Enregistrement> enregistrements;
		//********************association remboursement et Adherent********************//
		
		
		
		
		
	public int getId() {
		return id;
	}






	public int getNbr_typead() {
		return nbr_typead;
	}



	public void setNbr_typead(int nbr_typead) {
		this.nbr_typead = nbr_typead;
	}



	public int getNbr_ville() {
		return nbr_ville;
	}



	public void setNbr_ville(int nbr_ville) {
		this.nbr_ville = nbr_ville;
	}



	public int getMaladie() {
		return maladie;
	}



	public void setMaladie(int maladie) {
		this.maladie = maladie;
	}



	public int getMnt_prestataire() {
		return mnt_prestataire;
	}



	public void setMnt_prestataire(int mnt_prestataire) {
		this.mnt_prestataire = mnt_prestataire;
	}



	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getMatricule() {
		return matricule;
	}


	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	


	public Sexe getSexe() {
		return sexe;
	}


	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}


	public Situation getSituation() {
		return situation;
	}


	public void setSituation(Situation situation) {
		this.situation = situation;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Type_adh getTypeadh() {
		return typeadh;
	}


	public void setTypeadh(Type_adh typeadh) {
		this.typeadh = typeadh;
	}


	public Type_social getTypesocial() {
		return typesocial;
	}


	public void setTypesocial(Type_social typesocial) {
		this.typesocial = typesocial;
	}


	public situation_familiale getSituationfamiliale() {
		return situationfamiliale;
	}


	public void setSituationfamiliale(situation_familiale situationfamiliale) {
		this.situationfamiliale = situationfamiliale;
	}


	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}


	public int getIdadherent() {
		return idadherent;
	}


	public void setIdadherent(int idadherent) {
		this.idadherent = idadherent;
	}




	public List<Remboursement> getRemboursements() {
		return remboursements;
	}


	public void setRemboursements(List<Remboursement> remboursements) {
		this.remboursements = remboursements;
	}


	public Adherent() {
		super();
	}




	public List<Enregistrement> getEnregistrements() {
		return enregistrements;
	}


	public void setEnregistrements(List<Enregistrement> enregistrements) {
		this.enregistrements = enregistrements;
	}
	
	


	public int getVisibilite() {
		return visibilite;
	}


	public void setVisibilite(int visibilite) {
		this.visibilite = visibilite;
	}


	public List<Numero_Bulletin> getNumero_bulletins() {
		return numero_bulletins;
	}



	public void setNumero_bulletins(List<Numero_Bulletin> numero_bulletins) {
		this.numero_bulletins = numero_bulletins;
	}



	



	
	


}