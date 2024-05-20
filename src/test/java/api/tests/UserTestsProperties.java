package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpointsProperties;
import api.payloads.UserPojo;
import io.restassured.response.Response;

public class UserTestsProperties {
	Faker faker;
	UserPojo userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new UserPojo();
		
		//hashCode is used to generate random number
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//logs
		logger= LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser(){
		
		logger.info("*********Creating user*********");
		Response response = UserEndpointsProperties.createUser(userPayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
		 logger.info("*********User is Created*********");
	}
	@Test(priority=2)
	public void testGetUserByName() {
		
		logger.info("*********Reading user Info*********");
		Response response = UserEndpointsProperties.readUser(this.userPayload.getUsername());
         response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*********User Info is displayed*********");
	}
	@Test(priority=3)
	public void testUpdateUserByName(){
		
		logger.info("*********Updating user Info*********");
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpointsProperties.updateUser(this.userPayload.getUsername(), userPayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);	
		 logger.info("*********user is Updated*********");
		 
		 //checking data after update:
		 Response responseAfterUpdate = UserEndpointsProperties.readUser(this.userPayload.getUsername());
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=4)
	public void testDeleteUserByName(){
		logger.info("*********Deleting user Info*********");
		Response response = UserEndpointsProperties.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);	
		logger.info("*********User Deleted*********");
	}
}
