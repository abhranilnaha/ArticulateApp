package edu.sjsu.articulate.util;

import edu.sjsu.articulate.model.JSONResponse;

public class ServiceUtil {
	
	public static JSONResponse createErrorResponse(Exception e) {
		JSONResponse jr = new JSONResponse();
		jr.setHasError(true);
		jr.setMessage(e.getMessage());
		jr.setData(null);
		
		return jr;
	}
	
	public static JSONResponse buildJSONResponse(Object data) {
		JSONResponse jr = new JSONResponse();
		jr.setHasError(false);
		jr.setMessage("OK");
		jr.setData(data);
		return jr;
	}
	
}
