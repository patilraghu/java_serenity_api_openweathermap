package au.com.owm.api.v3.registration;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseBody_201 {
    @JsonProperty
    public String ID;

    @JsonProperty
    public String updated_at;

    @JsonProperty
    public String created_at;

    @JsonProperty
    public String user_id;

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

    @JsonProperty
    public int rank;

    @JsonProperty
    public int source_type;
}
