package au.com.owm.api;

import au.com.owm.api.v3.registration.ResponseBody_201;
import au.com.owm.api.v3.registration.ResponseBody_401;
import au.com.owm.api.v3.registration.ValidPayload;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterStation extends PageObject {
    private static Logger LOGGER = LogManager.getLogger(RegisterStation.class);
    private CloseableHttpResponse registration_response;
    private ResponseBody_401 responseBody_401;
    private ResponseBody_201 responseBody_201;

    private ValidPayload validPayload = new ValidPayload();

    private final EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

    private final ObjectMapper serializer = new ObjectMapper()
            .configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
            //.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

    private final ObjectMapper deserializer = new ObjectMapper()
            .configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public void inputData(String key, String value) {
        switch(key.toLowerCase()) {
            case "external_id":
                validPayload.external_id = value;
                break;
            case "name":
                validPayload.name = value;
                break;
            case "latitude":
                validPayload.latitude = Double.parseDouble(value);
                break;
            case "longitude":
                validPayload.longitude = Double.parseDouble(value);;
                break;
            case "altitude":
                validPayload.altitude = Integer.parseInt(value);;
                break;
            default:
                Assert.fail("Invalid Key " + key);
                break;
        }
    }

    public void verifyResponse(String key, String value) {
        switch(key.toLowerCase()) {
            case "code":
                assertThat(registration_response.getStatusLine().getStatusCode()).isEqualTo(Integer.parseInt(value));
                break;
            case "message":
                assertThat(responseBody_401.message).isEqualTo(value);
                break;
            case "external_id":
                assertThat(responseBody_201.external_id).isEqualTo(value);
                break;
            case "name":
                assertThat(responseBody_201.name).isEqualTo(value);
                break;
            case "latitude":
                assertThat(responseBody_201.latitude).isEqualTo(Double.parseDouble(value));
                break;
            case "longitude":
                assertThat(responseBody_201.longitude).isEqualTo(Double.parseDouble(value));
                break;
            case "altitude":
                assertThat(responseBody_201.altitude).isEqualTo(Integer.parseInt(value));
                break;
            default:
                Assert.fail("Invalid Key " + key);
                break;
        }
    }

    public void postPayload(String type) {
        try {
            //Now serialize the Java object to Json String
            serializer.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            String payload =  serializer.writeValueAsString(validPayload);
            String base_url = variables.getProperty("openweathermap.url");
            String api_id = "";
            if (type.equalsIgnoreCase("<VALID>")) {
                api_id = variables.getProperty("openweathermap.api_id");
            }
            registration_response = RestAPIClient.postPayload(base_url,api_id, payload);
            assert registration_response != null;
            String registration_response_string = EntityUtils.toString(registration_response.getEntity());
            int response_code = registration_response.getStatusLine().getStatusCode();
            if (response_code == 401) {
                responseBody_401 = serializer.readValue(registration_response_string, ResponseBody_401.class);
            } else if (response_code == 201) {
                responseBody_201 = serializer.readValue(registration_response_string, ResponseBody_201.class);
            } else {
                LOGGER.warn("Unhandled response code :" + response_code);
            }

        } catch (Exception e) {
            Assert.fail("Exception during posting payload :" + e);
        }
    }
}
