package tn.socotu.spring.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Assurance;
import tn.socotu.spring.entities.Category;
import tn.socotu.spring.repository.AssuranceRepository;
import tn.socotu.spring.repository.CategoryRepository;
import tn.socotu.spring.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryService categoryService; 
	
	@Autowired
	AssuranceRepository assuranceRepository;
//  http://localhost:8080/select_category
	@GetMapping("/select_category")
	public List<Category> select_category()
	{
	int year = Calendar.getInstance().get(Calendar.YEAR);
	
		
		Assurance assurance = assuranceRepository.FindassuranceByYear(year);
		//return categoryRepository.findAll(Sort.by("nom").ascending());
		List<Category> list_cat = new ArrayList<Category>();
		for (Category category : assurance.getCategories()) {
			if(category.getIdcategory()==0)
				list_cat.add(category);
		}
		return list_cat;
			
	}
	
//  http://localhost:8080/select_list_category
	@GetMapping("/select_list_category")
	public List<Category> select_list_category()
	{
int year = Calendar.getInstance().get(Calendar.YEAR);
	
		
		Assurance assurance = assuranceRepository.FindassuranceByYear(year);
		List<Category> list_cat = new ArrayList<Category>();

		for (Category category : assurance.getCategories()) {
			list_cat.add(category);
		}
		return list_cat;
			
	}
	
	
	//  http://localhost:8080/ajoutercategory
	@PostMapping("/ajoutercategory")
	public void ajoutercategory(@RequestBody Category category)
	{
		
		categoryService.ajoutercategory(category);
	}
	
	
	
	//  http://localhost:8080/ajouter_sous_category/{id_category}
	@PostMapping("/ajouter_sous_category/{id_category}")
	public void ajouter_sous_category(@PathVariable("id_category") int id_category,@RequestBody Category category)
	{
		
		categoryService.ajouter_sous_category(id_category,category);
	}
	
	
	//  http://localhost:8080/select_sous_category_bycategoryid/{id_category}
	@GetMapping("/select_sous_category_bycategoryid/{id_category}")
	public List<Category> select_sous_category_bycategoryid(@PathVariable("id_category") int id_category,@RequestParam(value="id_sous_adh", required= false) String id_sous_adh)
	{
		
		 return categoryService.select_sous_category_bycategoryid(id_category,id_sous_adh);
	}
	
	
	
	//  http://localhost:8080/modifier_category/{id_category}
	@PutMapping("/modifier_category/{id_category}")
	public void modifier_category(@PathVariable("id_category") int id_category,@RequestBody Category category)
	{
		  categoryService.modifier_category(id_category,category);
	}
	
	
//  http://localhost:8080/delete_category/{id_category}
	@DeleteMapping("/delete_category/{id_category}")
	public void delete_category(@PathVariable("id_category") int id_category)
	{
		  categoryService.delete_category(id_category);
	}
	
	//  http://localhost:8080/details_category/{id_category}
	@GetMapping("/details_category/{id_category}")
	public Category details_category(@PathVariable("id_category") int id_category)
	{
		  return  categoryService.details_category(id_category);
	}
	
	
	
	
	
	//  http://localhost:8080/modifier_sous_categorybycategory/{id_souscategory}
	@PutMapping("/modifier_sous_categorybycategory/{id_souscategory}")
	public void modifier_sous_categorybycategory(@PathVariable("id_souscategory") int id_souscategory,@RequestBody Category category)
	{
		  categoryService.modifier_sous_categorybycategory(id_souscategory,category);
	}
	
	
	
	
	
	

}
