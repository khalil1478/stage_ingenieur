package tn.socotu.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bordereau {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private  int numero ;

	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Temporal(TemporalType.DATE)
	private Date date_arrivees;
	
	private int numero_bordereau_arrivees;
	
	//********************association remboursement et bordereau********************//
	@JsonIgnore
	@OneToMany(mappedBy="bordereau")
	private List<Remboursement> remboursements;
	
	
	@Transient
	private double mnt_dep ;
	
	@Transient
	private double mnt_remboursement ;
	
	
	private int status ;
	
	
public double getMnt_dep() {
		return mnt_dep;
	}

	public void setMnt_dep(double mnt_dep) {
		this.mnt_dep = mnt_dep;
	}

	public double getMnt_remboursement() {
		return mnt_remboursement;
	}

	public void setMnt_remboursement(double mnt_remboursement) {
		this.mnt_remboursement = mnt_remboursement;
	}

	//********************association remboursement et bordereau********************//
	public Bordereau() {
		super();
	}

	@Override
	public String toString() {
		return "Bordereau [id=" + id + ", numero=" + numero + "]";
	}

	public Bordereau(int id, int numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

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

	public Date getDate() {
		return date;
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Remboursement> getRemboursements() {
		return remboursements;
	}

	public void setRemboursements(List<Remboursement> remboursements) {
		this.remboursements = remboursements;
	}

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

	

	
	
	
	
	

}
