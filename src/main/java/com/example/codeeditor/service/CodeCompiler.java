package com.example.codeeditor.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class CodeCompiler {

    private static final String JUDGE0_API_URL = "https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&wait=true&fields=*";
    private static final String JUDGE0_API_KEY = "";


    public String compile(String code) throws IOException, InterruptedException, URISyntaxException {
        // Construct the request body
        String requestBody = "{\n" +
                "    \"language_id\": 71,\n" +
                "    \"source_code\": \"" + base64Encode(code) + "\",\n" +
                "    \"stdin\": \"world\"\n" +
                "}";

        // Create the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(JUDGE0_API_URL))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", JUDGE0_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the HTTP request
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());

        // Return the output
        String output = rootNode.get("stdout").asText();

        byte[] decoded = Base64.getMimeDecoder().decode(output);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);

        return decodedStr;
    }

    private String base64Encode(String input) {
        return java.util.Base64.getEncoder().encodeToString(input.getBytes());
    }
}
