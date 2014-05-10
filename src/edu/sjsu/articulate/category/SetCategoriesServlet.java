package edu.sjsu.articulate.category;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		
		JSONResponse jr = null;
		
		String name = request.getParameter("name");
		String parentName = request.getParameter("parentName");
		String icon = request.getParameter("icon");
		String link = request.getParameter("link");
		String level = request.getParameter("level");
		
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
