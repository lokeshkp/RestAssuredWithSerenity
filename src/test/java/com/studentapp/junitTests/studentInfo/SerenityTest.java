package com.studentapp.junitTests.studentInfo;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import io.restassured.RestAssured;

@RunWith(SerenityRunner.class)
public class SerenityTest extends TestBase{

	
	@Title("Getting All Students information from Database")
	@Test
	public void getAllStudents(){
		SerenityRest.given().urlEncodingEnabled(false).
					when().get("/list").
					then().log().all().statusCode(200);
	}
	
	@Title("Fail Test")
	@Test
	public void failTest(){
		assertEquals(10, 20);
	}
	
	@Pending
	@Test
	public void pendingTest(){
		System.out.println("Pending Test");
	}
	

	@Ignore
	@Test
	public void ingnoreTest(){
		System.out.println("Ingnore Test");
	}
	
	@Test
	public void causeErrorTest(){
		System.out.println("Arthametic Error-"+5/0);
	}
	
	@Test
	public void fileDoestNotExitTest() throws FileNotFoundException{
		File file = new File("F:\\ds\\abc.txt");
		FileReader fr = new FileReader(file);
		System.out.println("Arthametic Error-"+5/0);
	}
	
	@Manual
	@Test
	public void manualTest(){
		System.out.println("Manual TestCase");
	}
}
