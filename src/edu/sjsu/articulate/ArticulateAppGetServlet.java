package edu.sjsu.articulate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.*;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ArticulateAppGetServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {	
		
		//sets the type of response and the charset used
        //Be careful, in your mobile client, read the response using the same charset
        resp.setContentType("application/json; charset=UTF-8");

        //create some clients and add them to the vector
        Vector vClients = new Vector();

        Client clientOne = new Client();
        clientOne.setFirstName("Abhranil");
        clientOne.setLastName("Naha");
        clientOne.setId("123456");

        vClients.add(clientOne);

        Client clientTwo = new Client();
        clientTwo.setFirstName("Varun");
        clientTwo.setLastName("Dixit");
        clientTwo.setId("987534");

        vClients.add(clientTwo);

        Client clientThree = new Client();
        clientThree.setFirstName("Asif");
        clientThree.setLastName("Nadaf");
        clientThree.setId("555555");

        vClients.add(clientThree);
        
        Client clientFour = new Client();
        clientFour.setFirstName("Harini");
        clientFour.setLastName("Aswin");
        clientFour.setId("555777");

        vClients.add(clientFour);
        
        Client clientFive = new Client();
        clientFive.setFirstName("Priya");
        clientFive.setLastName("Rajesh");
        clientFive.setId("555888");

        vClients.add(clientFive);

        //convert the clients vector to JSON using GSON, very easy!
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(vClients);

        System.out.println("*****JSON STRING TO RESPONSE*****");
        System.out.println(jsonOutput);
        System.out.println("*********************************");

        //print the response
        PrintWriter out = resp.getWriter();
        out.println(jsonOutput);
        out.flush();
        out.close();
	}
}
