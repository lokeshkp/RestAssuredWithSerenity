package com.studentapp.cucumber.serenity;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.Student;
import com.studentapp.utils.ReuseableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {

	@Step
	public ValidatableResponse createStudent(String firstName,String lastName,String email, String programme, List<String>courses) {
		
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme("ComputerScience1");
		student.setCourses(courses);

		return SerenityRest.rest().given().spec(ReuseableSpecifications.getGenericRequestSpec()).
				when().body(student).post().
				then().log().all().statusCode(201);
	}
	
	@Step("Updating Student Info with firstName {0} lastName {1} email {2} programme {3} courses {4} studentId {5}")
	public ValidatableResponse updateStudent(String firstName,String lastName,String email, String programme, List<String>courses,int studentId) {

		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme("ComputerScience");
		student.setCourses(courses);

		return SerenityRest.rest().given().spec(ReuseableSpecifications.getGenericRequestSpec()).
				when().body(student).put("/"+studentId).
				then().log().all();
	}
	
	@Step("Deleting Student information with studentId{0}")
	public void deleteStudent(int studentId) {
		SerenityRest.rest().given().
							when().delete("/"+studentId);
	}
	
	
	@Step("Getting Student Information with firstName:{0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
	
		String p1 = "findAll{it.firstName =='";
		String p2 = "'}.get(0)";

		return SerenityRest.rest().given().
									when().get("/list").
									then().extract().path(p1+firstName+p2);
	}
	
	
	@Step("Getting Student Info by studentId{0}")
	public ValidatableResponse getStudentById(int studentId) {
		return SerenityRest.rest().given().
									when().get("/"+studentId	).
									then();
	}
}
