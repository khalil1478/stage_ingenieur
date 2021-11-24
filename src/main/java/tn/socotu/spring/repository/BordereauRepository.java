package tn.socotu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Bordereau;
import tn.socotu.spring.entities.Remboursement;


	@Repository
	public interface BordereauRepository extends CrudRepository<Bordereau, Integer>  {

		
		@Query("select r from Remboursement r  join  r.bordereau  b  where  b.id=  :id_borderau")
		List<Remboursement> select_remboursement_bybordereau(@Param("id_borderau") int id_borderau);

		@Query("select sum(r.montant_dep) from Remboursement  r  join r.bordereau b   where  b.id= :id_bordereau ")
		public double somme_dep_remboursement_bybordereau(@Param("id_bordereau")  int id_bordereau);

		
		@Query("select sum(r.montant_remboursement) from Remboursement  r  join r.bordereau b   where  b.id= :id_bordereau ")
		public double somme_remboursement_bybordereau(@Param("id_bordereau")  int id_bordereau);

		
	//	@Query("select count(r.id) from Remboursement  r  join r.bordereau b   where  b.id= :id_bordereau and r.ecart !=0 ")
	//	public int status_bordereau(@Param("id_bordereau")  int id_bordereau);
		
		@Query("select count(r.id) from Remboursement  r  join r.bordereau b   where   b.id= :id_bordereau  and r.ecart !=0 and r.numero_bulletin= :num_bul and r.id_sup_adh= :id_adherent")
		public int change_status_bybodereau_byadh(@Param("id_bordereau")  int id_bordereau,@Param("id_adherent")  int id_adherent,@Param("num_bul")  int num_bul);

		
		@Query("SELECT b FROM Bordereau b WHERE b.id= :search  or b.numero_bordereau_arrivees= :search")
		List<Bordereau> recherche_bordereau(@Param("search") int search);
		
		
		@Query("SELECT r FROM Remboursement r WHERE r.numero_bulletin= :key and  r.bordereau.id= :id")
		List<Remboursement> recherche_bulletin_search(@Param("key") int key, @Param("id") int id);

}
