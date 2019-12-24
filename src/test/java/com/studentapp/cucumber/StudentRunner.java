package com.studentapp.cucumber;

import org.junit.runner.RunWith;
import com.studentapp.testbase.TestBase;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="C:\\Users\\lokesh.kondepudi\\workspace\\RestAssured\\src\\test\\resources\\features")
public class StudentRunner extends TestBase {
		
}