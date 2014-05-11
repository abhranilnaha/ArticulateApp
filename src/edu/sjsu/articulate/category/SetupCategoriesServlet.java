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
			categoryDAO.addCategory("I", "", "img/phones/Categories/Level1/I.png", "", "1");
			categoryDAO.addCategory("want", "I", "img/phones/Categories/Level2/want.png", "", "2");
			categoryDAO.addCategory("need", "I", "img/phones/Categories/Level2/need.png", "", "2");
			categoryDAO.addCategory("feel", "I", "img/phones/Categories/Level2/feel.png", "", "2");
			categoryDAO.addCategory("think", "I", "img/phones/Categories/Level2/think.png", "", "2");
			categoryDAO.addCategory("water", "want", "img/phones/Categories/Level3/water.png", "", "3");
			categoryDAO.addCategory("fresh air", "want", "img/phones/Categories/Level3/fresh air.png", "", "3");
			categoryDAO.addCategory("sleep", "need", "img/phones/Categories/Level3/sleep.png", "", "3");
			categoryDAO.addCategory("go", "need", "img/phones/Categories/Level3/go.png", "", "3");
			categoryDAO.addCategory("study", "need", "img/phones/Categories/Level3/study.png", "", "3");
			categoryDAO.addCategory("hungry", "feel", "img/phones/Categories/Level3/hungry.png", "", "3");
			categoryDAO.addCategory("happy", "feel", "img/phones/Categories/Level3/happy.png", "", "3");
			categoryDAO.addCategory("sad", "feel", "img/phones/Categories/Level3/sad.png", "", "3");
			categoryDAO.addCategory("sunshine", "think", "#", "img/phones/Categories/Level3/sunshine.png", "3");
			categoryDAO.addCategory("books", "think", "img/phones/Categories/Level3/books.png", "", "3");
			categoryDAO.addCategory("You", "", "img/phones/Categories/Level1/you.png", "", "1");
			categoryDAO.addCategory("should", "You", "img/phones/Categories/Level2/should.png", "", "2");
			categoryDAO.addCategory("must", "You", "img/phones/Categories/Level2/must.png", "", "2");
			categoryDAO.addCategory("understand", "You", "img/phones/Categories/Level2/understand.png", "", "2");
			categoryDAO.addCategory("call", "should", "img/phones/Categories/Level3/call.png", "", "3");
			categoryDAO.addCategory("study well", "should", "img/phones/Categories/Level3/study.png", "", "3");
			categoryDAO.addCategory("talk", "must", "img/phones/Categories/Level3/must.png", "", "3");
			categoryDAO.addCategory("listen", "must", "img/phones/Categories/Level3/hear.png", "", "3");
			categoryDAO.addCategory("me", "understand", "img/phones/Categories/Level3/me.png", "", "3");
			categoryDAO.addCategory("lecture", "understand", "img/phones/Categories/Level3/lecture.png", "", "3");
			categoryDAO.addCategory("How", "", "img/phones/272 pics/Level1/how.png", "", "1");
			categoryDAO.addCategory("are", "How", "img/phones/Categories/Level2/are.png", "", "2");
			categoryDAO.addCategory("is", "How", "img/phones/Categories/Level2/is.png", "", "2");
			categoryDAO.addCategory("you today", "are", "img/phones/Categories/Level3/you.png", "", "3");
			categoryDAO.addCategory("life", "is", "#", "img/phones/Categories/Level3/life.png", "3");
			categoryDAO.addCategory("Family", "", "img/phones/Categories/Level1/family.png", "", "1");
			categoryDAO.addCategory("cares", "Family", "img/phones/Categories/Level2/cares.png", "", "2");
			categoryDAO.addCategory("takes", "Family", "img/phones/Categories/Level2/takes.png", "", "2");
			categoryDAO.addCategory("bought", "Family", "img/phones/Categories/Level2/bought.png", "", "2");
			categoryDAO.addCategory("health", "cares", "img/phones/Categories/Level3/health.png", "", "3");
			categoryDAO.addCategory("happiness", "cares", "img/phones/Categories/Level3/happiness.png", "", "3");
			categoryDAO.addCategory("vacation", "takes", "img/phones/Categories/Level3/vacation.png", "", "3");
			categoryDAO.addCategory("car", "takes", "#", "img/phones/Categories/Level3/car.png", "3");
			categoryDAO.addCategory("clothes", "bought", "img/phones/Categories/Level3/clothes.png", "", "3");
			categoryDAO.addCategory("medicines", "bought", "img/phones/Categories/Level3/medicine.png", "", "3");
			categoryDAO.addCategory("food", "bought", "img/phones/Categories/Level3/food.png", "", "3");
			categoryDAO.addCategory("Friends", "", "img/phones/Categories/Level1/Friends.png", "", "1");
			categoryDAO.addCategory("be", "Friends", "img/phones/Categories/Level2/be.png", "", "2");
			categoryDAO.addCategory("help", "Friends", "img/phones/Categories/Level2/help.png", "", "2");
			categoryDAO.addCategory("hear", "Friends", "img/phones/Categories/Level2/hear.png", "", "2");
			categoryDAO.addCategory("nice", "be", "img/phones/Categories/Level3/nice.png", "", "3");
			categoryDAO.addCategory("friendly", "be", "img/phones/Categories/Level3/friendly.png", "", "3");
			categoryDAO.addCategory("trouble", "help", "img/phones/Categories/Level3/trouble.png", "", "3");
			categoryDAO.addCategory("grammar", "help", "img/phones/Categories/Level3/grammer.png", "", "3");
			categoryDAO.addCategory("jokes", "hear", "img/phones/Categories/Level3/jokes.png", "", "3");
			categoryDAO.addCategory("speech", "hear", "img/phones/Categories/Level3/speech.png", "", "3");
			categoryDAO.addCategory("School", "", "img/phones/Categories/Level1/school.png", "", "1");
			categoryDAO.addCategory("has", "School", "img/phones/Categories/Level2/has.png", "", "2");
			categoryDAO.addCategory("teaches", "School", "img/phones/Categories/Level2/teaches.png", "", "2");
			categoryDAO.addCategory("recess", "has", "img/phones/Categories/Level3/recess.png", "", "3");
			categoryDAO.addCategory("playground", "has", "img/phones/Categories/Level3/playground.png", "", "3");
			categoryDAO.addCategory("lessons", "teaches", "img/phones/Categories/Level3/lesson.png", "", "3");
			categoryDAO.addCategory("games", "teaches", "img/phones/Categories/Level3/games.png", "", "3");
			categoryDAO.addCategory("Home", "", "img/phones/Categories/Level1/home.png", "", "1");
			categoryDAO.addCategory("seems", "Home", "img/phones/Categories/Level2/seems.png", "", "2");
			categoryDAO.addCategory("became", "Home", "img/phones/Categories/Level2/became.png", "", "2");
			categoryDAO.addCategory("beautiful", "seems", "img/phones/Categories/Level3/beautiful.png", "", "3");
			categoryDAO.addCategory("warm", "seems", "img/phones/Categories/Level3/warm.png", "", "3");
			categoryDAO.addCategory("clean", "became", "img/phones/Categories/Level3/clean.png", "", "3");
			categoryDAO.addCategory("cozy", "became", "img/phones/Categories/Level3/cozy.png", "", "3");
			categoryDAO.addCategory("Holiday", "", "img/phones/Categories/Level1/holiday.png", "", "1");
			categoryDAO.addCategory("contained", "Holiday", "img/phones/Categories/Level2/contained.png", "", "2");
			categoryDAO.addCategory("will", "Holiday", "img/phones/Categories/Level2/will.png", "", "2");
			categoryDAO.addCategory("Christmas", "contained", "img/phones/Categories/Level3/christmas.png", "", "3");
			categoryDAO.addCategory("Easter", "contained", "img/phones/Categories/Level3/easter.png", "", "3");
			categoryDAO.addCategory("come", "will", "img/phones/Categories/Level3/come.png", "", "3");
			categoryDAO.addCategory("cancel", "will", "img/phones/Categories/Level3/cancel.png", "", "3");
			categoryDAO.addCategory("Music", "", "img/phones/Categories/Level1/music.png", "", "1");
			categoryDAO.addCategory("plays", "Music", "img/phones/Categories/Level3/plays.png", "", "2");
			categoryDAO.addCategory("sounds", "Music", "img/phones/Categories/Level3/sounds.png", "", "2");
			categoryDAO.addCategory("fast", "plays", "img/phones/Categories/Level3/fast.png", "", "3");
			categoryDAO.addCategory("slow", "plays", "img/phones/Categories/Level3/slow.png", "", "3");
			categoryDAO.addCategory("good", "sounds", "img/phones/Categories/Level3/good.png", "", "3");
			categoryDAO.addCategory("wonderful", "sounds", "img/phones/Categories/Level3/wonderful.png", "", "3");
			
			
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
