package edu.sjsu.articulate.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.sjsu.articulate.dao.CategoryDAO;
import edu.sjsu.articulate.model.Category;
import edu.sjsu.articulate.model.Item;

public class GetCategoriesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3474532871300790386L;
	
	CategoryDAO categoryDAO = new CategoryDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String jsonOutput = "";
		
		String level = request.getParameter("level");
		
		if(level != null) {
			
			List<Category> categories = categoryDAO.getCategoriesByLevel(level);
			Gson gson = new Gson();
			jsonOutput = gson.toJson(categories);
			
		} else {
			List<Item> list = new ArrayList<Item>();
			
			Item itemRoot = new Item();
			itemRoot.setTitle("All Categories");
			itemRoot.setIcon("fa fa-reorder");
			itemRoot.setItems(categoryDAO.getCategoriesResponse(""));
			
			list.add(itemRoot);
			
			Gson gson = new Gson();
			jsonOutput = gson.toJson(list);
		}
		

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonOutput);
		out.flush();
		out.close();
		
		
	}

}
