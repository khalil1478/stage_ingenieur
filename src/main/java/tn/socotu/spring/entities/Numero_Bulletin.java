package tn.socotu.spring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Numero_Bulletin {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int numero;

	
	//********************association Numero_Bulletin et Adherent********************//
	@ManyToOne
	private Adherent adherent;
		//********************association Numero_Bulletin et Adherent********************//
		
	
	
	
	//********************association remboursement et numero_bulletin********************//
	/*	@JsonIgnore
		@OneToMany(mappedBy="numero_Bulletin")
		private List<Remboursement> remboursements;*/
		
		
		//********************association remboursement et numero_bulletin********************//
	
		
		
		
	public Numero_Bulletin() {
		super();
	}

	/*public List<Remboursement> getRemboursements() {
		return remboursements;
	}

	public void setRemboursements(List<Remboursement> remboursements) {
		this.remboursements = remboursements;
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	
	
	
	

}
