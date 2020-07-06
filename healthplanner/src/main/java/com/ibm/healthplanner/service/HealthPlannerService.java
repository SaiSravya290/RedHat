package com.ibm.healthplanner.service;


import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;


public interface HealthPlannerService {
	
	public JSONArray gettestWiseCount() throws IOException, ParseException;

	public JSONArray getstateWiseCount() throws IOException, ParseException;

	public JSONArray getcountryDetails(String state, String city) throws IOException, ParseException;
	
}
