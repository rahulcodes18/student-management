package com.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.student.service.AIService;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askAI(
            @RequestBody AIRequest request,
            Authentication authentication) {

        // Check authentication
        if (authentication == null) {
            return ResponseEntity.status(401)
                    .body("Authentication required.");
        }

        // Check request
        if (request == null ||
                request.getQuestion() == null ||
                request.getQuestion().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body("Please enter a question.");
        }

        // Get user role
        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .map(authority -> authority.getAuthority())
                .orElse("");

        String question = request.getQuestion().trim();

        // ==============================
        // STUDENT AI
        // ==============================

        if ("ROLE_STUDENT".equals(role)) {

            String prompt = """
                    You are an AI Student Assistant for a Student Management System.

                    Help students with:
                    - Java
                    - SQL
                    - Programming
                    - Interview preparation
                    - Study planning
                    - Academic questions
                    - Error explanation

                    Give simple, clear and practical answers.

                    Student Question:
                    """ + question;

            try {

                String answer = aiService.askAI(prompt);

                return ResponseEntity.ok(answer);

            } catch (Exception e) {

                e.printStackTrace();

                return ResponseEntity.internalServerError()
                        .body("AI service error: " + e.getMessage());
            }
        }

        // ==============================
        // ADMIN AI
        // ==============================

        if ("ROLE_ADMIN".equals(role)) {

            String prompt = """
                    You are an AI Assistant for a Student Management System administrator.

                    Help administrators with:
                    - Student management
                    - Student statistics
                    - Department analysis
                    - Database insights
                    - Administrative questions

                    Give clear and practical answers.

                    Admin Question:
                    """ + question;

            try {

                String answer = aiService.askAI(prompt);

                return ResponseEntity.ok(answer);

            } catch (Exception e) {

                e.printStackTrace();

                return ResponseEntity.internalServerError()
                        .body("AI service error: " + e.getMessage());
            }
        }

        // ==============================
        // UNAUTHORIZED ROLE
        // ==============================

        return ResponseEntity.status(403)
                .body("You are not authorized to use the AI assistant.");
    }


    // ==============================
    // REQUEST BODY
    // ==============================

    public static class AIRequest {

        private String question;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }
}