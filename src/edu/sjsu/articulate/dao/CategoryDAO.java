package edu.sjsu.articulate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import edu.sjsu.articulate.PMF;
import edu.sjsu.articulate.model.Category;
import edu.sjsu.articulate.model.GetCategoriesResponse;

public class CategoryDAO {
	
	@SuppressWarnings("unchecked")
	public Category addCategory(String name, String parentName, String link, String icon) throws Exception {
		
		Category category = new Category();
		
		if (name != null && !name.trim().isEmpty()) {
			
			name = name.trim();
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			//Check if a category doesnt exists with name
			Query q1 = pm.newQuery(Category.class);
			q1.setFilter("name == nameParam");
			q1.declareParameters("String nameParam");
			
			List<Category> existingCategoriesWithName = (List<Category>) q1.execute(name);
			
			if(existingCategoriesWithName.size() > 0) {
				throw new Exception("Category name : " + name + " already exists");
			}
			
			//if parent category is supplied, check if it exists
			if (parentName != null && !parentName.trim().isEmpty()) { 
				
				parentName = parentName.trim();
				
				Query q2 = pm.newQuery(Category.class);
				q2.setFilter("name == nameParam");
				q2.declareParameters("String nameParam");
				
				List<Category> existingCategoriesWithParamName = (List<Category>) q2.execute(parentName);
				
				if(existingCategoriesWithParamName.size() == 0) {
					throw new Exception("Parent Category name : " + parentName + " does not exist");
				}
			}
			
			
			
			category.setName(name);
			category.setParentName(parentName);
			category.setIcon(icon);
			category.setLink(link);

			try {
				pm.makePersistent(category);
			} finally {
				pm.close();
			}
			
		} else {
			throw new Exception("category name cannot be blank or null");
		}
		
		return category;
		
	}
	
	public List<GetCategoriesResponse> getCategoriesResponse(String inputName) {
		List<GetCategoriesResponse> categoriesResponse = new ArrayList<GetCategoriesResponse>();
		
		List<Category> categories = getCategories(inputName);

		for (Category cat : categories) {
			GetCategoriesResponse catResp = new GetCategoriesResponse();
			catResp.setName(cat.getName());
			catResp.setIcon(cat.getIcon());
			catResp.setLink(cat.getLink());
			
			
			List<GetCategoriesResponse> subCategoriesResponse = new ArrayList<GetCategoriesResponse>();
			
			List<Category> subCategories = getCategories(cat.getName());
			
			for(Category subCat : subCategories) {
				GetCategoriesResponse subCatResp = new GetCategoriesResponse();
				subCatResp.setName(subCat.getName());
				subCatResp.setIcon(subCat.getIcon());
				subCatResp.setLink(subCat.getLink());
				
				List<GetCategoriesResponse> subCategories1Response = new ArrayList<GetCategoriesResponse>();
				
				List<Category> subCategories1 = getCategories(subCat.getName());
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
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategories(String inputName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		
		List<Category> categoriesResp = new ArrayList<Category>();
		
		try {
			Query q1 = pm.newQuery(Category.class);
			
			List<Category> categories = (List<Category>) q1.execute();
			
			for(Category cat : categories) {
				if(cat.getParentName().equals(inputName)) {
					categoriesResp.add(cat);
				}
			}
			
		} finally {
			pm.close();
		}
		
		
		return categoriesResp;
	}
	
	
	
}
