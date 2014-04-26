package edu.sjsu.articulate.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.sjsu.articulate.dao.CategoryDAO;
import edu.sjsu.articulate.model.GetCategoriesResponse;
import edu.sjsu.articulate.model.JSONResponse;
import edu.sjsu.articulate.util.ServiceUtil;

public class GetCategoriesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3474532871300790386L;
	
	CategoryDAO categoryDAO = new CategoryDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONResponse jr = null;
		
		List<GetCategoriesResponse> categories = categoryDAO.getCategoriesResponse("");
		
		jr = ServiceUtil.buildJSONResponse(categories);
		
		Gson gson = new Gson();
		String jsonOutput = gson.toJson(jr);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonOutput);
		out.flush();
		out.close();
		
		
	}

}
