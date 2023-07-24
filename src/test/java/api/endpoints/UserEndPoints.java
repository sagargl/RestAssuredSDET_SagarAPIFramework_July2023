package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created for Perform CRUD Operations- Create, Read, Update and Delete Requests to the User API.

public class UserEndPoints {
	
	public static Response CreateUser(UserPojo payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
			
			return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response=given()
					.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
			
			return response;
	}
	
	public static Response updateUser(String userName, UserPojo payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
		.when()
			.put(Routes.update_url);
			
			return response;
	}

	public static Response deleteUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url);
			
			return response;
	}

}
