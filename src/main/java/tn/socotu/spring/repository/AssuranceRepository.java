package tn.socotu.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Assurance;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Integer> {
	
	
	@Query("select a from Assurance a  where  ( a.date_deb= :year ) or (a.date_deb >= :year  and  a.date_fin < :year) or (a.date_deb < :year  and  a.date_fin > :year) ")
	public Assurance FindassuranceByYear(@Param("year")  int year);

	
	//@Query("select a from Assurance a  where   year  BETWEEN a.date_deb AND a.date_fin  ")
	//public Assurance FindassuranceByYear(@Param("year") int year);
	
	
	
//	@Query("select a from Remboursement r  where    ")
//	public List<Adherent> select_adherent_statistique_mois_byannee(@Param("annee") int annee ,@Param("mois") int mois);
	

}
