package com.ibm.healthplanner.model;

import org.json.simple.JSONArray;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("response")
	private JSONArray response;

	public JSONArray getResponse() {
		return response;
	}

	public void setResponse(JSONArray response) {
		this.response = response;
	}
	
	

}
