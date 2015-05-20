package com.wakaleo.training.webservices.jbehave.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class UrlShortenerSteps extends ScenarioSteps {

    RestTemplate restTemplate;
    // The google-Api is only useable with an access-key as described here: https://developers.google.com/url-shortener/v1/getting_started#APIKey
    // You need to activate the api at https://console.developers.google.com/project/{yourProjectNameHere}/apiui/apis/library and then get the api-key 
    // from https://console.developers.google.com/project/{yourProjectNameHere}/apiui/credential
    String googleAccessKey = "somethingLikeAIzaSyBm8RAN4jzBiVQ0NOC91vYat7smWVW2azQ";

    public UrlShortenerSteps() {
        restTemplate = new RestTemplate();
    }

    @Step("Shorten Url for longUrl={0}")
    public String shorten(String providedUrl) {
        Map<String, String> urlForm = new HashMap<String, String>();
        urlForm.put("longUrl", providedUrl);
        return restTemplate.postForObject("https://www.googleapis.com/urlshortener/v1/url", urlForm, String.class);
    }

    @Step("Expand Url for shortUrl={0}")
    public String expand(String providedUrl) {
        return restTemplate.getForObject("https://www.googleapis.com/urlshortener/v1/url?shortUrl={shortUrl}?key=" + googleAccessKey, String.class, providedUrl);
    }

    @Step
    public void response_should_contain_shortened_url(String returnedMessage, String expectedUrl) throws JSONException {
        String expectedJSONMessage = "{'id':'" + expectedUrl + "'}";
        JSONAssert.assertEquals(expectedJSONMessage, returnedMessage, JSONCompareMode.LENIENT);
    }

    @Step
    public void response_should_contain_long_url(String returnedMessage, String expectedUrl) throws JSONException {
        String expectedJSONMessage = "{'longUrl':'" + expectedUrl + "'}";
        JSONAssert.assertEquals(expectedJSONMessage, returnedMessage, JSONCompareMode.LENIENT);
    }
}
