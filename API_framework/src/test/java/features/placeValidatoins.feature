Feature: Validating Place API's 

@AddPlace @Regression
Scenario Outline: Verify if place is added successfully using AddPlaceApi
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceURL" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify that place_id created maps to "<name>" using "GetPlaceURL"
	
	
Examples:
	| name	| language | address 			|
	|U123House| English  | World crocs center |
#	|UUHouse1| English1  | World crocs center1 |


@DeletePlace @Regression
Scenario: Verify that Delete Place functionality is working

	Given DeletePlace Payload 
	When user calls "DeletePlaceURL" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	