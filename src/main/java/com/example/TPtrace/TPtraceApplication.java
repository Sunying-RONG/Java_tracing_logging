package com.example.TPtrace;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

@SpringBootApplication
public class TPtraceApplication {
	public static String TEST_PROJECT_PATH = "";
	private static JSONArray profil_read = new JSONArray();
	private static JSONArray profil_write = new JSONArray();
	private static JSONArray profil_read_expensive = new JSONArray();
	private static JSONObject writeList = new JSONObject();

	public static void main(String[] args) {
		SpringApplication.run(TPtraceApplication.class, args);

		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("/Users/rongsunying/eclipse-workspace/TPtrace/applicationExample.log"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray operationList = (JSONArray) obj;
            
            operationList.forEach(op -> createProfile((JSONObject)op));
            writeList.put("Read", profil_read);
            writeList.put("Write", profil_write);
            writeList.put("Read most expensive", profil_read_expensive);
            
          //Write JSON file
            try (FileWriter file = new FileWriter("/Users/rongsunying/eclipse-workspace/TPtrace/applicationProfil.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(writeList.toJSONString());
                String prettyJsonString = gson.toJson(je);
                file.write(prettyJsonString); 
                file.flush();
     
            } catch (IOException e) {
                e.printStackTrace();
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	private static void createProfile(JSONObject op) {
		
		JSONObject userDetails = new JSONObject();
		JSONArray operation_time = new JSONArray();
		
		if (op.get("loggerName").equals("com.example.TPtrace.controller.ProductController")) {
			
			String info = (String) op.get("message");
			String time = (String) op.get("time");
			
			String user_id = info.substring(info.indexOf("user_id=")+8, info.indexOf(", name="));
			String name = info.substring(info.indexOf("name=")+5, info.indexOf(", age="));
			String age = info.substring(info.indexOf("age=")+4, info.indexOf(", email="));
			String email = info.substring(info.indexOf("email=")+6, info.indexOf(", password="));
			String password = info.substring(info.indexOf("password=")+9, info.indexOf(']'));
			
			operation_time.add(time);
			
			userDetails.put("user_id", user_id);
			userDetails.put("name", name);
			userDetails.put("age", age);
			userDetails.put("email", email);
			userDetails.put("password", password);
			userDetails.put("operation_time", operation_time);
			
			if (info.contains("Read action")) {
				if (profil_read.size() == 0) {
					profil_read.add(userDetails);
				} else {
					for (int i=0; i<profil_read.size(); i++) {
						JSONObject user = (JSONObject) profil_read.get(i);
						if (user.get("user_id").equals(user_id)) {
							JSONArray times = (JSONArray) user.get("operation_time");
							times.add(time);
							return;
						}
					}
					profil_read.add(userDetails);
				}
				
			} else if (info.contains("Write action")) {
				if (profil_write.size() == 0) {
					profil_write.add(userDetails);
				} else {
					for (int i=0; i<profil_write.size(); i++) {
						JSONObject user = (JSONObject) profil_write.get(i);
						if (user.get("user_id").equals(user_id)) {
							JSONArray times = (JSONArray) user.get("operation_time");
							times.add(time);
							return;
						}
					}
					profil_write.add(userDetails);
				}
			} else if (info.contains("Read most expensive object")) {
				if (profil_read_expensive.size()==0) {
					profil_read_expensive.add(userDetails);
				} else {
					for (int i=0; i<profil_read_expensive.size(); i++) {
						JSONObject user = (JSONObject) profil_read_expensive.get(i);
						if (user.get("user_id").equals(user_id)) {
							JSONArray times = (JSONArray) user.get("operation_time");
							times.add(time);
							return;
						} 
					}
					profil_read_expensive.add(userDetails);
				}
			}
	
		}
		
	}

}
