package tn.socotu.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Numero_Bulletin;

@Repository
public interface Numero_BulletinRepository  extends JpaRepository<Numero_Bulletin, Integer>{
	
//	Numero_Bulletin findByNumero(int numero);
	
	@Query("select count(nb)   from Numero_Bulletin nb   where nb.numero= :numero  ")
	public int findByNumero_b(@Param("numero") int numero);

	
	@Query("select b   from Numero_Bulletin b join b.adherent a    where b.numero= :numero and a.id= :id_adherent  ")
	public Numero_Bulletin select_remboursement_byadherent_bynumero(@Param("id_adherent") int id_adherent,@Param("numero") int numero);
}
