package tn.socotu.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enregistrement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String file;
	
	@JsonIgnore
	@ManyToOne
	private Adherent adherent;
	
	private int date;
	
private int status;
	
	
	

	public Enregistrement() {
		super();
	}
	
	
	








	public int getStatus() {
		return status;
	}











	public void setStatus(int status) {
		this.status = status;
	}











	public int getDate() {
		return date;
	}



	public void setDate(int date) {
		this.date = date;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public Adherent getAdherent() {
		return adherent;
	}


	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}



	@Override
	public String toString() {
		return "Enregistrement [id=" + id + ", file=" + file + ", adherent=" + adherent + ", date=" + date + "]";
	}
	
	
	
	
	

}
