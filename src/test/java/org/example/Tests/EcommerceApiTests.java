package org.example.Tests;


import Utility.Models.Ecommerce.CreateOrderModel;
import Utility.Models.Ecommerce.LoginResponseModel;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.testng.annotations.Test;

import java.util.ArrayList;
import static Utility.ConfigUtil.ConfigProperties.ECOM_BASE_URL;
import static Utility.ConfigUtil.ConfigProperties.ECOM_LOGIN_PATH;
import static Utility.RequestSpecBuilder.RequestSpecBuilder.ecommerceAddProductRequestSpec;
import static Utility.RequestSpecBuilder.RequestSpecBuilder.ecommerceLoginRequestSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class EcommerceApiTests {


	//@Test
	public void LoginTest(){
		//Login
	 LoginResponseModel loginResponseModel = ecommerceLoginRequestSpec()
				.post(ECOM_LOGIN_PATH).then().extract()
				.response().as(LoginResponseModel.class);
	  log.info(loginResponseModel.getToken());

	  // create product
	    String createdProductId = ecommerceAddProductRequestSpec(loginResponseModel.getToken(), loginResponseModel.getUserId())
			     .post("/api/ecom/product/add-product").then().log().all().extract().response().jsonPath().get("productId");
    //66fd110eae2afd4c0b8c62cb- delete
    //66fd514aae2afd4c0b8cba18


		//create order
		RequestSpecification createOrderBaseReq = new io.restassured.builder.RequestSpecBuilder()
				.setBaseUri(ECOM_BASE_URL).setContentType(ContentType.JSON).build();
		val order = CreateOrderModel.OrderDetailModel.builder().productOrderedId(createdProductId).country("uk").build();
		val orderArray = new ArrayList<CreateOrderModel.OrderDetailModel>();
		 orderArray.add(order);
		val fullOrder = CreateOrderModel.builder().orders(orderArray).build();
		val orderStringInformation = given().spec(createOrderBaseReq).header("authorization",loginResponseModel.getToken()).body(fullOrder).when()
				.post("api/ecom/order/create-order")
				.then().log().all().extract().response().asPrettyString();
		log.info(orderStringInformation);


		// delete order
		RequestSpecification baseRequestSpec = new io.restassured.builder.RequestSpecBuilder()
				.setBaseUri(ECOM_BASE_URL).addHeader("authorization",loginResponseModel.getToken())
				.setContentType(ContentType.JSON).build();
		String deleteMessage = given().relaxedHTTPSValidation().log().all().spec(baseRequestSpec).pathParams("productId",createdProductId)
				.when().delete("/api/ecom/product/delete-product/{productId}").then()
				.log().all().extract().response().jsonPath().get("message");
		assertThat(deleteMessage).isEqualTo("Product Deleted Successfully");

	}
}
