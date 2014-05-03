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
			categoryDAO.addCategory("I", "", "#", "");
			categoryDAO.addCategory("want", "I", "#", "");
			categoryDAO.addCategory("need", "I", "#", "");
			categoryDAO.addCategory("feel", "I", "#", "");
			categoryDAO.addCategory("think", "I", "#", "");
			categoryDAO.addCategory("water", "want", "#", "");
			categoryDAO.addCategory("fresh air", "want", "#", "");
			categoryDAO.addCategory("sleep", "need", "#", "");
			categoryDAO.addCategory("go", "need", "#", "");
			categoryDAO.addCategory("study", "need", "#", "");
			categoryDAO.addCategory("hungry", "feel", "#", "");
			categoryDAO.addCategory("happy", "feel", "#", "");
			categoryDAO.addCategory("sad", "feel", "#", "");
			categoryDAO.addCategory("sunshine", "think", "#", "");
			categoryDAO.addCategory("books", "think", "#", "");
			categoryDAO.addCategory("You", "", "#", "");
			categoryDAO.addCategory("should", "You", "#", "");
			categoryDAO.addCategory("must", "You", "#", "");
			categoryDAO.addCategory("understand", "You", "#", "");
			categoryDAO.addCategory("call", "should", "#", "");
			categoryDAO.addCategory("study well", "should", "#", "");
			categoryDAO.addCategory("talk", "must", "#", "");
			categoryDAO.addCategory("listen", "must", "#", "");
			categoryDAO.addCategory("me", "understand", "#", "");
			categoryDAO.addCategory("lecture", "understand", "#", "");
			categoryDAO.addCategory("How", "", "#", "");
			categoryDAO.addCategory("are", "How", "#", "");
			categoryDAO.addCategory("is", "How", "#", "");
			categoryDAO.addCategory("you today", "are", "#", "");
			categoryDAO.addCategory("life", "is", "#", "");
			categoryDAO.addCategory("Family", "", "#", "");
			categoryDAO.addCategory("cares", "Family", "#", "");
			categoryDAO.addCategory("takes", "Family", "#", "");
			categoryDAO.addCategory("bought", "Family", "#", "");
			categoryDAO.addCategory("health", "cares", "#", "");
			categoryDAO.addCategory("happiness", "cares", "#", "");
			categoryDAO.addCategory("vacation", "takes", "#", "");
			categoryDAO.addCategory("car", "takes", "#", "");
			categoryDAO.addCategory("clothes", "bought", "#", "");
			categoryDAO.addCategory("medicines", "bought", "#", "");
			categoryDAO.addCategory("food", "bought", "#", "");
			categoryDAO.addCategory("Friends", "", "#", "");
			categoryDAO.addCategory("be", "Friends", "#", "");
			categoryDAO.addCategory("help", "Friends", "#", "");
			categoryDAO.addCategory("hear", "Friends", "#", "");
			categoryDAO.addCategory("nice", "be", "#", "");
			categoryDAO.addCategory("friendly", "be", "#", "");
			categoryDAO.addCategory("trouble", "help", "#", "");
			categoryDAO.addCategory("grammar", "help", "#", "");
			categoryDAO.addCategory("jokes", "hear", "#", "");
			categoryDAO.addCategory("speech", "hear", "#", "");
			categoryDAO.addCategory("School", "", "#", "");
			categoryDAO.addCategory("has", "School", "#", "");
			categoryDAO.addCategory("teaches", "School", "#", "");
			categoryDAO.addCategory("recess", "has", "#", "");
			categoryDAO.addCategory("playground", "has", "#", "");
			categoryDAO.addCategory("lessons", "teaches", "#", "");
			categoryDAO.addCategory("games", "teaches", "#", "");
			categoryDAO.addCategory("Home", "", "#", "");
			categoryDAO.addCategory("seems", "Home", "#", "");
			categoryDAO.addCategory("became", "Home", "#", "");
			categoryDAO.addCategory("beautiful", "seems", "#", "");
			categoryDAO.addCategory("warm", "seems", "#", "");
			categoryDAO.addCategory("clean", "became", "#", "");
			categoryDAO.addCategory("cozy", "became", "#", "");
			categoryDAO.addCategory("Holiday", "", "#", "");
			categoryDAO.addCategory("contained", "Holiday", "#", "");
			categoryDAO.addCategory("will", "Holiday", "#", "");
			categoryDAO.addCategory("Christmas", "contained", "#", "");
			categoryDAO.addCategory("Easter", "contained", "#", "");
			categoryDAO.addCategory("come", "will", "#", "");
			categoryDAO.addCategory("cancel", "will", "#", "");
			categoryDAO.addCategory("Music", "", "#", "");
			categoryDAO.addCategory("plays", "Music", "#", "");
			categoryDAO.addCategory("sounds", "Music", "#", "");
			categoryDAO.addCategory("fast", "plays", "#", "");
			categoryDAO.addCategory("slow", "plays", "#", "");
			categoryDAO.addCategory("good", "sounds", "#", "");
			categoryDAO.addCategory("wonderful", "sounds", "#", "");
			
			
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
