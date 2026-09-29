package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepDefinitions"}, plugin= "json:target/jsonReports/cucmber-report.json", tags= "@AddPlace or @DeletePlace" 
)
public class TestRunner {
}