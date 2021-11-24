package tn.socotu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Reclamation;
import tn.socotu.spring.entities.Remboursement;

@Repository
public interface ReclamationRepository  extends JpaRepository<Reclamation, Integer>{

	
	
	@Query("select  r  from Reclamation  r   join r.remboursement b where  b.id= :id_remboursement")
	public Reclamation recherche_reclamation_byid_remboursement(@Param("id_remboursement")  int id_remboursement);

																								
	@Query("select  r  from Reclamation  r   join r.remboursement b join b.adherent a  join b.bordereau bo where  bo.id= :a or b.numero_bulletin= :a or a.matricule= :a")
	public List<Reclamation> recherche_remboursement_in_reclamations(@Param("a")  int a);
	
	
	
		
}
