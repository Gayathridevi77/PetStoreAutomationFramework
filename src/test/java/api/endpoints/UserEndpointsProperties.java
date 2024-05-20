package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpointsProperties {
	
	//method for getting url's from routes.properties file
	public static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //here routes is the name of the routes.properties file 
		                                                              //which is present in src/test/resources
		return routes;
	}

	public static Response createUser(UserPojo payload){
		
		String post_url = getURL().getString("post_url");
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
		    .post(post_url);
		
		return response;
	}
	
	public static Response readUser(String username){
		String get_url = getURL().getString("get_url");
		Response response = given()
		                      .pathParam("username",username)
		    .when()
		       .get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPojo payload){
		String update_url = getURL().getString("update_url");
		Response response = given()
				.contentType(ContentType.JSON)
			    .accept(ContentType.JSON)
				.pathParam("username", userName)
		       .body(payload)
		.when()
		    .put(update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName){
		String delete_url = getURL().getString("delete_url");
		Response response = given()
		                      .pathParam("username", userName)
		    .when()
		       .delete(delete_url);
		
		return response;
	}
}
