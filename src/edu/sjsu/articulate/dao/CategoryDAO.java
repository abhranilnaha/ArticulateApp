package edu.sjsu.articulate.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.cmd.Query;

import edu.sjsu.articulate.OfyService;
import edu.sjsu.articulate.model.Category;
import edu.sjsu.articulate.model.GetCategoriesResponse;

public class CategoryDAO {
	
	public Category addCategory(String name, String parentName, String link, String icon) throws Exception {
		
		Category category = new Category();
		if (name != null && !name.trim().isEmpty()) {
			
			name = name.trim();
			
			Objectify ofy = OfyService.factory().begin();
			
			List<Category> existingCategoriesWithName = getCategoriesByName(name);
			
			if(existingCategoriesWithName.size() > 0) {
				throw new Exception("Category name : " + name + " already exists");
			}
			
			//if parent category is supplied, check if it exists
			if (parentName != null && !parentName.trim().isEmpty()) { 
				
				parentName = parentName.trim();
				
				List<Category> existingCategoriesWithParamName = getCategoriesByName(parentName);
				
				if(existingCategoriesWithParamName.size() == 0) {
					throw new Exception("Parent Category name : " + parentName + " does not exist");
				}
			}
			
			category.setName(name);
			category.setParentName(parentName);
			category.setIcon(icon);
			category.setLink(link);
			
			ofy.save().entity(category).now();
			
		} else {
			throw new Exception("category name cannot be blank or null");
		}
		
		return category;
	}
	
	
	public List<GetCategoriesResponse> getCategoriesResponse(String inputName) {
		List<GetCategoriesResponse> categoriesResponse = new ArrayList<GetCategoriesResponse>();
		
		List<Category> categories = getCategoriesByParentName(inputName);

		for (Category cat : categories) {
			GetCategoriesResponse catResp = new GetCategoriesResponse();
			catResp.setName(cat.getName());
			catResp.setIcon(cat.getIcon());
			catResp.setLink(cat.getLink());
			
			
			List<GetCategoriesResponse> subCategoriesResponse = new ArrayList<GetCategoriesResponse>();
			
			List<Category> subCategories = getCategoriesByParentName(cat.getName());
			
			for(Category subCat : subCategories) {
				GetCategoriesResponse subCatResp = new GetCategoriesResponse();
				subCatResp.setName(subCat.getName());
				subCatResp.setIcon(subCat.getIcon());
				subCatResp.setLink(subCat.getLink());
				
				List<GetCategoriesResponse> subCategories1Response = new ArrayList<GetCategoriesResponse>();
				
				List<Category> subCategories1 = getCategoriesByParentName(subCat.getName());
				for(Category subCat1 : subCategories1) {
					GetCategoriesResponse subCat1Resp = new GetCategoriesResponse();
					subCat1Resp.setName(subCat1.getName());
					subCat1Resp.setIcon(subCat1.getIcon());
					subCat1Resp.setLink(subCat1.getLink());
					
					subCategories1Response.add(subCat1Resp);
				}
				subCatResp.setItems(subCategories1Response);
				subCategoriesResponse.add(subCatResp);
			}
			
			catResp.setItems(subCategoriesResponse);
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
	
	
	
}
