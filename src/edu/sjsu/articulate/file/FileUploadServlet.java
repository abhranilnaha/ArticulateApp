package edu.sjsu.articulate.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 8367618333138027430L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.print("<p>Error: The request method <code>"+req.getMethod()+"</code> is inappropriate for the URL <code>"+req.getRequestURI()+"</code></p>");
		out.close();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		
		// See appengine-web.xml
		long maxsize = Long.parseLong("10240000");
		
		boolean isFailureSubmit = false;
		boolean isDuplicatedId = false;
		boolean isSizeLimitExceeded = false;
		String fileId = "";
		String fileOwner = "";
		String fileName = "";
		int fileSize = -1;
		String contentType = "";
		String message = "";
		   	
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			isFailureSubmit = true;			
		}
		    	
		try {
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload();
			// Set overall request size constraint: the default value of -1 indicates that there is no limit.
			upload.setSizeMax(maxsize); //the maximum allowed size, in bytes.
			// Set the UTF-8 encoding to grab the correct uploaded filename, especially for Chinese
			upload.setHeaderEncoding("UTF-8");
						
			// Parse the request
			FileItemIterator iter = upload.getItemIterator(req);				
			while (iter.hasNext()) {
			    FileItemStream item = iter.next();
				InputStream stream = item.openStream();
			    String fieldName = item.getFieldName();
			   
		    	// Process a file upload
			    fileId = "f"+String.valueOf(new Date().getTime());
		        fileOwner = "test@example.com";
		        fileName = item.getName();
		        if (fileName != null)
		        	fileName= FilenameUtils.getName(fileName);
		        contentType = item.getContentType();
		        
		        if (fieldName.equals("message")) {
		        	message = Streams.asString(stream, "UTF-8");
		        }
		        
		        if (fieldName.equals("file")) {
		        	// Check if the fileId conforms to the Key format of the Google datastore 
	        		// and all other uploaded fields are not empty.
		        	if (DatastoreUtils.isKey(fileId) && fileOwner.length() > 0 && fileName.length() > 0) {
			        	// Save into Google datastore
			        	fileSize = DatastoreUtils.insertGoogleFile(fileId, fileOwner, fileName, contentType, message, stream);
			        	if (fileSize < 0) {
			        		//fileSize == -1 or -2
			        		isFailureSubmit = true;
			        		if (fileSize == -2)
				        		isDuplicatedId = true;
			        	}
		        	} else {
		        		isFailureSubmit = true;
		        	}
		        }			    
			} //end while
		} catch (IOException e) {
			// Upload fail
			isFailureSubmit = true;			
		} catch (FileUploadException e) {
			// Upload fail				
			isFailureSubmit = true;
			isSizeLimitExceeded = true;				
		}
    	
    	
		if (!isFailureSubmit) {
			out.println("File Uploaded Successfully!");
		}
		
		out.close();
	}
}