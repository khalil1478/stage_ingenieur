package tn.socotu.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reclamation {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//********************association remboursement et reclamation********************//
	@ManyToOne
	@JoinColumn(name="id_remboursement", nullable = true)
	private Remboursement remboursement;
	//********************association remboursement et reclamation********************//
	
		
	private int validation;
	
	
	@Temporal(TemporalType.DATE)
	private Date date_arrivees;
	
	private int numero_bordereau_arrivees;
	
	
	
	public Date getDate_arrivees() {
		return date_arrivees;
	}

	public void setDate_arrivees(Date date_arrivees) {
		this.date_arrivees = date_arrivees;
	}

	public int getNumero_bordereau_arrivees() {
		return numero_bordereau_arrivees;
	}

	public void setNumero_bordereau_arrivees(int numero_bordereau_arrivees) {
		this.numero_bordereau_arrivees = numero_bordereau_arrivees;
	}

	public int getValidation() {
		return validation;
	}

	public void setValidation(int validation) {
		this.validation = validation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Remboursement getRemboursement() {
		return remboursement;
	}

	public void setRemboursement(Remboursement remboursement) {
		this.remboursement = remboursement;
	}
	
	
	
}
