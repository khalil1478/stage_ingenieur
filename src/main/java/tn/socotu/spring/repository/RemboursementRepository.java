package tn.socotu.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Remboursement;


@Repository
public interface RemboursementRepository extends CrudRepository<Remboursement, Integer>  {

	
	
	
	@Query("select sum(r.montant_remboursement) from Remboursement  r   where  r.id_sup_adh= :id_sup_adh")
	public double somme_remboursement_byadherent(@Param("id_sup_adh")  int id_sup_adh);

	
	@Query("select sum(r.montant_remboursement) from Remboursement  r   where  r.adherent.id= :id_sup_adh   and r.category.id= :category_id ")
	public double somme_remboursement_byadherent(@Param("id_sup_adh")  int id_sup_adh,@Param("category_id") int category_id );


/*	@Query("select count(r) from Remboursement  r   where  r.id_sup_adh= :id_sup_adh ")
	public int count_remboursementby_idsupadh(@Param("id_sup_adh")  int id_sup_adh );*/


	@Query("select count(r) from Remboursement  r   where  r.adherent.id= :id_sup_adh and r.category.id= :category_id ")
	public int count_categoryby_adherent(@Param("id_sup_adh")  int id_sup_adh, @Param("category_id") int category_id );
	
	
/*	@Query("select count(r) from Remboursement  r   where  r.adherent.id= :id_sousadh and r.category.id= :category_id ")
	public int count_categoryby_sousadh(@Param("id_sousadh")  int id_sousadh, @Param("category_id") int category_id );*/
	
	
	@Query("select r  from Remboursement  r   where  r.id_sup_adh= :id_sousadh ")
	public List<Remboursement> select_reboursement_byadherentsup(@Param("id_sousadh")  int id_sousadh );

	
	@Query("select r  from Remboursement  r   where  r.adherent.id= :id_sousadhr ")
	public List<Remboursement> select_reboursement_bysousadh(@Param("id_sousadhr")  int id_sousadhr );

	
	@Query("SELECT m FROM Remboursement m WHERE  m.docteur.name LIKE %:search%   OR m.adherent.prenom LIKE %:search%   OR m.adherent.nom LIKE %:search% OR  m.category.nom LIKE %:search%   OR  m.category.libelle LIKE %:search%   OR  m.adherent.typesocial LIKE %:search%")
	List<Remboursement> recherche_bulletin(@Param("search") String search);


	@Query("select count(r) from Remboursement  r   where  r.adherent.id= :id and r.category.id= :category_id ")
	public int select_remboursement_byadherent_byidcategory(@Param("id") int id, @Param("category_id") int category_id);

	
	@Query("select r from Remboursement  r ORDER BY  date DESC  ")
	public List<Remboursement> liste_remboursement();


	
	
	
	@Query("select r  from Remboursement  r   where  r.id_sup_adh= :id_sousadh   and r.validation = 0 ")
	public List<Remboursement> afficher_remboursement_byadherent_byvalidation(@Param("id_sousadh")  int id_sousadh );

	
	@Query("select r  from Remboursement  r   where  r.id_sup_adh= :id_sousadh   and r.validation = 1")
	public List<Remboursement> selectremboursement_byadherent_byvalidation_de1(@Param("id_sousadh")  int id_sousadh );


//ajouter le 13/08/2021
	@Query("select r  from Remboursement  r   where  r.numero_bulletin= :num_bull   and r.validation = 1  and r.bordereau = null ")
	public List<Remboursement> select_tous_adherentbynum_bull(@Param("num_bull")  int num_bull );

	@Query("select DISTINCT(r.numero_bulletin)  from Remboursement  r where r.bordereau =null ")
	public List<Integer> select_num_bulletin_distinct();
	
	
	@Query("select DISTINCT r.id_sup_adh   from Remboursement  r where  r.numero_bulletin= :num_bull    ")
	public int adherentbynum_bull(@Param("num_bull")  int num_bull);

	//ajouter le 13/08/2021

	@Query("select sum(r.montant_remboursement) from Remboursement  r   where  r.id_sup_adh= :id_sup_adh  and r.validation = 1 and r.bordereau = null ")
	public double somme_remboursement_byadherent_byvalidation(@Param("id_sup_adh")  int id_sup_adh);
	
	//ajouter le 13/08/2021

	@Query("select sum(r.montant_dep) from Remboursement  r   where  r.id_sup_adh= :id_sup_adh  and r.validation = 1  and r.bordereau = null ")
	public double somme_dep_byadherent_byvalidation(@Param("id_sup_adh")  int id_sup_adh);

	//ajouter le 13/08/2021

