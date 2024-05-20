package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//created for UserEndpoints.java
//created to perform CRUD(Create, Retrieve, Update, Delete) operations over requests
//payload---> means Request body

public class UserEndPoints {

	public static Response createUser(UserPojo payload){
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
		    .post(Routes.post_url);
		
		return response;
	}
	
	public static Response readUser(String username){
		Response response = given()
		                      .pathParam("username",username)
		    .when()
		       .get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPojo payload){
		Response response = given()
				.contentType(ContentType.JSON)
			    .accept(ContentType.JSON)
				.pathParam("username", userName)
		       .body(payload)
		.when()
		    .put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName){
		Response response = given()
		                      .pathParam("username", userName)
		    .when()
		       .delete(Routes.delete_url);
		
		return response;
	}
}
