package edu.sjsu.articulate.category;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.sjsu.articulate.dao.CategoryDAO;
import edu.sjsu.articulate.model.JSONResponse;
import edu.sjsu.articulate.util.ServiceUtil;

public class SetupCategoriesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5910638014989071243L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONResponse jr = null;
		
		
		
		CategoryDAO categoryDAO = new CategoryDAO();
		
		try {
			categoryDAO.addCategory("I", "", "#", "", "1");
			categoryDAO.addCategory("want", "I", "#", "", "2");
			categoryDAO.addCategory("need", "I", "#", "", "2");
			categoryDAO.addCategory("feel", "I", "#", "", "2");
			categoryDAO.addCategory("think", "I", "#", "", "2");
			categoryDAO.addCategory("water", "want", "#", "", "3");
			categoryDAO.addCategory("fresh air", "want", "#", "", "3");
			categoryDAO.addCategory("sleep", "need", "#", "", "3");
			categoryDAO.addCategory("go", "need", "#", "", "3");
			categoryDAO.addCategory("study", "need", "#", "", "3");
			categoryDAO.addCategory("hungry", "feel", "#", "", "3");
			categoryDAO.addCategory("happy", "feel", "#", "", "3");
			categoryDAO.addCategory("sad", "feel", "#", "", "3");
			categoryDAO.addCategory("sunshine", "think", "#", "", "3");
			categoryDAO.addCategory("books", "think", "#", "", "3");
			categoryDAO.addCategory("You", "", "#", "", "1");
			categoryDAO.addCategory("should", "You", "#", "", "2");
			categoryDAO.addCategory("must", "You", "#", "", "2");
			categoryDAO.addCategory("understand", "You", "#", "", "2");
			categoryDAO.addCategory("call", "should", "#", "", "3");
			categoryDAO.addCategory("study well", "should", "#", "", "3");
			categoryDAO.addCategory("talk", "must", "#", "", "3");
			categoryDAO.addCategory("listen", "must", "#", "", "3");
			categoryDAO.addCategory("me", "understand", "#", "", "3");
			categoryDAO.addCategory("lecture", "understand", "#", "", "3");
			categoryDAO.addCategory("How", "", "#", "", "1");
			categoryDAO.addCategory("are", "How", "#", "", "2");
			categoryDAO.addCategory("is", "How", "#", "", "2");
			categoryDAO.addCategory("you today", "are", "#", "", "3");
			categoryDAO.addCategory("life", "is", "#", "", "3");
			categoryDAO.addCategory("Family", "", "#", "", "1");
			categoryDAO.addCategory("cares", "Family", "#", "", "2");
			categoryDAO.addCategory("takes", "Family", "#", "", "2");
			categoryDAO.addCategory("bought", "Family", "#", "", "2");
			categoryDAO.addCategory("health", "cares", "#", "", "3");
			categoryDAO.addCategory("happiness", "cares", "#", "", "3");
			categoryDAO.addCategory("vacation", "takes", "#", "", "3");
			categoryDAO.addCategory("car", "takes", "#", "", "3");
			categoryDAO.addCategory("clothes", "bought", "#", "", "3");
			categoryDAO.addCategory("medicines", "bought", "#", "", "3");
			categoryDAO.addCategory("food", "bought", "#", "", "3");
			categoryDAO.addCategory("Friends", "", "#", "", "1");
			categoryDAO.addCategory("be", "Friends", "#", "", "2");
			categoryDAO.addCategory("help", "Friends", "#", "", "2");
			categoryDAO.addCategory("hear", "Friends", "#", "", "2");
			categoryDAO.addCategory("nice", "be", "#", "", "3");
			categoryDAO.addCategory("friendly", "be", "#", "", "3");
			categoryDAO.addCategory("trouble", "help", "#", "", "3");
			categoryDAO.addCategory("grammar", "help", "#", "", "3");
			categoryDAO.addCategory("jokes", "hear", "#", "", "3");
			categoryDAO.addCategory("speech", "hear", "#", "", "3");
			categoryDAO.addCategory("School", "", "#", "", "1");
			categoryDAO.addCategory("has", "School", "#", "", "2");
			categoryDAO.addCategory("teaches", "School", "#", "", "2");
			categoryDAO.addCategory("recess", "has", "#", "", "3");
			categoryDAO.addCategory("playground", "has", "#", "", "3");
			categoryDAO.addCategory("lessons", "teaches", "#", "", "3");
			categoryDAO.addCategory("games", "teaches", "#", "", "3");
			categoryDAO.addCategory("Home", "", "#", "", "1");
			categoryDAO.addCategory("seems", "Home", "#", "", "2");
			categoryDAO.addCategory("became", "Home", "#", "", "2");
			categoryDAO.addCategory("beautiful", "seems", "#", "", "3");
			categoryDAO.addCategory("warm", "seems", "#", "", "3");
			categoryDAO.addCategory("clean", "became", "#", "", "3");
			categoryDAO.addCategory("cozy", "became", "#", "", "3");
			categoryDAO.addCategory("Holiday", "", "#", "", "1");
			categoryDAO.addCategory("contained", "Holiday", "#", "", "2");
			categoryDAO.addCategory("will", "Holiday", "#", "", "2");
			categoryDAO.addCategory("Christmas", "contained", "#", "", "3");
			categoryDAO.addCategory("Easter", "contained", "#", "", "3");
			categoryDAO.addCategory("come", "will", "#", "", "3");
			categoryDAO.addCategory("cancel", "will", "#", "", "3");
			categoryDAO.addCategory("Music", "", "#", "", "1");
			categoryDAO.addCategory("plays", "Music", "#", "", "2");
			categoryDAO.addCategory("sounds", "Music", "#", "", "2");
			categoryDAO.addCategory("fast", "plays", "#", "", "3");
			categoryDAO.addCategory("slow", "plays", "#", "", "3");
			categoryDAO.addCategory("good", "sounds", "#", "", "3");
			categoryDAO.addCategory("wonderful", "sounds", "#", "", "3");
			
			
			jr = ServiceUtil.buildJSONResponse("Success");
		} catch (Exception e) {
			jr = ServiceUtil.createErrorResponse(e);
			e.printStackTrace();
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
