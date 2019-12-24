Feature: Testing different requests on the Student application

@SMOKE
Scenario: Check if the student application can be accessed by users
	When user sends a GET request to the list endpoint, they must get back a valid status code 200 
	
@Regression
Scenario Outline: Creating a new student & verify if the student is added
	When I create new student by providing the following information firstName <firstName> lastName <lastName> email <email> programme <programme> courses <courses>
	Then I verify that the student with <email> is created
	
	Examples:
	|firstName		|lastName			|email					|programme		|courses	|
	|SithaRama		|LaxmanaHanuma		|Sitharama@gmail.com	|Ramayanam		|Jeevitham	|
	|KrishnaArjuna	|DharmaRajBheema	|Krishnaa@gmail.com		|MahaBharatam	|Jeevitham	|