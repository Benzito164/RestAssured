package org.example.Tests;

import Utility.Models.Jira.JiraIssueResponse;
import Utility.Models.Jira.JiraModel;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;

import static Utility.ConfigUtil.ConfigProperties.*;
import static io.restassured.RestAssured.given;
@Slf4j
public class JiraAttachmentTest extends BaseTest {

	//@BeforeTest
	public void beforeTest(){
		RestAssured.baseURI = JIRA_BASE_URL;
	}

	//@Test
	public void jiraScreenShotAttachmentTest(){

		val projectInfo  = new HashMap<String,String>();
		projectInfo.put("key","KAN");
		val bugType = new HashMap<String,String>();
		bugType.put("name","Bug");

		val jiraBugDetails = JiraModel.BugFields.builder()
				.project(projectInfo)
				.summary(faker.educator().course())
				.issuetype(bugType).build();

		JiraModel jiraIssue = JiraModel.builder()
				.fields(jiraBugDetails).build();

		val bugInfo = given().header("Content-Type","application/json")
							.header("Authorization","Basic "+JIRA_BASE_64_ENCODE)
							.body(jiraIssue)
							.log().all().
					when().post(JIRA_RESOURCE_PATH).then()
				          .log().all().statusCode(201)
				          .extract().as(JiraIssueResponse.class);

		given()
				.header("X-Atlassian-Token","no-check")
				.header("Authorization","Basic "+JIRA_BASE_64_ENCODE)
				.multiPart("file",new File("react image.png")).log().all()
				.post(JIRA_RESOURCE_PATH+"/"+bugInfo.getId()+"/attachments").then().log().all()
				.assertThat().statusCode(200);
	}

}
