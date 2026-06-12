package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class DtoRecipeAiRequest {

    @NotEmpty(message = "Not ingredients cannot be empty")
    List<String>ingredients;

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
