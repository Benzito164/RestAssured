package Steps;

import Utility.Models.Ecommerce.CreateOrderModel;
import Utility.Models.Ecommerce.LoginResponseModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;

import static Utility.ConfigUtil.ConfigProperties.*;
import static Utility.RequestSpecBuilder.RequestSpecBuilder.ecommerceAddProductRequestSpec;
import static Utility.RequestSpecBuilder.RequestSpecBuilder.ecommerceLoginRequestSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class EcommerceSteps {


	String deleteConfirmationMessage;
	String createdProductId;
	LoginResponseModel loginResponseModel;

	@Given("i login successfully")
	public void i_login_successfully() {
		loginResponseModel = ecommerceLoginRequestSpec()
				.post(ECOM_LOGIN_PATH).then().extract()
				.response().as(LoginResponseModel.class);
		log.info(loginResponseModel.getToken());
	}

	@And("i create a new product")
	public void i_create_a_new_product() {
		createdProductId = ecommerceAddProductRequestSpec(loginResponseModel.getToken(), loginResponseModel.getUserId())
				.post(ECOM_ADD_PRODUCT).then().log().all().extract().response().jsonPath().get("productId");
		//66fd110eae2afd4c0b8c62cb- delete
		//66fd514aae2afd4c0b8cba18
	}

	@When("i add the created product to my basket")
	public void i_add_the_created_product_to_my_basket() {
		RequestSpecification createOrderBaseReq = new io.restassured.builder.RequestSpecBuilder()
				.setBaseUri(ECOM_BASE_URL).setContentType(ContentType.JSON).build();
		val order = CreateOrderModel.OrderDetailModel.builder().productOrderedId(createdProductId).country("uk").build();
		val orderArray = new ArrayList<CreateOrderModel.OrderDetailModel>();
		orderArray.add(order);
		val fullOrder = CreateOrderModel.builder().orders(orderArray).build();
		val orderStringInformation = given().spec(createOrderBaseReq).header("authorization",loginResponseModel.getToken())
				.body(fullOrder).when()
				.post(ECOM_CREATE_ORDER_PATH)
				.then().log().all().extract().response().asPrettyString();
		log.info(orderStringInformation);
	}

	@And("i delete the created order")
	public void i_delete_the_created_order() {
		RequestSpecification baseRequestSpec = new io.restassured.builder.RequestSpecBuilder()
				.setBaseUri(ECOM_BASE_URL).addHeader("authorization",loginResponseModel.getToken())
				.setContentType(ContentType.JSON).build();
		deleteConfirmationMessage = given().relaxedHTTPSValidation().log().all().spec(baseRequestSpec).pathParams("productId",createdProductId)
				.when().delete("/api/ecom/product/delete-product/{productId}").then()
				.log().all().extract().response().jsonPath().get("message");
	}

	@Then("i get a {string} message")
	public void i_get_a_message(String string) {
		assertThat(deleteConfirmationMessage).isEqualTo(string);
	}
}
