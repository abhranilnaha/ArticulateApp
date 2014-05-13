package edu.sjsu.articulate.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import edu.sjsu.articulate.Client;
import edu.sjsu.articulate.PMF;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

public class FileListServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(GoogleFile.class);
		
		query.setFilter("fileOwner == 'test@example.com'");
		query.setOrdering("date desc");
		query.setRange(0, 10);
		List<GoogleFile> entities = (List<GoogleFile>) query.execute();
		Vector vFiles = new Vector();
		
		if (!entities.isEmpty()) {		
			for (GoogleFile gFile : entities) {				
				int fileSize = gFile.getFileSize();
				String contentType = gFile.getContentType();
				Date fileDate = gFile.getDate();
				String fileId = gFile.getId();
				String fileOwner = gFile.getFileOwner();
				String fileName = gFile.getFileName();
				String message = gFile.getMessage();
				
				GoogleFile googleFile = new GoogleFile(fileId, fileOwner, fileName, fileSize, contentType, message, null);
				googleFile.setDate(fileDate);
				vFiles.add(googleFile);
			}
		}
		
        //convert the clients vector to JSON using GSON, very easy!
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(vFiles);

        System.out.println("*****JSON STRING TO RESPONSE*****");
        System.out.println(jsonOutput);
        System.out.println("*********************************");

        //print the response
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(jsonOutput);
        out.flush();
        out.close();
		pm.close();
	}

}
