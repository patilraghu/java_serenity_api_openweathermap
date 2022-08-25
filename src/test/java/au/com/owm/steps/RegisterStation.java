package au.com.owm.steps;

import net.thucydides.core.steps.ScenarioSteps;

public class RegisterStation extends ScenarioSteps {

    au.com.owm.api.RegisterStation registerStation;

    public void inputData(String key, String value) {
        registerStation.inputData(key, value);
    }

    public void verifyResponse(String key, String value) {
        registerStation.verifyResponse(key, value);
    }

    public void postPayload(String type) {
        registerStation.postPayload(type);
    }
}
