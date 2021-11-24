package tn.socotu.spring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;
		
	private String libelle;
	
	private int max;
	
	
	private int duree;
	
	
	
	@Enumerated(EnumType.STRING)
	private Type_contrat type;
	
	private double montant;
	
	private int idcategory;
	
	
	
	
	private int maladie;
	
	//********************association remboursement et category********************//
			@JsonIgnore
			@OneToMany(mappedBy="category")
			private List<Remboursement> remboursements;
	//********************association remboursement et category********************//

			
			
			//********************association docteur et category********************//
			@JsonIgnore
			@OneToMany(mappedBy="category_docteur")
			private List<Docteur> docteurs;
			//********************association docteur et category********************//

			
			
			
			
			
	
			//********************association assurance et category********************//
			@ManyToOne	
			private Assurance assurance;
			//********************association  assurance et category********************//

			
			
			

	public int getId() {
		return id;
	}


	public int getMaladie() {
		return maladie;
	}


	public void setMaladie(int maladie) {
		this.maladie = maladie;
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


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}
	
	


	


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	


	public Assurance getAssurance() {
		return assurance;
	}


	public void setAssurance(Assurance assurance) {
		this.assurance = assurance;
	}


	public  Category() {
		// TODO Auto-generated constructor stub
	}


	public Category(int id, String nom, String libelle, int max, int duree, Type_contrat type) {
		super();
		this.id = id;
		this.nom = nom;
		this.libelle = libelle;
		this.max = max;
		this.type = type;
	}


	public Type_contrat getType() {
		return type;
	}


	public void setType(Type_contrat type) {
		this.type = type;
	}


	public int getIdcategory() {
		return idcategory;
	}


	public void setIdcategory(int idcategory) {
		this.idcategory = idcategory;
	}


	public List<Remboursement> getRemboursements() {
		return remboursements;
	}


	public void setRemboursements(List<Remboursement> remboursements) {
		this.remboursements = remboursements;
	}
	
	


	public List<Docteur> getDocteurs() {
		return docteurs;
	}


	public void setDocteurs(List<Docteur> docteurs) {
		this.docteurs = docteurs;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", nom=" + nom + ", libelle=" + libelle + ", max=" + max + ", duree=" + duree
				+ ", type=" + type + ", montant=" + montant + ", idcategory=" + idcategory + ", remboursements="
				+ remboursements + "]";
	}


	
	
	









	



	

	
	
	
	
	


	


	

	



}
