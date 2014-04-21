package edu.sjsu.articulate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ArticulateAppSetServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
        // We have one entity group per Guestbook with all Greetings residing
        // in the same entity group as the Guestbook to which they belong.
        // This lets us run a transactional ancestor query to retrieve all
        // Greetings for a given Guestbook.  However, the write rate to each
        // Guestbook should be limited to ~1/second.
        String guestbookName = "Frozen";
        Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
        String content = "An Animated Movie Made by Disney";
        Date date = new Date();
        Entity greeting = new Entity("Greeting", guestbookKey);        
        greeting.setProperty("date", date);
        greeting.setProperty("content", content);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(greeting);
        
        Query query = new Query("Greeting", guestbookKey).addSort("date", Query.SortDirection.DESCENDING);
        List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));

        //print the response
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("Data Saved Successfully");
        out.flush();
        out.close();
	}
}

