package au.com.owm.api.v3.registration;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseBody_201 {
    @JsonProperty
    public int count;

    @JsonProperty
    public int id;

    @JsonProperty
    public String name;
}
