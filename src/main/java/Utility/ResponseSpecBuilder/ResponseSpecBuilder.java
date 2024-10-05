package Utility.ResponseSpecBuilder;


import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilder {

	public static ResponseSpecification generalResponseSpec(int statusCode){

		return new io.restassured.builder.ResponseSpecBuilder()
				.expectStatusCode(statusCode)
				.build();
	}
}
