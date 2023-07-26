package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created for Perform CRUD Operations- Create, Read, Update and Delete Requests to the User API.

public class UserEndPoints2 {
	
	public static Properties prop;
	
	public UserEndPoints2() throws IOException
	{
	prop = new Properties();

		FileInputStream ip =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com" + "/web/qa/config/config.properties");
		prop.load(ip);
	}
	

	
	
	//Created for getting urls from properties file
		static ResourceBundle getURL()
		{
		//Properties prop = new Properties();
			ResourceBundle routes = ResourceBundle.getBundle("routes");// Load Properties
			return routes;
		}
	public static Response CreateUser(UserPojo payload)
	{
		//String post_url=getURL().getString("post_url");
		String post_url=prop.getProperty("post_url");
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
			
			return response;
	}
	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url");
		Response response=given()
					.pathParam("username", userName)
		.when()
			.get(get_url);
			
			return response;
	}
	
	public static Response updateUser(String userName, UserPojo payload)
	{
		String update_url=getURL().getString("update_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
		.when()
			.put(update_url);
			
			return response;
	}

	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url");
		
		Response response=given()
				.pathParam("username", userName)
		.when()
			.delete(delete_url);
			
			return response;
	}

}
