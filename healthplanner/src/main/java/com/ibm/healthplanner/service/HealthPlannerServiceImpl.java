package com.ibm.healthplanner.service;

import java.io.IOException;
import java.io.InputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.healthplanner.model.CountryPojo;
import com.ibm.healthplanner.model.StateWisePojo;
import com.ibm.healthplanner.model.TestCount;

@Service
public class HealthPlannerServiceImpl implements HealthPlannerService {

	private static final Logger log = LoggerFactory.getLogger(HealthPlannerServiceImpl.class);

	@Override
	public JSONArray gettestWiseCount() throws IOException, ParseException {

		Resource res = new ClassPathResource("testCount.json");
		InputStream is = res.getInputStream();

		ObjectMapper mapper = new ObjectMapper();
		log.info("testcount before returning ::: " + is.toString());
		TestCount testcount[] = mapper.readValue(is, TestCount[].class);
		String testcountString = mapper.writeValueAsString(testcount);

		JSONParser parser = new JSONParser();
		JSONArray json = (JSONArray) parser.parse(testcountString);

		return json;

	}

	@Override
	public JSONArray getstateWiseCount() throws IOException, ParseException {

		Resource res = new ClassPathResource("statesWise.json");
		InputStream is = res.getInputStream();

		ObjectMapper mapper = new ObjectMapper();
		log.info("testcount before returning ::: " + is.toString());
		StateWisePojo statecount[] = mapper.readValue(is, StateWisePojo[].class);
		String stateWiseString = mapper.writeValueAsString(statecount);

		JSONParser parser = new JSONParser();
		JSONArray json = (JSONArray) parser.parse(stateWiseString);

		return json;

	}

	@Override
	public JSONArray getcountryDetails(String state, String city) throws IOException, ParseException {

		Resource res = new ClassPathResource("countryWise.json");
		InputStream is = res.getInputStream();

		ObjectMapper mapper = new ObjectMapper();
		log.info("testcount before returning ::: " + is.toString());
		CountryPojo country[] = mapper.readValue(is, CountryPojo[].class);
		String countryString = mapper.writeValueAsString(country);

		JSONParser parser = new JSONParser();
		JSONArray json = (JSONArray) parser.parse(countryString);
		
		JSONArray finalJson = new JSONArray();
		
		for(int i = 0; i<json.size(); i++) {
			JSONObject obj = (JSONObject) json.get(i);
			
			if(obj.get("state").equals(state) && obj.get("city").equals(city)) {
				finalJson.add(obj);
			}
		}

		return finalJson;

	}

}
