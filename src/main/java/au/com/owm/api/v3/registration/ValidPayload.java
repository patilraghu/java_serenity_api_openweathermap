package au.com.owm.api.v3.registration;

import org.codehaus.jackson.annotate.JsonProperty;

public class ValidPayload {
    @JsonProperty
    public String external_id;

    @JsonProperty
    public String name;

    @JsonProperty
    public double latitude;

    @JsonProperty
    public double longitude;

    @JsonProperty
    public int altitude;
}
