package com.ibm.sdet.restassured.testcases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.ibm.sdet.restassured.contracts.PayLoad;

public class TestMapAPI{
	
	public static void addNewPlace() {
		given().log().all().queryParam("key", "qaclick123").contentType("application/json")
		.body(PayLoad.getPayLoad())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		}
	
	public static void getPlace() {
		String response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "57f4ca1ccda3762386d3cd212fa9d504").contentType("application/json")
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).body("address", equalTo("29, side layout, cohen 09"))
				.header("Content-Type", "application/json;charset=UTF-8")
				.extract().response().asString();
		System.out.println(response);
		
		JsonPath jsp = new JsonPath(response);
		
		jsp.get("name");
		
		System.out.println("Name: " + jsp.getString("name"));
	}
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//addNewPlace();
		getPlace();
		
		

	}

}
