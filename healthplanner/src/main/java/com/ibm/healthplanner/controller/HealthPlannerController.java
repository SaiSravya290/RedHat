package com.ibm.healthplanner.controller;

import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.healthplanner.model.Response;
import com.ibm.healthplanner.model.TestCount;
import com.ibm.healthplanner.service.HealthPlannerService;


import io.swagger.annotations.Api;
import springfox.documentation.spring.web.json.Json;
@RestController
@CrossOrigin(origins="*")
@RequestMapping(value={"/","/healthplanner"})
@Api(value="onlinestore", description="Operations pertaining to Health Advisor")
public class HealthPlannerController {
	
	private static final Logger log = LoggerFactory.getLogger(HealthPlannerController.class);
	
	@Autowired
	HealthPlannerService healthplannerService;
	
	@GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
	public String get(){
		return "Please give url as Spring-Boot-Rest/patient/get";
		
	}
	
	@GetMapping(value="/testsCount", headers="Accept=application/json")
	public ResponseEntity<Response> gettestCount() {
		
		Response res = new Response();
		try {
			JSONArray testWiseCountRes = healthplannerService.gettestWiseCount();
			res.setResponse(testWiseCountRes);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Response>(res, HttpStatus.OK);
	}
	
	@GetMapping(value="/state-wise", headers="Accept=application/json")
	public ResponseEntity<Response> getStateWiseDetails() {
		
		Response res = new Response();
		try {
			JSONArray stateWiseCountRes = healthplannerService.getstateWiseCount();
			res.setResponse(stateWiseCountRes);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Response>(res, HttpStatus.OK);
	}
	
	@GetMapping(value="/countryData/state/{state}/city/{city}", headers="Accept=application/json")
	public ResponseEntity<Response> getCountryDetails(@PathVariable("state") String state, @PathVariable("city") String city) {
		
		Response res = new Response();
		try {
			JSONArray countryRes = healthplannerService.getcountryDetails(state, city);
			res.setResponse(countryRes);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Response>(res, HttpStatus.OK);
	}
	
    
	
}
