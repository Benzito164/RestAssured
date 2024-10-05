package Utility.RequestSpecBuilder;
import Utility.Models.Ecommerce.LoginRequestModel;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.io.*;
import static Utility.ConfigUtil.ConfigProperties.*;


public class RequestSpecBuilder {

	static  PrintStream stream;

	static {
		try {
			stream = new PrintStream(new FileOutputStream("logging.txt"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SneakyThrows
	public static RequestSpecification oAuthRequestSpec(){

		return  RestAssured.given().spec(new io.restassured.builder.RequestSpecBuilder()
						.addFilter(RequestLoggingFilter.logRequestTo(stream))
						.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setBaseUri(OAUTH_BASE_URL)
				.addFormParam("client_id",OAUTH_CLIENT_ID)
				.addFormParam("client_secret", OAUTH_CLIENT_SECRET)
				.addFormParam("grant_type", "client_credentials")
				.addFormParam("scope", "trust").build().when()
				.log()
				.all());
	}

	public static RequestSpecification ecommerceLoginRequestSpec(){
		return  RestAssured.given().spec(new io.restassured.builder.RequestSpecBuilder()
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setBaseUri(ECOM_BASE_URL)
				.setContentType(ContentType.JSON)
				.build().log().all()
				.body(LoginRequestModel.builder().userEmail(ECOM_USERNAME).userPassword(ECOM_PASSWORD).build()));
	}

	public static RequestSpecification ecommerceAddProductRequestSpec(String headerValue,String userId){
		return  RestAssured.given().spec(new io.restassured.builder.RequestSpecBuilder()
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setBaseUri(ECOM_BASE_URL)
						.addHeader("authorization",headerValue)
				.build().param("productName","Laptop")
						.param("productAddedBy",userId)
						.param("productCategory","fashion")
						.param("productSubCategory","shirts")
						.param("productDescription","Lenovo")
						.param("productFor","men")
						.param("productPrice","11500")
						.multiPart("productImage",new File("react image.png"))
				.log().all());

	}
}
