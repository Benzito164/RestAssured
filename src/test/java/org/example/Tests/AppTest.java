package org.example.Tests;

import Utility.JsonUtil.Mapper;
import Utility.Models.RegisterModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import static Utility.ConfigUtil.ConfigProperties.REGISTER_URL;
import static Utility.ConfigUtil.ConfigProperties.USERS_URL;
import static Utility.ConfigUtil.ConfigReader.GetConfigInformation;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class AppTest extends BaseTest {


  //  @Test
    public void GetUsers() {
        String response = given().queryParam("page","2")
                .when().get(GetConfigInformation(USERS_URL))
                .then().log().body().assertThat().statusCode(200)
                        .extract().response().asPrettyString();

        String pageNumber = readFromJsonNode(response,"data[0].last_name");
        assertThat(pageNumber).contains("Lawson");

    }

  //  @Test(dataProvider = "postData")
    public void postTest(String email,String password,int statusCode,String errorMessage,String token) throws JsonProcessingException {
       RegisterModel user = RegisterModel.init().SetEmail(email)
                        .SetPassword(password);


          RegisterModel model =  given().log().uri().header("Content-Type","application/json")
                .body(Mapper.ConvertClassToJson(user))
                .when().post(GetConfigInformation(REGISTER_URL))
                .then().log().all().statusCode(statusCode).extract().as(RegisterModel.class);
        assertThat(model.getError()).isEqualTo(errorMessage);
        assertThat(model.getToken()).isEqualTo(token);

    }


    @DataProvider(name = "postData")
    public Object[][] getData(){
       return new Object[][]{
                {"eve.holt@reqres.in","pistol",200,null,"QpwL5tke4Pnpja7X4"},
               {faker.internet().emailAddress(),faker.internet().password(),400,"Note: Only defined users succeed registration",null }
        };
    }



    private String readFromJsonNode(String jsonData,String nodeName){
        return new JsonPath(jsonData).get(nodeName).toString();
    }


}

