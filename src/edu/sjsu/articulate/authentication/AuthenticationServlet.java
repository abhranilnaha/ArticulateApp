package edu.sjsu.articulate.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;


import com.google.appengine.api.datastore.Query;

import edu.sjsu.articulate.model.JSONResponse;

public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = -74L;	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		
		String userEmail = request.getParameter("userEmail");
		Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL, userEmail);    	
    	Query query = new Query("User").setFilter(emailFilter);    	
    	PreparedQuery preparedQuery = dataStore.prepare(query);
    	
    	String password = null;
    	for (Entity result : preparedQuery.asIterable()) {    		
    		password = (String) result.getProperty("password");
    		break;    		
		}
    	
    	Gson gson = new Gson();
    	JSONResponse jsonObj = new JSONResponse();            	
    	jsonObj.setMessage(password);    	
    	String jsonOutput = gson.toJson(jsonObj);
    	
    	response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonOutput);
		out.close();    	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {		
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
	    String userEmail = jsonObj.get("userEmail").getAsString();
	    String userPassword = jsonObj.get("userPassword").toString();
    	
    	DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
    	
    	Entity user = new Entity("User");

    	user.setProperty("email", userEmail);
    	user.setProperty("password", userPassword);
    	   	
    	dataStore.put(user);
    	
    	Gson gson = new Gson();
    	JSONResponse jsonResponse = new JSONResponse();            	
    	jsonResponse.setMessage("User Information Saved Successfully!");    	
    	String jsonOutput = gson.toJson(jsonResponse);
    	
    	response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonOutput);
		out.close();
	}
}
