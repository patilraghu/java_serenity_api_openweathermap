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

    @Given("{string} as {string}")
    public void input(String key, String value) {
        registerStation.inputData(key, value);
    }


    @When("Payload posted without API key")
    public void postPayloadWithoutApiID() {
        registerStation.postPayloadWithoutApiID();
    }

    @Given("{string} is {string}")
    public void verifyResponse(String key, String value) {
        registerStation.verifyResponse(key, value);
    }
}