	@Query("select r  from Remboursement  r   where  r.numero_bulletin= :num_bull   and r.id_sup_adh= :id_adherent and r.bordereau = null ")
	public List<Remboursement> selectremboursement_byadherent_bynumerobulletin(@Param("id_adherent")  int id_adherent,@Param("num_bull")  int num_bull);
	
	//ajouter le 13/08/2021

	@Query("select r  from Remboursement  r   where  r.numero_bulletin= :num_bull   and r.id_sup_adh= :id_adherent and r.bordereau != null  ")
	public List<Remboursement> selectremboursement_byadherent_bynumerobulletin_bordereau_not_null(@Param("id_adherent")  int id_adherent,@Param("num_bull")  int num_bull);

	

	@Query("select DISTINCT(r.numero_bulletin)  from Remboursement  r  join r.bordereau b where  b.id= :id_bordereau")
	public List<Integer> select_remboursementb_bynum_distinct_by_bordereau(@Param("id_bordereau")  int id_bordereau);
	
	
	@Query("select DISTINCT r.id_sup_adh   from Remboursement  r  join r.bordereau b where   r.numero_bulletin= :num_bull   and  b.id= :id_bordereau")
	public int adherentbynum_bull_by_bordereau(@Param("num_bull")  int num_bull,@Param("id_bordereau")  int id_bordereau);
	
	
	@Query("select r  from Remboursement  r   join r.bordereau b  where  r.numero_bulletin= :num_bull   and r.validation = 1 and  b.id= :id_bordereau")
	public List<Remboursement> select_tous_adherentbynum_bull_byidbordereau(@Param("num_bull")  int num_bull ,@Param("id_bordereau")  int id_bordereau);
	
	
	
	@Query("select sum(r.montant_remboursement) from Remboursement  r  join r.bordereau b  where  r.id_sup_adh= :id_sup_adh  and r.validation = 1 and  b.id= :id_bordereau ")
	public double somme_remboursement_byadherent_byvalidation_byidbordereau(@Param("id_sup_adh")  int id_sup_adh, @Param("id_bordereau")  int id_bordereau);
	
	
	@Query("select sum(r.montant_dep) from Remboursement  r  join r.bordereau b   where  r.id_sup_adh= :id_sup_adh  and r.validation = 1  and b.id= :id_bordereau ")
	public double somme_dep_byadherent_byvalidation_byidbordereau(@Param("id_sup_adh")  int id_sup_adh, @Param("id_bordereau")  int id_bordereau);


	
	@Query("select count(nb)   from Remboursement nb   where nb.numero_bulletin= :numero  ")
	public int findByNumero_b(@Param("numero") int numero);

	@Query("select sum(r.montant_remboursement) from Remboursement  r   where year(r.date)= :year ")
	public double somme_mnt_remboursement_byyear(@Param("year") int year);

	
	

	@Query("select  max(year(r.date))  from Remboursement r  join r.adherent a join r.category c   where a.id= :id_adherent and c.id= :id_category")
	public int recuperer_annee_selon_duree_remboursement_byadherent(@Param("id_adherent")  int id_adherent, @Param("id_category")  int id_category);



	//ajouter le 20/08/2021
		@Query("select r  from Remboursement  r   where  r.numero_bulletin= :num_bull   and r.validation = 1  and r.date_arrivees = null and r.bordereau != null")
		public List<Remboursement> select_tous_adherentbynum_bull_pour_bulletins_arrivess(@Param("num_bull")  int num_bull );
		
		//ajouter le 20/08/2021

		@Query("select sum(r.montant_remboursement) from Remboursement  r   where  r.id_sup_adh= :id_sup_adh  and r.validation = 1 and r.date_arrivees = null and r.bordereau != null ")
		public double somme_remboursement_byadherent_byvalidation_pour_bulletins_arrivess(@Param("id_sup_adh")  int id_sup_adh);
		
		//ajouter le 20/08/2021

		@Query("select sum(r.montant_dep) from Remboursement  r   where  r.id_sup_adh= :id_sup_adh  and r.validation = 1 and r.date_arrivees = null   and r.bordereau != null ")
		public double somme_dep_byadherent_byvalidation_pour_bulletins_arrivess(@Param("id_sup_adh")  int id_sup_adh);
	
	
		//ajouter le 20/08/2021

		@Query("select DISTINCT(r.numero_bulletin)  from Remboursement  r  where r.validation = 1 and  r.bordereau != null and r.date_arrivees = null  ")
		public List<Integer> select_num_bulletin_distinct_pour_bulletins_arrivess();
	/*@Query("select sum(r.montant_remboursement) from Remboursement  r   where  r.adherent.id= :id_sup_adh   and r.category.id= :category_id group by r.adherent.id ")
	public double montant_totle_byidh_idcate(@Param("id_sup_adh")  int id_sup_adh,@Param("category_id") int category_id );*/


	

}
