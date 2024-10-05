package org.example.Tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeTest;
import static Utility.ConfigUtil.ConfigProperties.BASE_URL;
import static Utility.ConfigUtil.ConfigReader.GetConfigInformation;

public class BaseTest {

	Faker faker;
	@SneakyThrows
	@BeforeTest
	public void setUp(){
		RestAssured.baseURI = GetConfigInformation(BASE_URL);
		faker = new Faker();
	}
}
