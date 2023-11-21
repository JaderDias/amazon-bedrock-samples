package com.example.app.pojo;

public class CohereResponse {

    private Generation[] generations;
    private String id;
    private String prompt;

    public Generation[] getGenerations() {
        return generations;
    }

    public void setGenerations(Generation[] generations) {
        this.generations = generations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
