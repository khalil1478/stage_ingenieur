package tn.socotu.spring.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Assurance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private  String name;
	
	private int mat_assurance;

	
	private int date_deb;
	
	private int date_fin;
	
	//********************association assurance et category********************//
	@JsonIgnore
	@OneToMany(mappedBy="assurance"/*,cascade=CascadeType.ALL*/)
	private List<Category> categories;
	
	
	//********************association  assurance et category********************//

	
	
	
	
	public Assurance() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMat_assurance() {
		return mat_assurance;
	}

	public void setMat_assurance(int mat_assurance) {
		this.mat_assurance = mat_assurance;
	}
	@Override
	public String toString() {
		return "Assurance [id=" + id + ", name=" + name + ", mat_assurance=" + mat_assurance + "]";
	}

	public int getDate_deb() {
		return date_deb;
	}

	public void setDate_deb(int date_deb) {
		this.date_deb = date_deb;
	}

	public int getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(int date_fin) {
		this.date_fin = date_fin;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	
	
	
	

	
	
}
