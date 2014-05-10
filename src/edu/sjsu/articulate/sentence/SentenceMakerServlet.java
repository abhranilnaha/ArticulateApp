package edu.sjsu.articulate.sentence;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.sjsu.articulate.model.JSONResponse;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class SentenceMakerServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.print("<p>Error: The request method <code>"+req.getMethod()+"</code> is inappropriate for the URL <code>"+req.getRequestURI()+"</code></p>");
		out.close();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String noun = req.getParameter("noun");
		String verb = req.getParameter("verb");
		String object = req.getParameter("object");
		String extra = req.getParameter("extra");
		String tense = req.getParameter("tense");
		String negation  = req.getParameter("negation");
		String question  = req.getParameter("question");		
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        p.setSubject(noun);
        p.setVerb(verb);
        p.setObject(object);
        
        if (extra != null && !extra.equals("")) {
        	p.addComplement(extra);
        }
        
        Realiser realiser = new Realiser(lexicon);
                
        // Add Extra Feature        
        if (tense.equals("past")) {
        	p.setFeature(Feature.TENSE, Tense.PAST);
        } else if (tense.equals("future")) {
        	p.setFeature(Feature.TENSE, Tense.FUTURE);
		}
        
		if (negation.equals("yes")) {
        	p.setFeature(Feature.NEGATED, true);
		}
		
		if (question.equals("yes")) {
			p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.YES_NO);
		}		
        
        String output = realiser.realiseSentence(p);
        
        Gson gson = new Gson();
    	JSONResponse jsonObj = new JSONResponse();            	
    	jsonObj.setMessage(output);    	
    	String jsonOutput = gson.toJson(jsonObj);
        
		out.println(jsonOutput);
		out.close();
	}
}
