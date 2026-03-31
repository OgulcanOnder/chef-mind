package com.ogulcanonder.chef_mind.dto.response;

import java.util.List;

public class DtoRecipeAiResponse {
        private String description;
        private List<String> steps;
        private List<String> alternatives;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
