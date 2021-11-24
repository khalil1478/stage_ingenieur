package tn.socotu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Enregistrement;
import tn.socotu.spring.entities.Type_adh;
import tn.socotu.spring.entities.Type_social;
import tn.socotu.spring.entities.Ville;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent,Integer> {
	
	boolean existsByMatricule(int matricule);
	
	
	@Query("select a from Adherent a  where  a.idadherent= :idadherent")
	public List<Adherent> FindByIdAdherent(@Param("idadherent")  int idadherent);
	
	
	@Query("select a from Adherent a  where  a.idadherent= :idadherent or a.id= :idadherent")
	List<Adherent> select_adherent_and_sous_adherent_byadherentid(@Param("idadherent")  int idadherent);
	
	
	
	@Query("SELECT m FROM Adherent m WHERE m.nom LIKE %:search%  OR m.date LIKE %:search% OR m.prenom LIKE %:search% OR m.adresse LIKE %:search%   OR m.matricule LIKE %:search% OR  m.ville LIKE %:search%  OR m.typeadh LIKE %:search%  OR m.typesocial LIKE %:search%  OR m.situationfamiliale LIKE %:search% ")
	List<Adherent> recherche_adherent(@Param("search") String search);
	
	
	@Query("select a from Adherent a  where  a.typeadh= :typeadh")
	public List<Adherent> select_adherent_parTypeadh(@Param("typeadh") Type_adh typeadh);
	

	@Query("select a from Adherent a  where  a.ville= :ville")
	List<Adherent> select_adherent_parVille(@Param("ville") Ville ville);


	@Query("select a from Enregistrement a  where  a.date= :date and a.adherent.id= :idadh")
   public	Enregistrement findByDate_idadh(@Param("idadh") int idadh,@Param("date") int date);


	@Query("select a from Adherent a  where  a.typesocial= :key")
	public List<Adherent> select_adherent_byType_social(@Param("key") Type_social key);


	@Query("select a from Adherent a  where  a.matricule LIKE %:id%  and a.idadherent = 0")
	public Adherent findByMatricule_type(@Param("id") String id);


	
	@Query("select a from Adherent a  where  a.typesocial= :key  and a.idadherent= :id ")
	public List<Adherent> select_adherent_byidadherent_byType_social( @Param("id") int id ,@Param("key") Type_social key);

	
	
	@Query("select  DISTINCT(a.ville) from Adherent  a  where   a.idadherent = 0   ")
	 public List<String> select_chart_byville();

	@Query("select  count(a.id) from Adherent  a  where   a.idadherent = 0  and a.ville= :villeStatus  ")
	int countville(@Param("villeStatus") Ville villeStatus);


	@Query("select  DISTINCT(a.typeadh) from Adherent  a  where   a.idadherent = 0   ")
	 public List<String> select_chart_bytypeadh();
	
	@Query("select  count(a.id) from Adherent  a  where   a.idadherent = 0  and a.typeadh= :typeadhStatus  ")
	int countville(@Param("typeadhStatus") Type_adh typeadhStatus);





	
	
	


}
