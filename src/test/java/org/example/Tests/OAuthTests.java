package org.example.Tests;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utility.ConfigUtil.ConfigProperties.*;
import static Utility.RequestSpecBuilder.RequestSpecBuilder.oAuthRequestSpec;
import static Utility.ResponseSpecBuilder.ResponseSpecBuilder.generalResponseSpec;
import static io.restassured.RestAssured.given;

@Slf4j
public class OAuthTests {

	private final String accessTokenKeyName = "access_token";
    @BeforeTest
	public void beforeTest(){
		RestAssured.baseURI = OAUTH_BASE_URL;
	}

	//@Test
	public void oAuthTest(){
		String response = given()
						.formParams("client_id", OAUTH_CLIENT_ID)
						.formParams("client_secret", OAUTH_CLIENT_SECRET)
						.formParams("grant_type", "client_credentials")
						.formParams("scope", "trust")
				.when()
						.log()
						.all()
						.post(OAUTH_TOKEN_ENDPOINT)
				.then()
						.log().all().extract().response()
						.jsonPath().getString(accessTokenKeyName);

		 given()
					.queryParams(accessTokenKeyName, response)
				.when().log().all()
					.get(COURSES_ENDPOINT)
				.then()
				.log().all().statusCode(401);
	}

	//@Test
	public void oAuthWithSpecBuilderTest(){
		log.info("Test started");
		String response = oAuthRequestSpec()
					.post(OAUTH_TOKEN_ENDPOINT)
				.then()
					.log().all().extract().response()
					.jsonPath().getString(accessTokenKeyName);
        log.debug(accessTokenKeyName);
		given()
				.queryParams(accessTokenKeyName, response)
				.when().log().all()
				.get(COURSES_ENDPOINT)
				.then()
				.spec(generalResponseSpec(401)).log().all();
		log.error("Test complete");
	}
}
