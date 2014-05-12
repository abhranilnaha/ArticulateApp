package edu.sjsu.articulate.category;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.sjsu.articulate.dao.CategoryDAO;
import edu.sjsu.articulate.model.Category;
import edu.sjsu.articulate.model.JSONResponse;
import edu.sjsu.articulate.util.ServiceUtil;

public class SetCategoriesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7458133169568672212L;
	
	CategoryDAO categoryDAO = new CategoryDAO();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		try {
		    BufferedReader reader = request.getReader();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	        	sb.append(line);
	        }
	    } catch (Exception e) { 
	    	e.printStackTrace(); 
	    }
	    
	    JsonParser parser = new JsonParser();
	    JsonObject jsonObj = (JsonObject)parser.parse(sb.toString());
	    		
		String name = jsonObj.get("name").getAsString();
		String parentName = jsonObj.get("parentName").getAsString();
		String icon = jsonObj.get("icon").getAsString();
		String link = jsonObj.get("link").getAsString();
		String level = jsonObj.get("level").getAsString();
		
		JSONResponse jr = null;
		Category category = null;
		try {
			category = categoryDAO.addCategory(name, parentName, link, icon, level);
			jr = ServiceUtil.buildJSONResponse(category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jr = ServiceUtil.createErrorResponse(e);
		}		

		Gson gson = new Gson();
		String jsonOutput = gson.toJson(jr);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonOutput);
		out.flush();
		out.close();

	}

}
