package tn.socotu.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Enregistrement;

@Repository
public interface EnregistrementRepository  extends JpaRepository<Enregistrement, Integer>{

	@Query("select a  from Enregistrement  r  join  r.adherent  a  where  r.date= :year_act and r.file  =null  ")
	public List<Adherent> select_enfant_bydatecurrent(@Param("year_act")  int year_act);
	
	@Query("select DISTINCT  a  from Enregistrement  r  join  r.adherent  a   ")
	public List<Adherent> select_enfant_bysexe();
	
	@Query("select   max(r.date) from Enregistrement  r  join  r.adherent  a  where  a.id= :idadh group by a.id  ")
	public  int select_max_enfant_bysexe(@Param("idadh")  int idadh);

	
	@Query("select a  from Enregistrement  r  join  r.adherent  a  where  r.date= :year_act and r.file  !=null  ")
	public List<Adherent> select_enfant_bydatecurrent_file_notnull(@Param("year_act")  int year_act);

	
	@Query("select r  from Enregistrement  r  join  r.adherent  a  where  a.id= :id and r.date= :year_act    ")
	public Enregistrement select_enregistement_byadh_bydate(@Param("id")  int id, @Param("year_act")  int year_act);

	
	@Query("select r  from Enregistrement  r   where  r.date= :year_act and r.file  !=null  ")
	public List<Enregistrement> select_enregistement_bydate(@Param("year_act")  int year_act);
	
	@Query("select r  from Enregistrement  r  join  r.adherent  a  where  a.id= :id and r.date= :year_act   and r.file  !=null    ")
	public List<Enregistrement> select_enregistement_byadh_bydatenot_nullfile (@Param("id")  int id, @Param("year_act")  int year_act);

	@Query("select DISTINCT(max(r.status))  from Enregistrement  r ")
	public int getmax_status_enregistrement();
	
	@Query("select DISTINCT(min(r.status))  from Enregistrement  r ")
	public int getmin_status_enregistrement();

}
