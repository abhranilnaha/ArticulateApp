package edu.sjsu.articulate.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.sjsu.articulate.model.JSONResponse;;

public class FileManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 2190879304615239209L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.print("<p>Error: The request method <code>"+req.getMethod()+"</code> is inappropriate for the URL <code>"+req.getRequestURI()+"</code></p>");
		out.close();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {		
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String action = req.getParameter("action");
		String fileId = req.getParameter("id");
		   	
    	// Delete
    	if ("delete".equals(action)) {
	    	boolean isRemoved = false;
	    	if (DatastoreUtils.isKey(fileId)) {
	    		isRemoved = DatastoreUtils.deleteGoogleFileById(fileId);
			}
	    	
	    	Gson gson = new Gson();
	    	JSONResponse jsonObj = new JSONResponse();            	
	    	jsonObj.setMessage(fileId);
	    	if (!isRemoved)
	    		jsonObj.setHasError(true);
	    	String jsonOutput = gson.toJson(jsonObj);
	    	
	    	out.println(jsonOutput);
    	}    	
    	out.close();
	}
}