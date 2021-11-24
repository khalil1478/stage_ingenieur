package tn.socotu.spring.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.socotu.spring.entities.Adherent;
import tn.socotu.spring.entities.Assurance;
import tn.socotu.spring.entities.Category;
import tn.socotu.spring.repository.AdherentRepository;
import tn.socotu.spring.repository.AssuranceRepository;
import tn.socotu.spring.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AssuranceRepository assuranceRepository;
	
	@Autowired
	AdherentRepository adherentRepository;

	public void ajouter_sous_category(int id_category, Category category) {
		// TODO Auto-generated method stub
		int id_category1 = categoryRepository.findById(id_category).get().getId();
		category.setIdcategory(id_category1);
		categoryRepository.save(category);
	}

	public void modifier_category(int id_category, Category category) {
		// TODO Auto-generated method stub
		Category category_to_update = categoryRepository.findById(id_category).get();

		category_to_update.setDuree(category.getDuree());
		category_to_update.setLibelle(category.getLibelle());
		category_to_update.setMax(category.getMax());
		category_to_update.setMontant(category.getMontant());
		category_to_update.setNom(category.getNom());
		category_to_update.setType(category.getType());
		categoryRepository.save(category_to_update);
	}

	public void modifier_sous_categorybycategory(int id_souscategory, Category category) {
		// TODO Auto-generated method stub

		Category sous_category_to_update = categoryRepository.findById(id_souscategory).get();
		sous_category_to_update.setDuree(category.getDuree());
		sous_category_to_update.setLibelle(category.getLibelle());
		sous_category_to_update.setMax(category.getMax());
		sous_category_to_update.setMontant(category.getMontant());
		sous_category_to_update.setNom(category.getNom());
		sous_category_to_update.setType(category.getType());
		// sous_category_to_update.setIdcategory(id_category);
		categoryRepository.save(sous_category_to_update);
	}

	public List<Category> select_sous_category_bycategoryid(int id_category,String id_sous_adh) {
		// TODO Auto-generated method stub
		
	
		
		Category category2 = categoryRepository.findById(id_category).get();

		int id_category2 = categoryRepository.findById(id_category).get().getId();
		java.util.List<Category> list_category = new ArrayList<>();
		java.util.List<Category> list_category2 = new ArrayList<>();
		java.util.List<Category> list_category3 = new ArrayList<>();
		for (Category category : categoryRepository.FindByIdcategory(id_category2)) {
			
			
						
			list_category.add(category);
		}
		if(list_category.size()==0)
		{
			list_category.add(category2);
			return list_category;
		}

		if( id_sous_adh != null && categoryRepository.FindByIdcategory(id_category2).size() != 0 )
		{
			int id_sousadh = Integer.parseInt(id_sous_adh);
		
			Adherent adherent2 = adherentRepository.findById(id_sousadh).orElse(null);
			if( adherent2.getMaladie() == 1)
			{
				System.out.println("******************************"+id_sousadh);
				System.out.println("***************id_category***************"+id_category);
				System.out.println("*******************yeeeeeeeeeeeees*************");
			for (Category category3 : categoryRepository.FindByIdcategory(id_category2)) {
				if(category3.getMaladie() == 1)
				{	
					System.out.println("***************yes***************");

					list_category2.add(category3);
				}
				
			}
			
			return list_category2;

			}
			else
			{
				System.out.println("******************************"+id_sousadh);
			System.out.println("***************id_category***************"+id_category);
				System.out.println("*******************noooooooooooooooooo*************");
				for (Category category4 : categoryRepository.FindByIdcategory(id_category2)) {
					if(category4.getMaladie() == 0)
					{	
						System.out.println("***************noooooooooooooooooooooooooo***************");

						list_category3.add(category4);
					}
					
				}
				return list_category3;

			}
			
		}
		else
		return list_category;
	
		
	}

	public void delete_category(int id_category) {
		// TODO Auto-generated method stub
		Category category_delete = categoryRepository.findById(id_category).get();

		if (category_delete.getIdcategory() == 0) {
			for (Category category : categoryRepository.FindByIdcategory(category_delete.getId())) {
				categoryRepository.deleteById(category.getId());
			}
			categoryRepository.deleteById(category_delete.getId());
		}

		else {
			categoryRepository.deleteById(category_delete.getId());
		}
	}

	public Category details_category(int id_category) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id_category).get();

	}

	
	
	
	
	
	
	
	
	
	
	
	public void ajoutercategory(Category category) {
		// TODO Auto-generated method stub
		int year = Calendar.getInstance().get(Calendar.YEAR);
	
		
		Assurance assurance = assuranceRepository.FindassuranceByYear(year);
		
	category.setAssurance(assurance);
	category.setMaladie(0);
	categoryRepository.save(category);
		
	}
	
	

}
