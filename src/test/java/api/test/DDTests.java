package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	/*
	 * Create multiple users by sending multiple post requests. 
	 * Everytime you will get the new data in the defined variables. ie, DataProvider will send the data to the variables
	 * 
	 */
	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String phno)
	{
		UserPojo userpayload = new UserPojo();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstname(fname);
		userpayload.setLastname(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phno);
		
		Response response =UserEndPoints.CreateUser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider = "usernames", dataProviderClass = DataProviders.class)
	public void testGetUserByName(String userName)
	{
		Response response=UserEndPoints.readUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}


	/*
	 * Delete the data whatever the data we created with the above requests
	 * We will be referring getUserNames to get the data and then we delete
	 */
	
	@Test(priority=3, dataProvider = "usernames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
