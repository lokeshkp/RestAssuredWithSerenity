package com.studentapp.junitTests.studentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.model.Student;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.matchers.statematchers.HasValueMatcher;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase{

	static String firstName = "Lokesh"+TestUtils.getRandomValues();
	static String lastName = "Kondepudi"+TestUtils.getRandomValues();
	static String email = "lokesh"+TestUtils.getRandomValues()+"@gmail.com";
	static int studentId;

	@Title("Creating New Student")
	@Test
	public void test001(){
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java1");
		courses.add("Selenium1");

		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme("ComputerScience1");
		student.setCourses(courses);

		SerenityRest.rest().given().contentType(ContentType.JSON).log().all().
		when().body(student).post().
		then().log().all().statusCode(201);
	}



	@Title("Getting Student Info which was created in first testcase")
	@Test
	public void test002(){

		String p1 = "findAll{it.firstName =='";
		String p2 = p1+firstName+"'}.get(0)";

		HashMap<String,Object> value =  SerenityRest.rest().given().
				when().get("/list").
				then().extract().path(p2);

		assertThat(value,hasValue(firstName));
		studentId = (int) value.get("id");
	}

	@Title("Update user information and veify the update information")
	@Test
	public void test003(){
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java1");
		courses.add("Selenium1");

		firstName = firstName+"_updated";
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme("ComputerScience1");
		student.setCourses(courses);

		SerenityRest.rest().given().contentType(ContentType.JSON).log().all().
		when().body(student).put("/"+studentId).
		then().log().all();
	}

	@Title("Delte the student and verifing if deleted or not")
	@Test
	public void test004(){
		SerenityRest.rest().given().
		when().delete("/"+studentId).
		then().statusCode(204);
		
		SerenityRest.rest().given().
		when().get("/"+studentId).
		then().log().all().statusCode(404);

	}
}
