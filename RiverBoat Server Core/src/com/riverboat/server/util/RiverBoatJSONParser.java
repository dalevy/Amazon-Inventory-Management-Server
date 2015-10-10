
package com.riverboat.server.util;

import java.util.HashMap;
;


public class RiverBoatJSONParser {

	private String response;
	
	public RiverBoatJSONParser(String response){
		
		this.response = response;
		
	}
	
	
	//Verify the integrity of the JSON String
	public boolean properlyformed(){
		
		boolean verified = false;
		
		if(response.charAt(0) =='{' && response.charAt(response.length()-1) =='}')
			verified = true;
		
		return verified;
	}
	
	
	//Extract key value pairs
	public HashMap<String,String> getKeyValuePairs(){
		
		String holder =response.replaceAll("[{}\"]","");
		String temp="";
		String key="empty";
		String value ="empty";
		HashMap<String,String> results = new HashMap<>();
		for(int i = 0; i < holder.length(); i++)			
		{
			
			if(holder.charAt(i)==':')
			{
					key = temp;
					temp ="";
			}else if(holder.charAt(i)==','){
					value = temp;
					results.put(key, value);
					temp ="";
			}else{
					temp += holder.charAt(i);
			}

		}
		
		//last pair
		results.put(key, temp);
		
		return results;
		
	}
	
	
}
