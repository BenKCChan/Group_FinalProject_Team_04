/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 *
 * @author ben
 */
public class RealTimeOilAPI {

    private final HttpClient http = HttpClient.newHttpClient();

    private volatile String lastResult;

    public RealTimeOilAPI() {
    }

    /**
     * Call API now, store result, and also return it.
     *
     * @throws java.lang.Exception
     */
    public String refreshNow() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://api.oilpriceapi.com/v1/demo/prices"))
                .GET()
                .build();

        HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
        lastResult = resp.body();
        return lastResult;
    }

    public String getLastResult() {
        return lastResult;
    }

}
