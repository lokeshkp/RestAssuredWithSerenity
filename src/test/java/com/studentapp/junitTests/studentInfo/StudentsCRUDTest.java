package com.studentapp.junitTests.studentInfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.model.Student;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;


@RunWith(SerenityRunner.class)
public class StudentsCRUDTest extends TestBase{

	@Title("Creating New Student")
	@Test
	public void createNewStudent(){
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java1");
		courses.add("Selenium1");

		Student student = new Student();
		student.setFirstName("Lokesh"+TestUtils.getRandomValues());
		student.setLastName("Kondepudi"+TestUtils.getRandomValues());
		student.setEmail("lokesh"+TestUtils.getRandomValues()+"@gmail.com");
		student.setProgramme("ComputerScience1");
		student.setCourses(courses);

		SerenityRest.given().contentType(ContentType.JSON).log().all().
		when().body(student).post().
		then().log().all().statusCode(201);
	}
	
	
	
	@Title("Getting Student Info")
	@Test
	public void getStudent(){
		SerenityRest.given().
					when().get("/list").
					then().log().all().statusCode(200);
	}
}
