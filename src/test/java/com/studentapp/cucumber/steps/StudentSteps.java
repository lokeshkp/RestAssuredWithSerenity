package com.studentapp.cucumber.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.utils.TestUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class StudentSteps {

	@Steps
	StudentSerenitySteps step;

	@When("^user sends a GET request to the list endpoint, they must get back a valid status code 200$")
	public void verify_status_code_200_for_list_endpoint(){
		SerenityRest.rest().
		given().
		when().get("/list").
		then().statusCode(200);
	}


	@When("^I create new student by providing the following information firstName (.*?) lastName (.*?) email (.*?) programme (.*?) courses (.*?)$")
	public void createStudent(String firstName, String lastName, String email, String programme, String course) {
		List<String> courses = new ArrayList<>();
		courses.add(course);
		step.createStudent(firstName, lastName, email, programme, courses).
		statusCode(201);
	}


	@Then("^I verify that the student with (.*?) is created$")
	public void verifyStudent(String email) {
		HashMap<String, Object> actValue = step.getStudentInfoByEmailId(email);
		System.err.println("Created Email-->"+actValue);
		assertThat(actValue, hasValue(email));
	}
}
