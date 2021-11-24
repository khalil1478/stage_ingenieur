package tn.socotu.spring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Docteur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	private String   name;
	
	@ManyToOne
	private Category category_docteur;

	//********************association remboursement et docteur********************//
	@JsonIgnore
	@OneToMany(mappedBy="docteur")
	private List<Remboursement> remboursements;
			//********************association remboursement et docteur********************//

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Category getCategory_docteur() {
		return category_docteur;
	}



	public void setCategory_docteur(Category category_docteur) {
		this.category_docteur = category_docteur;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Docteur() {
		super();
	}

	


	public Docteur(int id) {
		super();
		this.id = id;
	}



	public Docteur(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public List<Remboursement> getRemboursements() {
		return remboursements;
	}



	public void setRemboursements(List<Remboursement> remboursements) {
		this.remboursements = remboursements;
	}



	@Override
	public String toString() {
		return "Docteur [id=" + id + ", name=" + name + ", category_docteur=" + category_docteur + ", remboursements="
				+ remboursements + "]";
	}





	



	
	
	
	
	
}
