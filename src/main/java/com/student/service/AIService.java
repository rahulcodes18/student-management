package com.student.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class AIService {

    private final Client client;

    public AIService(@Value("${gemini.api.key}") String apiKey) {

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String askAI(String question) {

        try {

            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-3.6-flash",
                            question,
                            null
                    );

            return response.text();

        } catch (Exception e) {

            e.printStackTrace();

            return "AI service error: " + e.getMessage();
        }
    }
}