package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTests2 {
	

	Faker faker;
	UserPojo userpayload;
	
	@BeforeClass
	public void setUpData()
	{
		/* 
		 * 1. In this method i will generate the data using faker library
		 * 2. And that data I will pass it to the Pojo Class, So that data will be ready
		 * 3. And that data we will pass it along with the Post Request
		 * 4. Whatever data we created using faker class the same data we have to pass this to pojo class, Both should be done parallely
		 * 5. Let us take an id and want to assign the id value, From the user payload we have to call one method to set the id value-setId()
		 * 6. And this id we have to generate using an faker library. faker.idNumber()
		 * 
		 */
		
		faker = new Faker();
		userpayload = new UserPojo();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void TestPostUser()
	{
		Response response=UserEndPoints2.CreateUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response=UserEndPoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		/*
		 * 1. For updating the payload we require 
		 * a. Username--> i.e, For the specific user we are going to update
		 * b. Userpayload-->With the payload we send the updated payload ie., setFirstName, setLastName and soon...
		 * 
		 * 
		 */
		
		//Update data using payload
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response= UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		Response responseafterupdate = UserEndPoints2.readUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);	
		
	}
	
	@Test(priority=4)
	public void testDeleteByUserName()
	{
		Response response=UserEndPoints2.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
