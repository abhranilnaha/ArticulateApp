package edu.sjsu.articulate.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.cmd.Query;

import edu.sjsu.articulate.OfyService;
import edu.sjsu.articulate.model.Category;
import edu.sjsu.articulate.model.CategoryResponse;
import edu.sjsu.articulate.model.Item;

public class CategoryDAO {
	
	public Category addCategory(String name, String parentName, String link, String icon, String level) throws Exception {
		
		Category category = new Category();
		if (name != null && (!name.trim().isEmpty())) {
			
			name = name.trim();
			parentName = parentName.trim();
			
			Objectify ofy = OfyService.factory().begin();
			
			List<Category> existingCategories = getCategoriesByNameAndParentName(name, parentName);
			
			if(existingCategories.size() > 0) {
				throw new Exception("Category name : " + name + " with parent name " + parentName + " already exists");
			}
			
			category.setName(name);
			category.setParentName(parentName);
			category.setIcon(icon);
			category.setLink(link);
			category.setLevel(level);
			
			ofy.save().entity(category).now();
			
		} else {
			throw new Exception("category name cannot be blank or null");
		}
		
		return category;
	}
	
	

	public List<CategoryResponse> getCategoriesResponse(String inputName) {
		List<CategoryResponse> categoriesResponse = new ArrayList<CategoryResponse>();
		
		List<Category> categories = getCategoriesByParentName(inputName);
		
		// Level 1
		for(Category cat : categories) {
			CategoryResponse catResp = new CategoryResponse();
			catResp.setName(cat.getName());
			catResp.setIcon(cat.getIcon());
			catResp.setLink(cat.getLink());
			
			//Level 2
			List<Category> subCategories = getCategoriesByParentName(cat.getName());
			
			if(subCategories.size() > 0) {
				List<Item> itemsResp = new ArrayList<Item>();
				Item itemResp = new Item();
				itemResp.setTitle(cat.getName());
				itemResp.setIcon(cat.getIcon());
				
				List<CategoryResponse> subCategoriesResponse = new ArrayList<CategoryResponse>();
				
				for(Category subCat : subCategories) {
					
					CategoryResponse subCatResp = new CategoryResponse();
					subCatResp.setName(subCat.getName());
					subCatResp.setIcon(subCat.getIcon());
					subCatResp.setLink(subCat.getLink());
					
					List<Category> subCategories1 = getCategoriesByParentName(subCat.getName());
					
					if(subCategories1.size() > 0) { 
						List<Item> itemsResp1 = new ArrayList<Item>();
						Item itemResp1 = new Item();
						itemResp1.setTitle(subCat.getName());
						itemResp1.setIcon(subCat.getIcon());
						
						
						List<CategoryResponse> subCategoriesResponse1 = new ArrayList<CategoryResponse>();
						for(Category subCat1 : subCategories1) {
							
							CategoryResponse subCatResp1 = new CategoryResponse();
							subCatResp1.setName(subCat1.getName());
							subCatResp1.setIcon(subCat1.getIcon());
							subCatResp1.setLink(subCat1.getLink());
							
							
							subCategoriesResponse1.add(subCatResp1);
						}
						
						itemResp1.setItems(subCategoriesResponse1);
						
						itemsResp1.add(itemResp1);
						subCatResp.setItems(itemsResp1);
						
					}
					
					subCategoriesResponse.add(subCatResp);
				}
				itemResp.setItems(subCategoriesResponse);
				itemsResp.add(itemResp);
				catResp.setItems(itemsResp);
			}
			categoriesResponse.add(catResp);
			
		}
		
		
		return categoriesResponse;
	}
	
	public List<Category> getCategoriesByParentName(String inputName) {
		Objectify ofy = OfyService.factory().begin();
		
		
		Query<Category> categories = ofy.load().type(Category.class).filter("parentName", inputName);
		
		
		List<Category> categoriesResp = new ArrayList<Category>();
			
		for (Category cat : categories) {
			categoriesResp.add(cat);
		}
			
		
		
		return categoriesResp;
	}
	
	public List<Category> getCategoriesByName(String inputName) {
		
		Objectify ofy = OfyService.factory().begin();
		
		Query<Category> categories = ofy.load().type(Category.class).filter("name", inputName);
		
		
		List<Category> categoriesResp = new ArrayList<Category>();
			
		for (Category cat : categories) {
			categoriesResp.add(cat);
		}
			
		
		return categoriesResp;
	}
	
	public List<Category> getCategoriesByNameAndParentName(String categoryName, String categoryParentName) {
		
		Objectify ofy = OfyService.factory().begin();
		
		Query<Category> categories = ofy.load().type(Category.class).filter("name", categoryName).filter("parentName", categoryParentName);
		
		
		List<Category> categoriesResp = new ArrayList<Category>();
			
		for (Category cat : categories) {
			categoriesResp.add(cat);
		}
			
		
		return categoriesResp;
	}
	
	public List<Category> getCategoriesByLevel(String level) {
		
		Objectify ofy = OfyService.factory().begin();
		
		Query<Category> categories = ofy.load().type(Category.class).filter("level", level);
		
		List<Category> categoriesResp = new ArrayList<Category>();
			
		for (Category cat : categories) {
			categoriesResp.add(cat);
		}
			
		
		return categoriesResp;
	}
	
	
	
}
