package com.studentapp.junitTests.studentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.Student;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReuseableSpecifications;
import com.studentapp.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.matchers.statematchers.HasValueMatcher;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase{

	static String firstName = "Lokesh"+TestUtils.getRandomValues();
	static String lastName = "Kondepudi"+TestUtils.getRandomValues();
	static String email = "lokesh"+TestUtils.getRandomValues()+"@gmail.com";
	static String programme = "Computer";
	static int studentId;
	
	@Steps
	StudentSerenitySteps steps;

	@Title("Creating New Student with firstName: {0}, lastName: {1}, email: {2}, programme: {3}, courses: {4}")
	@Test
	public void test001(){
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java1");
		courses.add("Selenium1");
		steps.createStudent(firstName, lastName, email, programme, courses).statusCode(201).
		spec(ReuseableSpecifications.getGenericResponseSpec());
	}


	@Title("Getting Student Info which was created in first testcase")
	@Test
	public void test002(){
		HashMap<String, Object> value =steps.getStudentInfoByFirstName(firstName);
		assertThat(value,hasValue(firstName));
		studentId = (int) value.get("id");
	}

	@Title("Update user information and veify the update information")
	@Test
	public void test003(){
		String p1 = "findAll{it.firstName =='";
		String p2 = "'}.get(0)";
		
		firstName = firstName +"_updated";

		ArrayList<String> courses = new ArrayList<>();
		courses.add("JAVA");
		courses.add("Cypress");

		steps.updateStudent(firstName, lastName, email, programme, courses,studentId);
		HashMap<String,Object> value= steps.getStudentInfoByFirstName(firstName);
		assertThat(value, hasValue(firstName));
	}

	@Title("Delete the student and verifing if deleted or not")
	@Test
	public void test004(){
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).log().all().statusCode(404);
	}

}
