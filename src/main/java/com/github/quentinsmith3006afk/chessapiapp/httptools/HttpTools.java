package com.github.quentinsmith3006afk.chessapiapp.httptools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class HttpTools {
    public static final String START = "";

    public static Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static HttpClient httpClient = HttpClient.newBuilder().build();

    public static String request(String endPointUrl) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(START + endPointUrl))
                    .build();

            String json = request(httpRequest);

            // ClassName resp = GSON.fromJson(json, ClassName.class);

        } catch (URISyntaxException urise) {
            urise.printStackTrace();
        }

        return "";
    }

    public static String request(HttpRequest httpRequest) {
        String json = "";
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            json = response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return json;
    }
}
