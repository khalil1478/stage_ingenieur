package tn.socotu.spring.entities;

import javax.persistence.*;

@Entity
public class Contrat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;

	public int getId() {
		return id;
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

	public Contrat(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	public Contrat() {
		super();
	}

	@Override
	public String toString() {
		return "Contrat [id=" + id + ", nom=" + nom + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
