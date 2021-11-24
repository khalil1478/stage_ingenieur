package tn.socotu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.socotu.spring.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c  where  c.idcategory= :id_category")
	public List<Category> FindByIdcategory(@Param("id_category")  int id_category);
	
	
}