package tn.socotu.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Remboursement {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private double montant_dep;
	
	private double montant_remboursement;
	
	private double montant_remboursement_proximites;
	
	
//	private String nom_docteur ;
	
	private int valeur;
	
	private int id_sup_adh;
	
	private int status_mnt_remboursement;

	@Transient
	private int status;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	@Temporal(TemporalType.DATE)
	private Date date_bulletin;
	
	private int jour;
	
	//********************association remboursement et category********************//
	@ManyToOne	
	private Category category;
	//********************association remboursement et category********************//

	
	//********************association remboursement et Adherent********************//
	@ManyToOne
	private Adherent adherent;
	//********************association remboursement et Adherent********************//
	
	
	//********************association remboursement et docteur********************//
		@ManyToOne
		@JoinColumn(name="id_docteur", nullable = true)
		private Docteur docteur;
		//********************association remboursement et docteur********************//
	

		
		//********************association remboursement et bordereau********************//
		@ManyToOne
		@JoinColumn(name="id_bordereau", nullable = true)
		private Bordereau bordereau;
		
	//********************association remboursement et bordereau********************//
		
		
		//********************association remboursement et reclamation********************//
		@JsonIgnore
		@OneToMany(mappedBy="remboursement")
		private List<Reclamation> reclamations;
		//********************association remboursement et reclamation********************//
		
		
		
		//********************association remboursement et numero_bulletin********************//
	/*	@ManyToOne
		@JoinColumn(name="num_bulletin", nullable = true)
		private Numero_Bulletin numero_Bulletin;*/
		//********************association remboursement et numero_bulletin********************//
		
		
		private String type;
		private int validation ;
		
		@Transient
		private int num_matricule ;
		
		private int numero_bulletin;
		
		@Transient
		private String chaine;
		
		private double ecart;
		
		private String commentaire;

		
		@Temporal(TemporalType.DATE)
		private Date date_arrivees;
		
		private int numero_bordereau_arrivees;
	


	public Date getDate_bulletin() {
			return date_bulletin;
		}


		public void setDate_bulletin(Date date_bulletin) {
			this.date_bulletin = date_bulletin;
		}


	public double getMontant_remboursement_proximites() {
			return montant_remboursement_proximites;
		}


		public void setMontant_remboursement_proximites(double montant_remboursement_proximites) {
			this.montant_remboursement_proximites = montant_remboursement_proximites;
		}


	public int getJour() {
			return jour;
		}


		public void setJour(int jour) {
			this.jour = jour;
		}


	public String getCommentaire() {
			return commentaire;
		}


		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}


	public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


	public int getStatus_mnt_remboursement() {
			return status_mnt_remboursement;
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


		public void setStatus_mnt_remboursement(int status_mnt_remboursement) {
			this.status_mnt_remboursement = status_mnt_remboursement;
		}


	public int getStatus() {
			return status;
		}


		public void setStatus(int status) {
			this.status = status;
		}


	public double getEcart() {
			return ecart;
		}


		public void setEcart(double ecart) {
			this.ecart = ecart;
		}


	public int getNum_matricule() {
			return num_matricule;
		}


		public void setNum_matricule(int num_matricule) {
			this.num_matricule = num_matricule;
		}


	public String getChaine() {
			return chaine;
		}


		public void setChaine(String chaine) {
			this.chaine = chaine;
		}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getMontant_dep() {
		return montant_dep;
	}


	public void setMontant_dep(double montant_dep) {
		this.montant_dep = montant_dep;
	}


	public double getMontant_remboursement() {
		return montant_remboursement;
	}


	public void setMontant_remboursement(double montant_remboursement) {
		this.montant_remboursement = montant_remboursement;
	}


	


	public int getNumero_bulletin() {
		return numero_bulletin;
	}


	public void setNumero_bulletin(int numero_bulletin) {
		this.numero_bulletin = numero_bulletin;
	}


	public int getValidation() {
		return validation;
	}


	public void setValidation(int validation) {
		this.validation = validation;
	}


	public int getValeur() {
		return valeur;
	}


	public void setValeur(int valeur) {
		this.valeur = valeur;
	}


	public int getId_sup_adh() {
		return id_sup_adh;
	}


	public void setId_sup_adh(int id_sup_adh) {
		this.id_sup_adh = id_sup_adh;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Adherent getAdherent() {
		return adherent;
	}


	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}


	public Remboursement() {
		super();
	}
	
	


	public Docteur getDocteur() {
		return docteur;
	}


	public void setDocteur(Docteur docteur) {
		this.docteur = docteur;
	}


	public Remboursement(int id) {
		super();
		this.id = id;
	}
	
	
	


	/*public Numero_Bulletin getNumero_Bulletin() {
		return numero_Bulletin;
	}


	public void setNumero_Bulletin(Numero_Bulletin numero_Bulletin) {
		this.numero_Bulletin = numero_Bulletin;
	}*/


	public Remboursement(int id, double montant_dep, double montant_remboursement, int valeur, int id_sup_adh,
			Category category, Adherent adherent, Docteur docteur) {
		super();
		this.id = id;
		this.montant_dep = montant_dep;
		this.montant_remboursement = montant_remboursement;
		this.valeur = valeur;
		this.id_sup_adh = id_sup_adh;
		this.category = category;
		this.adherent = adherent;
		this.docteur = docteur;
	}
	
	


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Bordereau getBordereau() {
		return bordereau;
	}


	public void setBordereau(Bordereau bordereau) {
		this.bordereau = bordereau;
	}


	@Override
	public String toString() {
		return "Remboursement [id=" + id + ", montant_dep=" + montant_dep + ", montant_remboursement="
				+ montant_remboursement ;
	}

	
	




	
	
	
	
	




	
	
	
	
	
	
}
