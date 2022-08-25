package au.com.owm.glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class RegisterStation {
    @Steps
    au.com.owm.steps.RegisterStation registerStation;

    @Before
    public void beforeEachScenario(Scenario scenario) {}

    @After
    public void afterEachScenario(Scenario scenario) {}

    @Given("I like to register a weather station")
    public void init() {

    }

    @Given("with {string} as {string}")
    public void input(String key, String value) {
        registerStation.inputData(key, value);
    }


    @When("I post the payload with {string} API ID")
    public void postPayload(String type) {
        registerStation.postPayload(type);
    }

    @Given("I get {string} as {string}")
    public void verifyResponse(String key, String value) {
        registerStation.verifyResponse(key, value);
    }
}
