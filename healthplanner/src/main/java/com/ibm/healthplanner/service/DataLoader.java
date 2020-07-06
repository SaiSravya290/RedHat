package com.ibm.healthplanner.service;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class DataLoader {
	
//	https://api.covid19india.org/states_daily.json
//		https://api.covid19india.org/resources/resources.json
	
	public static void main(String args[]) throws IOException {
		getCasesCount();
	}

	public static void getCasesCount() throws IOException {
		
		URL url;
		try {
			url = new URL("https://api.covid19india.org/state_test_data.json");
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.connect();
			conn.setConnectTimeout(360000);
			
			String output = "";
			int resCode = conn.getResponseCode();
			
			if(resCode != 200) {
				throw new RuntimeException("HttpsResCode:"+resCode);
			}
			else {
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					output+= sc.nextLine();
				}
				sc.close();
			}
			JSONObject jsonObj = new JSONObject(output);
			System.out.println("json :: " + jsonObj);
			
			JSONArray arr = (JSONArray) jsonObj.get("states_tested_data");
			
			Resource res = new ClassPathResource("test.json");
			
			FileWriter file = new FileWriter(res.getFile());
	         file.write(arr.toString());
	         file.close();
	         System.out.println("saved file!! ");
	         
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
