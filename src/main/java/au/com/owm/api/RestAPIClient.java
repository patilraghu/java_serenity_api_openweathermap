package au.com.owm.api;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.net.URI;

public class RestAPIClient {

    final private static EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

    public static CloseableHttpResponse postPayload(String url, String api_id, String registrationPayload) {
        try {
            URI uri = new URI( url + "?APPID=" + api_id);
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(registrationPayload));
            return buildHttpClient().execute(httpPost);
        } catch (Exception e) {
            Assert.fail("Exception during POST " + e);
        }
        return null;
    }

    public static CloseableHttpClient buildHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

}
