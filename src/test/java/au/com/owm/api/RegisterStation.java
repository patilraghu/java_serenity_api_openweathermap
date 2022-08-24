package au.com.owm.api;

import au.com.owm.api.v3.registration.ResponseBody_401;
import au.com.owm.api.v3.registration.ValidPayload;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterStation extends PageObject {
    private CloseableHttpResponse registration_response;
    private ResponseBody_401 responseBody_401;

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
                assertThat(Integer.parseInt(value)).isEqualTo(registration_response.getStatusLine().getStatusCode());
                break;
            case "message":
                assertThat(value).isEqualTo(responseBody_401.message);
                break;
            default:
                Assert.fail("Invalid Key " + key);
                break;
        }
    }

    public void postPayloadWithoutApiID() {
        try {
            //Now serialize the Java object to Json String
            serializer.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            String payload =  serializer.writeValueAsString(validPayload);
            String base_url = variables.getProperty("openweathermap.url");

            registration_response = RestAPIClient.postPayload(base_url,"", payload);
            assert registration_response != null;
            String registration_response_string = EntityUtils.toString(registration_response.getEntity());
            responseBody_401 = serializer.readValue(registration_response_string, ResponseBody_401.class);
        } catch (Exception e) {
            Assert.fail("Error" + e);
        }
    }
}
