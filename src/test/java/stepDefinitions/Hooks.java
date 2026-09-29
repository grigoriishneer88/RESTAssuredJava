package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinitions step = new StepDefinitions();
		if(StepDefinitions.response_obj==null) {
			step.add_place_payload("ssd", "frank", "euroPe");
			step.user_calls_with_http_request("AddPlaceURL", "POST");
			step.verify_that_place_id_created_maps_to_using("ssd", "GetPlaceURL");
		}
	}
}
