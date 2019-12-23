package com.studentapp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;


public class ReuseableSpecifications {

	public static RequestSpecBuilder reqSpecBuilder;
	public static RequestSpecification reqSpec;
	
	public static ResponseSpecBuilder resSpecBuilder;
	public static ResponseSpecification resSpec;
	
	/**
	 *   This Request Specification method used for general/commonly used settings (i.e Header/Param settings)
	 */
	public static RequestSpecification getGenericRequestSpec() {	
		reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.setContentType(ContentType.JSON);
		return reqSpec = reqSpecBuilder.build();
	}
	
	public static ResponseSpecification getGenericResponseSpec() {
		resSpecBuilder = new ResponseSpecBuilder();
		resSpecBuilder.expectHeader("Content-Type", "application/json;charset=UTF-8");												
		resSpecBuilder.expectHeader("Transfer-Encoding", "chunked");
		resSpecBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		return resSpec = resSpecBuilder.build();
		 
	}
	
}
